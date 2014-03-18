/*
 * These tests are meant to check the ability to load players from a config file and
 * perform operations on player objects
 * */
package clueTests;

import org.junit.*;

import clueBoard.Board;
import clueGame.ClueGame;

public class PlayerTests {
	ClueGame testGame = new ClueGame();
	
	@Test
	public void testNumPlayers() {
		Assert.assertEquals(6, testGame.getNumPlayers());
	}
	
	@Test
	public void testLoadingFirstPlayer() {
		Assert.assertEquals("Miss Scarlett", testGame.getPlayer(0).getName());
		Assert.assertEquals("Red", testGame.getPlayer(0).getColor());
		Assert.assertEquals(160, testGame.getPlayer(0).getStartingLocation());
		
	}
	
	@Test
	public void testLoadingPlayerInMiddle() {
		Assert.assertEquals("Mrs. White", testGame.getPlayer(2).getName());
		Assert.assertEquals("White", testGame.getPlayer(2).getColor());
		Assert.assertEquals(299, testGame.getPlayer(2).getStartingLocation());
	}
	
	@Test
	public void testLoadingLastPlayer() {
		Assert.assertEquals("Professor Plum", testGame.getPlayer(5).getName());
		Assert.assertEquals("Purple", testGame.getPlayer(5).getColor());
		Assert.assertEquals(13, testGame.getPlayer(5).getStartingLocation());
	}
	
}
