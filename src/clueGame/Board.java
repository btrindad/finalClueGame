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
		System.out.println("Rooms is now this large: " + rooms.size());
		input.close();
	}
	
	public void loadBoard() throws FileNotFoundException, BadConfigFormatException {
		FileReader read = new FileReader(configFile);
		Scanner input = new Scanner(read);
		while (input.hasNextLine()) {
			// Read config?
		}
		input.close();
	}
	
	public void loadConfigFiles() {
		try {
			loadLegend();
			//loadBoard();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			
		} catch (BadConfigFormatException e) {
			
		}
		
		
	}
	
	public RoomCell getRoomCellAt(int rows, int columns) {
		return new RoomCell();
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
