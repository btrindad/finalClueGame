package clueGame;

public class RoomCell extends BoardCell {
	
	public enum DoorDirection {UP, DOWN, RIGHT, LEFT, NONE};
	DoorDirection doorDirection;
	char roomInitial;
	
	public RoomCell(String type, int row, int column) {
		super(row, column);
		
	}
	
	
	public boolean isRoom(){
		return true;
	}
	
	public DoorDirection getDoorDirection() {
		return DoorDirection.NONE;
	}
	
	public char getInitial() {
		return roomInitial;
	}
}
