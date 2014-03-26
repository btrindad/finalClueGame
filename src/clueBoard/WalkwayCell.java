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
		g.drawRect(getX_coordinate(), getY_coordinate(), 
				getWidth(), getHeight());
				
		g.setColor(Color.yellow);
		g.fillRect(getX_coordinate() + borderLineWidth, 
				getY_coordinate()+borderLineWidth, 
				getWidth()-borderLineWidth, getHeight()-borderLineWidth);
	}
}
