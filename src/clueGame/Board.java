package clueGame;

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

import clueGame.RoomCell.DoorDirection;



public class Board {

	private ArrayList<BoardCell> cells;
	private Map<Character,String> rooms;
	private Map<Integer, ArrayList<Integer>> adjMtx;
	private Set<BoardCell> targets;
	private boolean[] visited;
	private int numRows;
	private int numColumns;
	private String configFile;
	private String legendFile;
	
	public Board(String configFile, String legendFile) {
		this.configFile = configFile;
		this.legendFile = legendFile;
		targets = new HashSet<BoardCell>();
	}
	

	public void loadLegend() throws FileNotFoundException, BadConfigFormatException {
		rooms = new HashMap<Character,String>();
		FileReader read = new FileReader(legendFile);
		Scanner input = new Scanner(read);
		String line = new String();
		while (input.hasNextLine()) {
			line = input.nextLine();
			if (line.contains(", ")) {
				String[] parts = line.split(", ");
				if (parts.length == 2) {
					rooms.put(parts[0].charAt(0), parts[1]);
				}
				else {
					// Too many or too few parts?
					input.close();
					throw new BadConfigFormatException("Legend file has a line with two few or too many parts: " + line);
				}
			}
			else {
				// Is not comma delimited
				input.close();
				throw new BadConfigFormatException("Legend file has a line that is not comma delimited: " + line);
			}
		}
		input.close();
	}
	
	public void loadBoard() throws FileNotFoundException, BadConfigFormatException {
		cells = new ArrayList<BoardCell>();
		FileReader read = new FileReader(configFile);
		Scanner input = new Scanner(read);
		String line = new String();
		int row = 0;
		int column;
		Set<Character> validRooms = rooms.keySet();
		while (input.hasNextLine()) {
			line = input.nextLine();
			if (line.contains(",")) {
				String[] parts = line.split(",");
				column = 0;
				for (String s : parts) {
					
					// First, check length. If any string isn't 1 or two long, the config file is bad.
					if ((s.length() < 1) || (s.length() > 2)) {
						input.close();
						throw new BadConfigFormatException("Config file has a cell with the wrong number of characters: " + s);
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
						throw new BadConfigFormatException("Config file has a cell with an invalid character: " + s);
					}
					
					// If it has two characters, make sure the second is also valid.
					if (s.length() == 2) {
						if ((s.charAt(1) != 'U') &&
								(s.charAt(1) != 'D') &&
								(s.charAt(1) != 'L') &&
								(s.charAt(1) != 'R') &&
								(s.charAt(1) != 'N')) {
							input.close();
							throw new BadConfigFormatException("Config file has an invalid door direction: " + s);
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
					throw new BadConfigFormatException("Config file has an inconsistent number of columns.");
				}
				row++;
				
			} else {
				input.close();
				throw new BadConfigFormatException("Config file is not properly comma delimited.");
			}
		}
		if (numRows == 0) {
			numRows = row;
		}
		input.close();
		visited = new boolean[numRows * numColumns];
	}
	
	public void loadConfigFiles() {
		try {
			loadLegend();
			loadBoard();
		} catch (FileNotFoundException e) {
			
		} catch (BadConfigFormatException e) {
			try {
				FileWriter write = new FileWriter("errors.txt", true);
				write.write(e.getMessage());
				write.close();
			}
			catch (IOException f) {
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
	

	public int calcIndex(int row, int column){
		return (numColumns * row) + column;
	}
	
	public int getRows(){
		return numRows;
	}
	
	public int getColumns(){
		return numColumns;
	}
	
	public Map<Character,String> getRooms() {
		return rooms;
	}

	public ArrayList<Integer> getAdjList(int index) {
		return adjMtx.get(index);
	}

	public void calcAdjacencies() {
		adjMtx = new HashMap<Integer, ArrayList<Integer>>();
		for (int row = 0; row < numRows; row++) {
			for (int column = 0; column < numColumns; column++) {
				int index = calcIndex(row, column);
				adjMtx.put(index, new ArrayList<Integer>());
				// First check if doorway
				if (cells.get(calcIndex(row,column)).isDoorway()) {
					DoorDirection dir = cells.get(calcIndex(row, column)).getDoorDirection();
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
				} else {
					// ABOVE
					if (row > 0) {
						if (cells.get(calcIndex(row - 1, column)).isWalkway() ||
								(cells.get(calcIndex(row - 1,column)).isDoorway() &&
										(cells.get(calcIndex(row - 1,column)).getDoorDirection() == DoorDirection.DOWN))) {
							adjMtx.get(index).add(calcIndex(row - 1, column));
						}
					}
					// BELOW
					if (row < (numRows - 1)) {
						if (cells.get(calcIndex(row + 1, column)).isWalkway() ||
								(cells.get(calcIndex(row + 1,column)).isDoorway() &&
										(cells.get(calcIndex(row + 1,column)).getDoorDirection() == DoorDirection.UP))) {
							adjMtx.get(index).add(calcIndex(row + 1, column));
						}
					}
					// LEFT
					if (column > 0) {
						if (cells.get(calcIndex(row, column - 1)).isWalkway() ||
								(cells.get(calcIndex(row,column - 1)).isDoorway() &&
										(cells.get(calcIndex(row,column - 1)).getDoorDirection() == DoorDirection.RIGHT))) {
							adjMtx.get(index).add(calcIndex(row, column - 1));
						}
					}
					// RIGHT
					if (column < (numColumns - 1)) {
						if (cells.get(calcIndex(row, column + 1)).isWalkway() ||
								(cells.get(calcIndex(row,column + 1)).isDoorway() &&
										(cells.get(calcIndex(row,column + 1)).getDoorDirection() == DoorDirection.LEFT))) {
							adjMtx.get(index).add(calcIndex(row, column + 1));
						}
					}
				}
			}
		}
	}
	
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
		calcTargets(calcIndex(row,column), move);
	}
	

	public void calcTargets(int index, int move) {
		ArrayList<Integer> adjacentCells = new ArrayList<Integer>();
		for (Integer cell : getAdjList(index)) {
			if (!visited[cell]) {
				adjacentCells.add(cell);
			}
		}
		for (Integer cell : adjacentCells) {
			visited[cell] = true;
			if (move == 1) {
				targets.add(cells.get(cell));
			}
			else {
				calcTargets(cell, (move - 1));
			}
			visited[cell] = false;
		}
	}

	public Set<BoardCell> getTargets() {
		return targets;
	}
	
	
}
