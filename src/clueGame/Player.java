package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import clueBoard.Board;
import clueBoard.BoardCell;

public class Player {
	private Random r = new Random();
	private String name;
	private Color color;
	private int startingLocation;
	protected int currentLocation;
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
			return Color.YELLOW.darker();
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
	
	public void draw(Graphics g, Board b, boolean targets){
		try{
			BoardCell cell = b.getCellAt(startingLocation);
			g.setColor(Color.BLACK);
			g.drawOval(cell.getX_coordinate(), cell.getY_coordinate(), 
					cell.getWidth(), cell.getHeight());
			g.setColor(color);
			g.fillOval(cell.getX_coordinate(), cell.getY_coordinate(), 
					cell.getWidth(), cell.getHeight());
		}catch (NullPointerException n){
			System.out.println(n.getMessage());
		}
	}
	

	/*-----------Getters and Setters for Testing Purposes ONLY ------*/
	public Set<Card> getCards() { return myCards; }	
}
