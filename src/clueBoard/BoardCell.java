package clueBoard;

import java.awt.Graphics;

import clueBoard.RoomCell.DoorDirection;

abstract public class BoardCell {

	private int row;
	private int column;
	public final static int boardWidth = 805;
	public final static int boardHeight = 770;
	
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

	public DoorDirection getDoorDirection() {
		return DoorDirection.NONE;
	}
	
	public abstract void draw(Graphics g, Board b);

}
