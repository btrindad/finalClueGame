package clueGame;

import java.util.HashSet;
import java.util.Set;

public class Player {
	private String name;
	private String color;
	private int startingLocation;
	private Set<Card> myCards = new HashSet<Card>();
	
	public Card disproveSuggestion(String person, String room, String weapon) {
		// Create bogus card to remove error messages until function is implemented
		Card nullCard = new Card("DR. ORANGE", "PERSON");
		return nullCard;
	}
	
	public String getName() {
		return name;
	}
	
	public String getColor() {
		return color;
	}
	
	public int getStartingLocation() {
		return startingLocation;
	}
	
}
