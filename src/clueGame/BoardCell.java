package clueGame;

abstract public class BoardCell {

	private int row;
	private int column;
	
	public BoardCell(int row, int column) {
		
		this.row = row;
		this.column = column;
		
	}
	
	public boolean isRoom(){
		return false;
	}
	
	public boolean isWalkway(){
			return false;
	}
	
	public boolean isDoorway(){
		return false;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
	

}
