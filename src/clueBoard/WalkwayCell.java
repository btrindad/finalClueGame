package clueBoard;

import java.awt.Color;
import java.awt.Graphics;

public class WalkwayCell extends BoardCell {
	private final int borderLineWidth = 1;
	
	public WalkwayCell(int row, int column) {
		super(row, column);
	}

	@Override
	public boolean isWalkway(){
		return true;
	}
	
	public void draw(Graphics g, Board b) {
		g.setColor(Color.black);
		g.drawRect(getColumn()*(boardWidth/23), getRow()*(boardHeight/22), boardWidth/23, boardHeight/22);
		g.setColor(Color.yellow);
		g.fillRect(getColumn()*(boardWidth/23)+1, getRow()*(boardHeight/22)+1, 
				(boardWidth/23)-borderLineWidth, (boardHeight/22)-borderLineWidth);
	}
}
