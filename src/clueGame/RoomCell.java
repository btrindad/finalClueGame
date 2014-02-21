package clueGame;

public class RoomCell extends BoardCell {
	public enum DoorDirection {UP, DOWN, RIGHT, LEFT, NONE};
	DoorDirection doorDirection;
	char roomInitial;
	
	
	public boolean isRoom(){
		return true;
	}
	
	public DoorDirection getDoorDirection() {
		return null;
	}
	
	public char getInitial() {
		return roomInitial;
	}
}
