package clueGame;

import java.util.Random;
import java.util.Set;

import clueBoard.BoardCell;
import clueBoard.RoomCell;

public class ComputerPlayer extends Player {
	private Random rand = new Random();
	private char lastRoomVisited;
	
	public ComputerPlayer(String n, String c, int sL) {
		super(n, c, sL);
	}

	public BoardCell pickLocation(Set<BoardCell> targets) {
		
		for (BoardCell b : targets) {
			if (b.isRoom()) {
				RoomCell r = (RoomCell)b;
				if (r.getInitial() != lastRoomVisited) {
					return b;
				}
			}
		}

		BoardCell[] temp = targets.toArray(new BoardCell[0]);
		return temp[rand.nextInt(temp.length)];
		
	}
	
	public void createSuggestion() {
		
	}
	
	public void updateSeen (Card seen) {
		
	}
	
	public void setLastRoomVisited(char c) {
		lastRoomVisited = c;
	}
}
