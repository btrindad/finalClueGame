package clueGame;

import javax.swing.JOptionPane;

import clueBoard.Board;
import clueBoard.Board.CellClicked;

public class HumanPlayer extends Player {
	private boolean humanMustFinish;

	public HumanPlayer(String n, String c, int sL) {
		super(n, c, sL);
	}

	public void makeMove(Board board){
		humanMustFinish = true;
		board.setDrawTargets(true);
		board.repaint();
		while(humanMustFinish == true){
			if(board.clickTarget != null){
				currentLocation = board.calcIndex(board.clickTarget.getRow(), board.clickTarget.getColumn());
				humanMustFinish = false;
				board.clickTarget = null;
				board.setDrawTargets(false);
			}
		}
		board.repaint();
	}
}
