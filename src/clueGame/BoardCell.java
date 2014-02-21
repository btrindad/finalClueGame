package clueGame;

abstract public class BoardCell {

	private int row;
	private int column;
	
	public boolean isRoom(){
		return false;
	}
	
	public boolean isWalkway(){
			return false;
	}
	
	public boolean isDoorway(){
		return false;
	}
	

}
