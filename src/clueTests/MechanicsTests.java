/*
 *These tests are to check the mechanics of the gameplay. Things like accusations,
 *suggestions, and player movement.
 **/
package clueTests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.*;

import clueGame.*;

public class MechanicsTests {
	private static ClueGame testGame;
	private final int SIZE_OF_HAND = 3;
	
	@BeforeClass
	public static void setUp(){
		testGame =  new ClueGame();
		testGame.loadConfigFiles();
	}
	
	/*
	 * test that the deck is dealt out and that each player gets the same number of
	 * cards
	 */
	@Test
	public void testDeal(){
		testGame.deal();
		for(Player p : testGame.getPlayers()){
			assertEquals(p.getCards().size(), SIZE_OF_HAND);
		}
		assertTrue(testGame.getDeck().isEmpty());
	}
	
	/*
	 * test that players are not dealt any duplicates
	 */
	@Test
	public void testDeckDuplicates(){
		Set<Card> newDeck = new HashSet<Card>();
		for(Player p : testGame.getPlayers()){
			newDeck.addAll(p.getCards());
		}
		assertEquals(newDeck.size(), CardTests.TOTAL_CARDS);
	}
	
	@Test
	public void testCheckAccusationAllCorrect() {
		Solution s = new Solution("Mr. White", "Candlestick", "Library");
		Solution accusation = new Solution("Mr. White", "Candlestick", "Library");
		testGame.setSolution(s);
		assertTrue(testGame.checkAccusation(accusation));
	}
	
	@Test
	public void testCheckAccusationWrongRoom() {
		Solution s = new Solution("Mr. White", "Candlestick", "Library");
		Solution accusation = new Solution("Mr. White", "Candlestick", "Hall");
		testGame.setSolution(s);
		assertFalse(testGame.checkAccusation(accusation));
	}
	
	@Test
	public void testCheckAccusationWrongPerson() {
		Solution s = new Solution("Mr. White", "Candlestick", "Library");
		Solution accusation = new Solution("Mrs. Peacock", "Candlestick", "Library");
		testGame.setSolution(s);
		assertFalse(testGame.checkAccusation(accusation));
	}
	
	@Test
	public void testCheckAccusationWrongWeapon() {
		Solution s = new Solution("Mr. White", "Candlestick", "Library");
		Solution accusation = new Solution("Mr. White", "Knife", "Library");
		testGame.setSolution(s);
		assertFalse(testGame.checkAccusation(accusation));
	}
	
	@Test
	public void testCheckAccusationAllWrong() {
		Solution s = new Solution("Mr. White", "Candlestick", "Library");
		Solution accusation = new Solution("Mrs. Peacock", "Knife", "Hall");
		testGame.setSolution(s);
		assertFalse(testGame.checkAccusation(accusation));
	}
	

}
