/*
 * These tests are meant to check the ability to load and perform operations on card
 * objects
 * */
package clueTests;

import org.junit.*;

import clueGame.*;
import static org.junit.Assert.*;

public class CardTests {
	private ClueGame testGame;
	static final int TOTAL_CARDS = 21;
	final int ROOM_CARDS = 9;
	final int WEAPON_CARDS = 6;
	final int PLAYER_CARDS = 6;
	
	@BeforeClass
	public void setUp(){
		testGame = new ClueGame();
		testGame.loadConfigFiles();
	}

	/*
	 * Test that upon loading the cards are added to the deck. Check for the correct
	 * number of cards, and the right number of each type of card
	 */
	@Test
	public void testDeck(){
		assertFalse(testGame.getDeck().isEmpty());
		assertEquals(testGame.getDeck().size(), TOTAL_CARDS);
		assertEquals(countCards(CardType.ROOM), ROOM_CARDS);
		assertEquals(countCards(CardType.PERSON), PLAYER_CARDS);
		assertEquals(countCards(CardType.WEAPON), WEAPON_CARDS);
	}
	
	/*
	 * test that deck contains all the rooms
	 */
	@Test
	public void testRoomCards(){
		Card testCard = new Card("library", "Room");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("conservatory", "Room");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("kitchen", "Room");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("ballroom", "Room");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("billiard room", "Room");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("study", "Room");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("dining room", "Room");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("lounge", "Room");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("hall", "Room");
		assertTrue(testGame.getDeck().contains(testCard));
		
	}
	
	@Test
	public void testPlayerCards(){
		Card testCard = new Card("miss scarlett", "Person");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("colonel mustard", "Person");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("mrs. white", "Person");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("reverend green", "Person");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("mrs. peacock", "Person");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("professor plum", "Person");
		assertTrue(testGame.getDeck().contains(testCard));
	}
	
	@Test
	public void testWeaponCards(){
		Card testCard = new Card("candlestick", "Weapon");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("dagger", "Weapon");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("lead pipe", "Weapon");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("Revolver", "Weapon");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("Rope", "Weapon");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("wrench", "Weapon");
		assertTrue(testGame.getDeck().contains(testCard));
	}
	
	int countCards(CardType type){
		int counter = 0;
		for(Card c : testGame.getDeck()){
			if(c.cardType == type){
				counter++;
			}
		}
		return counter;
	}
	
}
