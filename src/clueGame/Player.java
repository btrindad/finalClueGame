package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Player {
	private Random r = new Random();
	private String name;
	private Color color;
	private int startingLocation;
	private int currentLocation;
	protected Set<Card> myCards;
	
	public Player(String n, String c, int sL) {
		name = n;
		color = stringToColor(c.toUpperCase());
		startingLocation = sL;
		currentLocation = sL;
		myCards = new HashSet<Card>();
	}
	
	/*
	 * given three cards from a suggestion, if player has a card that disproves the suggestion,
	 * return it
	 */
	public Card disproveSuggestion(Card person, Card room, Card weapon) {
		ArrayList<Card> disprovedCards = new ArrayList<Card>();
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
		if (disprovedCards.isEmpty()) {
			return null;
		}
		else {
			return (disprovedCards.get(r.nextInt(disprovedCards.size())));
		}
	}
	
	// getters and setters
	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getStartingLocation() {
		return startingLocation;
	}
	
	public void addCard(Card c) {
		myCards.add(c);
	}
	
	public Color stringToColor(String colorNameUpcase){
		switch(colorNameUpcase){
		case("YELLOW"):
			return Color.YELLOW;
		case("WHITE"):
			return Color.WHITE;
		case("PURPLE"):
			//Color class does not have purple, and I'm a little colorblind
			//the internet says these RGB values make purple
			return new Color(153, 0 ,153);
		case("RED"):
			return Color.RED;
		case("GREEN"):
			return Color.GREEN;
		case("BLUE"):
			return Color.BLUE;
		default:
			return Color.BLACK;
		}
	}
	

	/*-----------Getters and Setters for Testing Purposes ONLY ------*/
	public Set<Card> getCards() { return myCards; }	
}
