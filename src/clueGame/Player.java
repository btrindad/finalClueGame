package clueGame;

import java.util.HashSet;
import java.util.Set;

public class Player {
	private String name;
	private String color;
	private int startingLocation;
	private Set<Card> myCards;
	
	public Player(String n, String c, int sL) {
		name = n;
		color = c;
		startingLocation = sL;
	}
	
	public Card disproveSuggestion(Card person, Card room, Card weapon) {
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
	
	public void addCard(Card c) {
		myCards.add(c);
	}
	

	/*-----------Getters and Setters for Testing Purposes ONLY ------*/
	public Set<Card> getCards() { return myCards; }	
}
