package clueBoard;

import java.awt.Color;
import java.awt.Graphics;

public class RoomCell extends BoardCell {

	public enum DoorDirection {
		UP, DOWN, RIGHT, LEFT, NONE
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
		if (doorDirection == DoorDirection.NONE) {
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

	public void draw(Graphics g, Board b) {
		loadDimensions(b);
		if (isDoorway()) {
			g.setColor(Color.BLUE);
			switch (doorDirection) {
			case UP:
				g.fillRect(getColumn() * (cellWidth)
						+ (Board.marginSizePixels / 2),
						getRow() * (cellHeight), cellWidth, doorThickness);
				break;
			case DOWN:
				g.fillRect(getColumn() * (cellWidth)
						+ (Board.marginSizePixels / 2), getRow() * (cellHeight)
						+ (cellHeight - doorThickness), cellWidth,
						doorThickness);
				break;
			case LEFT:
				g.fillRect(getColumn() * (cellWidth)
						+ (Board.marginSizePixels / 2),
						getRow() * (cellHeight), doorThickness, cellHeight);
				break;
			case RIGHT:
				g.fillRect(getColumn()*(cellWidth) + (Board.marginSizePixels / 2) + (cellWidth - doorThickness), 
						getRow() * (cellHeight),
						doorThickness, cellHeight);
				break;
			}
		}
	}
}
