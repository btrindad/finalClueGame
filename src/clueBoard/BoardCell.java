package clueBoard;

import java.awt.Graphics;

import clueBoard.RoomCell.DoorDirection;

abstract public class BoardCell {

	private int row;
	private int column;
	private Integer cellWidth;
	private Integer cellHeight;

	public BoardCell(int row, int column) {

		this.row = row;
		this.column = column;

	}

	public boolean isRoom() {
		return false;
	}

	public boolean isWalkway() {
		return false;
	}

	public boolean isDoorway() {
		return false;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
	
	public int getWidth(){
		return cellWidth;
	}
	
	public int getHeight(){
		return cellHeight;
	}

	public DoorDirection getDoorDirection() {
		return DoorDirection.NONE;
	}

	//use the dimensions of the board to calculate the dimensions of a particular cell
	public void loadDimensions(Board b) {
		if (cellWidth == null || cellHeight == null) {
			cellWidth = Board.boardWidthPixels / b.getColumns();
			cellHeight = Board.boardHeightPixels / b.getRows();
		}
	}
	
	public abstract void draw(Graphics g, Board b);
	
	/* ------ helper functions for getting the x and y coordinate of a cell for drawing -----*/
	public int getX_coordinate(){
		return getColumn()*(cellWidth) + (Board.marginSizePixels / 2);
	}
	
	public int getY_coordinate(){
		return getRow() * (cellHeight) + (Board.marginSizePixels/ 2);
	}
}
