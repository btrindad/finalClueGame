package clueGame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Player {
	private Random r = new Random();
	private String name;
	private String color;
	private int startingLocation;
	private int currentLocation;
	protected Set<Card> myCards;
	
	public Player(String n, String c, int sL) {
		name = n;
		color = c;
		startingLocation = sL;
		currentLocation = sL;
		myCards = new HashSet<Card>();
	}
	
	public Card disproveSuggestion(Card person, Card room, Card weapon) {
		ArrayList<Card> disprovedCards = new ArrayList<Card>();
		System.out.println(disprovedCards);
		for (Card c : myCards) {
			if (person.equals(c)) {
				disprovedCards.add(c);
			}
			else if (room.equals(c)) {
				disprovedCards.add(room);
			}
			else if (weapon.equals(c)) {
				disprovedCards.add(weapon);
			}
		}
		System.out.println(disprovedCards);
		if (disprovedCards.isEmpty()) {
			return null;
		}
		else {
			return (disprovedCards.get(r.nextInt(disprovedCards.size())));
		}
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
