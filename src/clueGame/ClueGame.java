package clueGame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import clueBoard.Board;

public class ClueGame {
	private Solution theSolution;
	private Board theBoard; //new Board("ClueLayout.csv", "ClueLegend.txt");
	public HashSet<Card> deck;
	private ArrayList<Player> players = new ArrayList<Player>();
	
	public void deal() {
		
	}
	
	public void loadConfigFiles() {
		
	}
	
	public void selectAnswer() {
		
	}
	
	public void handleSuggestion (String person, String room, String weapon, Player accusingPerson) {
		
	}
	
	public boolean checkAccusation (Solution solution) {
		return false; // Returns false for testing purposes, change later
		
	}
	
	public Player getPlayer (int n) {
		return players.get(n);
	}
	
	public int getNumPlayers() {
		return players.size();
	}
}
