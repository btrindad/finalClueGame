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
	
	public Board(String configFile, String legendFile) {
		
	}
	
	public void loadLegend() throws FileNotFoundException{
		
	}
	
	public void loadBoard() throws FileNotFoundException{
		
	}
	
	public void loadConfigFiles() {
		
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
		return new HashMap<Character,String>();
	}
	
	
}
