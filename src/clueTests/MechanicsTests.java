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
	
	@Test
	public void testDeal(){
		testGame.deal();
		for(Player p : testGame.getPlayers()){
			assertEquals(p.getCards().size(), SIZE_OF_HAND);
		}
		assertTrue(testGame.getDeck().isEmpty());
	}
	
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

	}
	
	@Test
	public void testCheckAccusationWrongRoom() {
		
	}
	
	@Test
	public void testCheckAccusationWrongPerson() {
		
	}
	
	@Test
	public void testCheckAccusationWrongWeapon() {
		
	}
	
	@Test
	public void testCheckAccusationAllWrong() {
		
	}
	

}
