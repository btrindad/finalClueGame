package clueBoard;

import java.awt.Color;
import java.awt.Graphics;

public class WalkwayCell extends BoardCell {
	// width of the outline
	private final int borderLineWidth = 1;
	
	public WalkwayCell(int row, int column) {
		super(row, column);
	}

	@Override
	public boolean isWalkway(){
		return true;
	}
	
	// draw a walkway cell onto the board. Yellow squares with black outlines
	public void draw(Graphics g, Board b) {
		loadDimensions(b);
		// draw the outline
		g.setColor(Color.black);
		g.drawRect(getX_coordinate(), getY_coordinate(), 
				getWidth(), getHeight());
				
		// draw the colored in square, include offset for the outline so they don't overlap
		g.setColor(Color.yellow);
		g.fillRect(getX_coordinate() + borderLineWidth, 
				getY_coordinate()+borderLineWidth, 
				getWidth()-borderLineWidth, getHeight()-borderLineWidth);
	}
}
