package clueGame;

import java.util.HashSet;
import java.util.Set;

import clueBoard.Board;

public class ClueGame {
	private Solution theSolution;
	private Board theBoard; //new Board("ClueLayout.csv", "ClueLegend.txt");
	private Set<Card> deck;
	private Set<Player> players;
	
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
	
	/*-----------Getters and Setters for Testing Purposes ONLY ------*/
	
	public Set<Card> getDeck(){ return deck; }
	public Set<Player> getPlayers(){ return players; }
}
