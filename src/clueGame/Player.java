package clueGame;

import java.util.HashSet;
import java.util.Set;

public class Player {
	private String name;
	private Set<Card> myCards;
	
	public Card disproveSuggestion(String person, String room, String weapon) {
		// Create dummy card to remove error messages until function is implemented
		Card nullCard = new Card();
		return nullCard;
	}
	
	/*-----------Getters and Setters for Testing Purposes ONLY ------*/
	public Set<Card> getCards() { return myCards; }
	
}
