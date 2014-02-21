package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;



public class Board {

	private static final int MAX_ROWS = 22;
	private static final int MAX_COLUMNS = 23;
	private ArrayList<BoardCell> cells;
	private Map<Character,String> rooms;
	private int numRows;
	private int numColumns;	
	private String legend = "legend.txt";
	
	public void loadLegend() throws FileNotFoundException{
		
	}
	
	public void loadBoard() throws FileNotFoundException{
		
	}
	
	public void loadConfigFiles() {
		
	}
		

	public int calcIndex(int row, int column){
		return (MAX_COLUMNS * row) + column;
	}
	
	public int getRows(){
		return numRows;
	}
	
	public int getColumns(){
		return numColumns;
	}
	
	
}
