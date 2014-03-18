package clueGame;

import java.util.HashSet;
import java.util.Set;

public class Player {
	private String name;
	private Set<Card> myCards = new HashSet<Card>();
	
	public Card disproveSuggestion(String person, String room, String weapon) {
		// Create dummy card to remove error messages until function is implemented
		Card nullCard = new Card();
		return nullCard;
	}
	
}
