package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import clueBoard.BoardCell;
import clueBoard.RoomCell;

public class ComputerPlayer extends Player {
	private Random rand = new Random();
	private char lastRoomVisited;
	/*
	 * this attribute, allCards, works as the notepad in the real version of this game
	 * the possible cards are the keys in the map and the value is a boolean.
	 * If the card has been seen the value for the card is true, if the player has not
	 * yet seen that card the value is false.
	 */
	private Map<Card, Boolean> allCards;
	private static Map<Character,String> roomLegend;
	
	/*
	 * constructor, uses the parent class constructor then loads necessary information
	 */
	public ComputerPlayer(String n, String c, int sL) {
		super(n, c, sL);
		allCards = new HashMap<Card, Boolean>();
		loadAllCards();
		loadLegend();
	}

	/*
	 * load all the cards in the game for use in the allCards variable
	 */
	public void loadAllCards() {
		try {
			FileReader reader = new FileReader("ClueDeck.txt");
			Scanner inScanner = new Scanner(reader);
			try {
				while (inScanner.hasNextLine()) {
					String s = inScanner.nextLine();
					String[] queue = s.split(",");
					Card c = new Card(queue[0], queue[1]);
					allCards.put(c, false);
				}
			} finally {
				inScanner.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * load the legend of the rooms in the game, for reference when accessing the player's 
	 * location
	 */
	public void loadLegend() {
			try {
				roomLegend = new HashMap<Character,String>();
				FileReader read = new FileReader("ClueLegend.txt");
				Scanner input = new Scanner(read);

				try {

					String line = new String();
					while (input.hasNextLine()) {
						line = input.nextLine();
						if (line.contains(", ")) {
							String[] parts = line.split(", ");
							if (parts.length == 2) {
								roomLegend.put(parts[0].charAt(0), parts[1]);
							}

						}

					}
				} finally {
					input.close();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}

	/*
	 * have the computer player pick a target to move to. Any room that was not
	 * recently visited gets priority. If there are no rooms available the target
	 * is chosen pseudo-randomly
	 */
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
	
	/*
	 * have the computer generate a suggestion using known possibilities
	 */
	public Solution createSuggestion(char currentRoom) {
		ArrayList<Card> possiblePeople = new ArrayList<Card>();
		ArrayList<Card> possibleWeapons = new ArrayList<Card>();
		Card roomCard = new Card();
		for (Card c : allCards.keySet()) {
			if (!allCards.get(c)) {
				if (c.cardType == CardType.PERSON) {
					possiblePeople.add(c);
				}
				else if (c.cardType == CardType.WEAPON) {
					possibleWeapons.add(c);
				}
			}
			
			if (c.name.toUpperCase().equals(roomLegend.get(currentRoom).toUpperCase())) {
				roomCard = c;
			}
		}
		
		Solution aSuggestion = new Solution(possiblePeople.get(rand.nextInt(possiblePeople.size())),
				possibleWeapons.get(rand.nextInt(possibleWeapons.size())), roomCard);
		
		return aSuggestion;
		
	}
	
	/*
	 * when a new card is seen update possibilities
	 */
	public void updateSeen (Card seen, boolean b) {
		allCards.put(seen, b);
	}
	
	/*
	 * set all cards as seen
	 */
	public void updateSeen() {
		for (Card c : myCards) {
			allCards.put(c, true);
		}
	}
	
	/*
	 * update last room visited
	 */
	public void setLastRoomVisited(char c) {
		lastRoomVisited = c;
	}
	
	/* ----------------- Functions for Testing -------------------------- */ 
	public void clearAllCards() {
		allCards.clear();
	}
	
	public void updateAllCards(Card c, Boolean b) {
		allCards.put(c, b);
	}
	
	public void printAllCards() {
		System.out.println(allCards.toString());
	}
	
}
