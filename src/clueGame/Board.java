package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class Board {

	private ArrayList<BoardCell> cells;
	private Map<Character,String> rooms;
	private int numRows;
	private int numColumns;
	private String configFile;
	private String legendFile;
	
	public Board(String configFile, String legendFile) {
		this.configFile = configFile;
		this.legendFile = legendFile;
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
					throw new BadConfigFormatException();
				}
			}
			else {
				// Is not comma delimited
				input.close();
				throw new BadConfigFormatException();
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
		while (input.hasNextLine()) {
			line = input.nextLine();
			if (line.contains(",")) {
				String[] parts = line.split(",");
				column = 0;
				for (String s : parts) {
					if (s.equals("W")) {
						cells.add(new WalkwayCell(row, column));
					} else {
						cells.add(new RoomCell(s, row, column));
					}
					column++;
				}
				if (numColumns == 0) {
					numColumns = column;
				}
				row++;
				
			} else {
				input.close();
				throw new BadConfigFormatException();
			}
		}
		if (numRows == 0) {
			numRows = row;
		}
		input.close();
	}
	
	public void loadConfigFiles() {
		try {
			loadLegend();
			loadBoard();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			
		} catch (BadConfigFormatException e) {
			
		}
		
		
	}
	
	public RoomCell getRoomCellAt(int row, int column) {
		return new RoomCell(null, row, column);
	}
	
	public BoardCell getCellAt(int index) {
		return null;
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
	
	
}
