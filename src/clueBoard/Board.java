package clueBoard;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JPanel;

import clueBoard.RoomCell.DoorDirection;
import clueGame.Player;

public class Board extends JPanel {

	// constants that define properties of a board
	public static final int boardWidthPixels = 690;
	public static final int boardHeightPixels = 550;
	public static final int marginSizePixels = 10;

	// data structures to hold data about the board
	private ArrayList<BoardCell> cells;
	private Map<Character, String> rooms;
	private int numRows;
	private int numColumns;
	private Set<Player> playerMarkers;
	private boolean drawTargets;
	private Point p;

	public void setDrawTargets(boolean drawTargets) {
		this.drawTargets = drawTargets;
	}

	// structures to hold data for calculating possible player moves
	private Map<Integer, ArrayList<Integer>> adjMtx;
	private Set<BoardCell> targets;
	private boolean[] visited;

	// variables to hold directories to config files
	private String configFile;
	private String legendFile;

	public Board(String configFile, String legendFile) {
		this.configFile = configFile;
		this.legendFile = legendFile;
		targets = new HashSet<BoardCell>();
		playerMarkers = new HashSet<Player>();
		addMouseListener(new CellClicked());
	}

	/*
	 * Loads the clue legend config file
	 */
	public void loadLegend() throws FileNotFoundException,
			BadConfigFormatException {
		rooms = new HashMap<Character, String>();
		FileReader read = new FileReader(legendFile);
		Scanner input = new Scanner(read);
		String line = new String();
		while (input.hasNextLine()) {
			line = input.nextLine();
			if (line.contains(", ")) {
				String[] parts = line.split(", ");
				if (parts.length == 2) {
					rooms.put(parts[0].charAt(0), parts[1]);
				} else {
					// Too many or too few parts?
					input.close();
					throw new BadConfigFormatException(
							"Legend file has a line with too few or too many parts: "
									+ line);
				}
			} else {
				// Is not comma delimited
				input.close();
				throw new BadConfigFormatException(
						"Legend file has a line that is not comma delimited: "
								+ line);
			}
		}
		input.close();
	}

	/*
	 * loads the board layout from the config file
	 */
	public void loadBoard() throws FileNotFoundException,
			BadConfigFormatException {
		cells = new ArrayList<BoardCell>();
		FileReader read = new FileReader(configFile);
		Scanner input = new Scanner(read);
		String line = new String();
		Set<Character> validRooms = rooms.keySet();
		int row = 0;
		int column;
		while (input.hasNextLine()) {
			line = input.nextLine();
			if (line.contains(",")) {
				String[] parts = line.split(",");
				column = 0;
				for (String s : parts) {

					// First, check length. If any string isn't 1 or two long,
					// the config file is bad.
					if ((s.length() < 1) || (s.length() > 2)) {
						input.close();
						throw new BadConfigFormatException(
								"Config file has a cell with the wrong number of characters: "
										+ s);
					}

					// Next check first character.
					boolean isValid = false;
					for (char c : validRooms) {
						if (s.charAt(0) == c) {
							isValid = true;
						}
					}
					if (!isValid) {
						input.close();
						throw new BadConfigFormatException(
								"Config file has a cell with an invalid character: "
										+ s);
					}

					// If it has two characters, make sure the second is also
					// valid.
					if (s.length() == 2) {
						if ((s.charAt(1) != 'U') && (s.charAt(1) != 'D')
								&& (s.charAt(1) != 'L') && (s.charAt(1) != 'R')
								&& (s.charAt(1) != 'N')) {
							input.close();
							throw new BadConfigFormatException(
									"Config file has an invalid door direction: "
											+ s);
						}
					}

					// OK, add to cells
					if (s.equals("W")) {
						cells.add(new WalkwayCell(row, column));
					} else {
						cells.add(new RoomCell(s, row, column));
					}
					column++;
				}

				// If a width hasn't been set, set it, and check for bad columns
				if (numColumns == 0) {
					numColumns = column;
				} else if (column != numColumns) {
					input.close();
					throw new BadConfigFormatException(
							"Config file has an inconsistent number of columns.");
				}
				row++;

			} else {
				input.close();
				throw new BadConfigFormatException(
						"Config file is not properly comma delimited.");
			}
		}
		if (numRows == 0) {
			numRows = row;
		}
		input.close();
		visited = new boolean[numRows * numColumns];
	}

