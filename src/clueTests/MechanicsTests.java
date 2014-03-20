/*
 *These tests are to check the mechanics of the gameplay. Things like accusations,
 *suggestions, and player movement.
 **/
package clueTests;

import static org.junit.Assert.*;

import org.junit.*;

import clueGame.ClueGame;
import clueGame.Player;

public class MechanicsTests {
	ClueGame testGame;
	private final int SIZE_OF_HAND = 5;
	
	@BeforeClass
	public void setUp(){
		testGame =  new ClueGame();
		testGame.loadConfigFiles();
	}
	
	@Test
	public void testDeal(){
		testGame.deal();
		for(Player p : testGame.getPlayers()){
			assertEquals(p.getCards().size(), SIZE_OF_HAND);
		}
	}
	

}
