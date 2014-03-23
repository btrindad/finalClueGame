package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import clueBoard.BadConfigFormatException;
import clueBoard.BoardCell;
import clueBoard.RoomCell;

public class ComputerPlayer extends Player {
	private Random rand = new Random();
	private char lastRoomVisited;
	private Map<Card, Boolean> allCards; // The boolean represents whether the card has been seen: 1 for true, 0 for false
	private static Map<Character,String> roomLegend;
	
	
	public ComputerPlayer(String n, String c, int sL) {
		super(n, c, sL);
		allCards = new HashMap<Card, Boolean>();
		loadAllCards();
		loadLegend();
	}

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
	
	public void updateSeen (Card seen, boolean b) {
		allCards.put(seen, b);
	}
	
	public void updateSeen() {
		for (Card c : myCards) {
			allCards.put(c, true);
		}
	}
	
	public void setLastRoomVisited(char c) {
		lastRoomVisited = c;
	}
	
	// Functions for Testing 
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
