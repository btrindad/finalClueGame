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
		loadDimensions(b);
		g.setColor(Color.black);
		g.drawRect(getColumn()*cellWidth + Board.marginSizePixels/2, getRow()*cellHeight, 
				cellWidth, cellHeight);
				
		g.setColor(Color.yellow);
		g.fillRect(getColumn()*(cellWidth)+ borderLineWidth +(Board.marginSizePixels/2), 
				getRow()*(cellHeight)+borderLineWidth, 
				(cellWidth)-borderLineWidth, (cellHeight)-borderLineWidth);
	}
}
