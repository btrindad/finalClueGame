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

	@Test
	public void testDeck(){
		assertFalse(testGame.getDeck().isEmpty());
		assertEquals(testGame.getDeck().size(), TOTAL_CARDS);
		assertEquals(countCards(CardType.ROOM), ROOM_CARDS);
		assertEquals(countCards(CardType.PERSON), PLAYER_CARDS);
		assertEquals(countCards(CardType.WEAPON), WEAPON_CARDS);
	}
	
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
