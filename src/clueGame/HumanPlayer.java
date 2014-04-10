package clueGame;

import clueBoard.Board;

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
			if(board.clicked() != null){
				currentLocation = board.calcIndex(board.clicked().getRow(), board.clicked().getColumn());
				humanMustFinish = false;
			}
		}
		board.repaint();
	}
}
