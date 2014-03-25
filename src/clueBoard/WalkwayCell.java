package clueBoard;

import java.awt.Graphics;

public class WalkwayCell extends BoardCell {
	
	public WalkwayCell(int row, int column) {
		super(row, column);
	}

	@Override
	public boolean isWalkway(){
		return true;
	}
	
	public void draw(Graphics g, Board b) {
		g.drawRect(getColumn()*(boardWidth/23), getRow()*(boardHeight/22), boardWidth/23, boardHeight/22);
	}
}
