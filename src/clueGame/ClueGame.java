package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import clueBoard.Board;

public class ClueGame {
	private Solution theSolution;
	private Board theBoard;
	private ArrayList<Player> players;
	private Set<Card> deck;
	
	public ClueGame() {
		theBoard = new Board("ClueLayout.csv", "ClueLegend.txt");
		theBoard.loadConfigFiles();
		players = new ArrayList<Player>();
		deck = new HashSet<Card>();
	}
	
	public void deal() {
		selectAnswer();
		
		while(!deck.isEmpty()){
			
		}
	}
	
	public void loadDeck() {
		try {
			FileReader reader = new FileReader("ClueDeck.txt");
			Scanner inScanner = new Scanner(reader);
			try {
				while (inScanner.hasNextLine()) {
					String s = inScanner.nextLine();
					String[] queue = s.split(",");
					Card c = new Card(queue[0], queue[1]);
					System.out.println(c.name + "<--------");
					deck.add(c);
					System.out.println("java sucks");
				}
			}
			finally {
				inScanner.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("ClueDeck.txt not found");
			e.printStackTrace();
		}
	}
	
	public void loadPlayers() {
		try {
			FileReader reader = new FileReader("CluePlayers.txt");
			Scanner inScanner = new Scanner(reader);
			try {
				boolean firstLoaded = true;
				while (inScanner.hasNextLine()) {
					System.out.println("Stupid project");
					String s = inScanner.nextLine();
					String[] queue = s.split(",");
					System.out.println("Stupid setup");
					if (firstLoaded) {
						System.out.println("B");
						HumanPlayer h = new HumanPlayer(queue[0], queue[1], Integer.parseInt(queue[2]));
						players.add(h);
						firstLoaded = false;
					}
					else {
						System.out.println("T");
						ComputerPlayer c = new ComputerPlayer(queue[0], queue[1], Integer.parseInt(queue[2]));
						players.add(c);						
					}
				}
			}
			finally {
				inScanner.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void loadConfigFiles() {
		System.out.println("Hey");
		loadDeck();
		System.out.println("Hi");
		loadPlayers();
		System.out.println("Ouch");
	}
	
	public void selectAnswer() {
		
	}
	
	public void handleSuggestion (String person, String room, String weapon, Player accusingPerson) {
		
	}
	
	public boolean checkAccusation (Solution solution) {
		return false; // Returns false for testing purposes, change later
		
	}
	

	public Player getPlayer (int n) {
		return players.get(n);
	}
	
	public int getNumPlayers() {
		return players.size();
	}

	/*-----------Getters and Setters for Testing Purposes ONLY ------*/
	
	public Set<Card> getDeck(){ return deck; }
	public ArrayList<Player> getPlayers(){ return players; }

}
