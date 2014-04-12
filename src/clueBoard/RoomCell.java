package clueBoard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class RoomCell extends BoardCell {

	public enum DoorDirection {
		UP, DOWN, RIGHT, LEFT, NAME, NONE
	};

	DoorDirection doorDirection;
	char roomInitial;
	private final int doorThickness = 5;

	public RoomCell(String type, int row, int column) {
		super(row, column);
		if (type.length() > 1) {
			char dir = type.charAt(1);
			switch (dir) {
			case ('U'):
				doorDirection = DoorDirection.UP;
				break;
			case ('D'):
				doorDirection = DoorDirection.DOWN;
				break;
			case ('L'):
				doorDirection = DoorDirection.LEFT;
				break;
			case ('R'):
				doorDirection = DoorDirection.RIGHT;
				break;
			case ('N'):
				doorDirection = DoorDirection.NAME;
				break;
			default:
				doorDirection = DoorDirection.NONE;
				break;
			}
		} else {
			doorDirection = DoorDirection.NONE;
		}
		roomInitial = type.charAt(0);
	}

	@Override
	public boolean isDoorway() {
		if (doorDirection == DoorDirection.NONE || doorDirection == DoorDirection.NAME) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean isRoom() {
		return true;
	}

	@Override
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}

	public char getInitial() {
		return roomInitial;
	}

	/*
	 * draw a room cell onto the board. Draw door in appropriate direction or 
	 * room name if present
	 */
	public void draw(Graphics g, Board b, boolean targets) {
		loadDimensions(b);
		if (targets == false){
			g.setColor(Color.BLUE);
			switch (doorDirection) {
			case UP:
				g.fillRect(getX_coordinate(),
						getY_coordinate(), getWidth(), doorThickness);
				break;
			case DOWN:
				g.fillRect(getX_coordinate(), getY_coordinate() + (getHeight() - doorThickness), 
						getWidth(), doorThickness);
				break;
			case LEFT:
				g.fillRect(getX_coordinate(), getY_coordinate(), 
						doorThickness, getHeight());
				break;
			case RIGHT:
				g.fillRect(getX_coordinate() + (getWidth() - doorThickness), 
						getY_coordinate(),
						doorThickness, getHeight());
				break;
			case NAME:
				drawRoomName(g,b);
				break;
			case NONE:
				break;
			}
		} else {
			g.setColor(Color.BLUE.brighter());
			g.fillRect(getX_coordinate(), 
					getY_coordinate(), 
					getWidth(), getHeight());
		}
	}
	
	//helper function to draw a room name
	public void drawRoomName(Graphics g, Board b){
		if(doorDirection == DoorDirection.NAME){
			g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
			g.setColor(Color.BLACK);
			g.drawString(b.getRooms().get(roomInitial), 
					getX_coordinate()+(getWidth()/2), getY_coordinate()+(getHeight()/2));
		}
	}

	@Override
	public boolean isWalkway() {
		return false;
	}
}
