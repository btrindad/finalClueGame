package clueGame;

public class RoomCell extends BoardCell {
	
	public enum DoorDirection {UP, DOWN, RIGHT, LEFT, NONE};
	DoorDirection doorDirection;
	char roomInitial;
	
	public RoomCell(String type, int row, int column) {
		super(row, column);
		if (type.length() > 1) {
			char dir = type.charAt(1);
			switch (dir) {
			case('U'):
				doorDirection = DoorDirection.UP;
				break;
			case('D'):
				doorDirection = DoorDirection.DOWN;
				break;
			case('L'):
				doorDirection = DoorDirection.LEFT;
				break;
			case('R'):
				doorDirection = DoorDirection.RIGHT;
				break;
			default:
				doorDirection = DoorDirection.NONE;
				break;
			}
		}
		else {
			doorDirection = DoorDirection.NONE;
		}
		roomInitial = type.charAt(0);
	}
	
	public boolean isDoorway() {
		if (doorDirection == DoorDirection.NONE) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public boolean isRoom(){
		return true;
	}
	
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	public char getInitial() {
		return roomInitial;
	}
}
