/*
 * These tests are meant to check the ability to load and perform operations on card
 * objects
 * */
package clueTests;

import org.junit.*;

import clueGame.*;
import static org.junit.Assert.*;

public class CardTests {
	private static ClueGame testGame;
	static final int TOTAL_CARDS = 21;
	final int ROOM_CARDS = 9;
	final int WEAPON_CARDS = 6;
	final int PLAYER_CARDS = 6;
	
	@BeforeClass
	public static void setUp(){
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
		Card testCard = new Card("Library", "ROOM");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("Conservatory", "ROOM");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("Kitchen", "ROOM");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("Ballroom", "ROOM");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("Billiard Room", "ROOM");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("Study", "ROOM");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("Dining Room", "ROOM");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("Lounge", "ROOM");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("Hall", "ROOM");
		assertTrue(testGame.getDeck().contains(testCard));
		
	}
	
	/*
	 * test that the deck had all the player cards
	 */
	@Test
	public void testPlayerCards(){
		Card testCard = new Card("Miss Scarlett", "PERSON");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("Colonel Mustard", "PERSON");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("Mrs. White", "PERSON");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("Mr. Green", "PERSON");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("Mrs. Peacock", "PERSON");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("Professor Plum", "PERSON");
		assertTrue(testGame.getDeck().contains(testCard));
	}
	
	/*
	 * test that the deck has all the weapon cards
	 */
	@Test
	public void testWeaponCards(){
		Card testCard = new Card("Candlestick", "WEAPON");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("Knife", "WEAPON");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("Lead Pipe", "WEAPON");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("Revolver", "WEAPON");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("Rope", "WEAPON");
		assertTrue(testGame.getDeck().contains(testCard));
		
		testCard = new Card("Wrench", "WEAPON");
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