	/*
	 * call all functions to load data from config files
	 */
	public void loadConfigFiles() {
		try {
			loadLegend();
			loadBoard();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (BadConfigFormatException e) {
			try {
				FileWriter write = new FileWriter("errors.txt", true);
				write.write(e.getMessage());
				write.close();
			} catch (IOException f) {
				System.out.println("Could not write exception log.");
			}
		}
	}

	public RoomCell getRoomCellAt(int row, int column) {
		return (RoomCell) cells.get(calcIndex(row, column));
	}

	public BoardCell getCellAt(int index) {
		return cells.get(index);
	}

	public int calcIndex(int row, int column) {
		return (numColumns * row) + column;
	}

	public int getRows() {
		return numRows;
	}

	public int getColumns() {
		return numColumns;
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}

	public ArrayList<Integer> getAdjList(int index) {
		return adjMtx.get(index);
	}

	/*
	 * calculate for each cell, all adjacent cell. This information is needed
	 * for calculating all moves available to a player
	 */
	public void calcAdjacencies() {
		adjMtx = new HashMap<Integer, ArrayList<Integer>>();
		for (int row = 0; row < numRows; row++) {
			for (int column = 0; column < numColumns; column++) {
				int index = calcIndex(row, column);
				adjMtx.put(index, new ArrayList<Integer>());
				// Check if it is a room without a door, if it is, can't have
				// any adjacencies

				BoardCell current = cells.get(calcIndex(row, column));
				// Check if doorway
				if (current.isDoorway()) {
					DoorDirection dir = cells.get(calcIndex(row, column))
							.getDoorDirection();
					switch (dir) {
					case UP:
						adjMtx.get(index).add(calcIndex(row - 1, column));
						break;
					case DOWN:
						adjMtx.get(index).add(calcIndex(row + 1, column));
						break;
					case LEFT:
						adjMtx.get(index).add(calcIndex(row, column - 1));
						break;
					case RIGHT:
						adjMtx.get(index).add(calcIndex(row, column + 1));
						break;
					case NONE:
						break;
					}
				} else if (current.isWalkway()) {
					// ABOVE
					if (row > 0) {
						if (cells.get(calcIndex(row - 1, column)).isWalkway()
								|| (cells.get(calcIndex(row - 1, column))
										.isDoorway() && (cells.get(
										calcIndex(row - 1, column))
										.getDoorDirection() == DoorDirection.DOWN))) {
							adjMtx.get(index).add(calcIndex(row - 1, column));
						}
					}
					// BELOW
					if (row < (numRows - 1)) {
						if (cells.get(calcIndex(row + 1, column)).isWalkway()
								|| (cells.get(calcIndex(row + 1, column))
										.isDoorway() && (cells.get(
										calcIndex(row + 1, column))
										.getDoorDirection() == DoorDirection.UP))) {
							adjMtx.get(index).add(calcIndex(row + 1, column));
						}
					}
					// LEFT
					if (column > 0) {
						if (cells.get(calcIndex(row, column - 1)).isWalkway()
								|| (cells.get(calcIndex(row, column - 1))
										.isDoorway() && (cells.get(
										calcIndex(row, column - 1))
										.getDoorDirection() == DoorDirection.RIGHT))) {
							adjMtx.get(index).add(calcIndex(row, column - 1));
						}
					}
					// RIGHT
					if (column < (numColumns - 1)) {
						if (cells.get(calcIndex(row, column + 1)).isWalkway()
								|| (cells.get(calcIndex(row, column + 1))
										.isDoorway() && (cells.get(
										calcIndex(row, column + 1))
										.getDoorDirection() == DoorDirection.LEFT))) {
							adjMtx.get(index).add(calcIndex(row, column + 1));
						}
					}
				}
			}
		}
	}

	/*
	 * initialize variables needed to calculate possible moves for a player pass
	 * in current position of the player and number of steps
	 */
	public void startTargets(int row, int column, int move) {
		// Setup
		for (int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}
		if (adjMtx.isEmpty()) {
			calcAdjacencies();
		}
		targets.clear();
		visited[calcIndex(row, column)] = true;
		calcTargets(calcIndex(row, column), move);
	}

	/*
	 * recursive function to calculate all possible moves for a player at cell
	 * "index" with "move" number of steps. All possible targets are put in the
	 * "targets" variable
	 */
	public void calcTargets(int index, int move) {
		for (Integer cell : getAdjList(index)) {
			if (!visited[cell]) {
				visited[cell] = true;
				if (move == 1) {
					targets.add(cells.get(cell));
				} else if (cells.get(cell).isDoorway()) {
					targets.add(cells.get(cell));
				} else {
					calcTargets(cell, (move - 1));
				}
				visited[cell] = false;
			}
		}
	}

	public Set<BoardCell> getTargets() {
		return targets;
	}

	public void addPlayerMarker(final Player p) {
		playerMarkers.add(p);
	}

	/*
	 * draws the board and all the players
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (BoardCell c : cells) {
			c.draw(g, this, false);
		}

		for (Player p : playerMarkers) {
			p.draw(g, this, false);
		}
		
		if (drawTargets == true){
			for(BoardCell c : targets){
				c.draw(g, this, true);
			}
		}
	}
	
	public class CellClicked implements MouseListener{

		public void mousePressed (MouseEvent event){
			p = event.getPoint();
		}
		
		public void mouseClicked (MouseEvent event) {}
		public void mouseReleased (MouseEvent event) {}
		public void mouseEntered (MouseEvent event) {}
		public void mouseExited (MouseEvent event) {}

	}
	
	public BoardCell clicked(){
		for(BoardCell c : targets){
			if(c.wasClicked(p) == true){
				return c;
			}
		}
		return null;
	}
	
	//getters to return total width and height of the board
	public int getBoardHeight(){
		return boardHeightPixels + (boardHeightPixels/getRows()) 
				+ Board.marginSizePixels;
	}
	
	public int getBoardWidth(){
		return boardWidthPixels + (boardWidthPixels/getColumns()) 
				+ marginSizePixels;
	}

}
