package clueGame;

import java.util.Set;

import clueBoard.BoardCell;

public class ComputerPlayer extends Player {
	private char lastRoomVisited;
	
	public ComputerPlayer(String n, String c, int sL) {
		super(n, c, sL);
	}

	public BoardCell pickLocation(Set<BoardCell> targets) {
		return null;
		//for (BoardCell b : targets)
		
	}
	
	public void createSuggestion() {
		
	}
	
	public void updateSeen (Card seen) {
		
	}
	
	public void setLastRoomVisited(char c) {
		lastRoomVisited = c;
	}
}
