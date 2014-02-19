package clueTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import clue.IntBoard;

public class IntBoardTests {
	
	@Before
	public void before() {
		IntBoard testBoard = new IntBoard();
		testBoard.calcAdjacencies();
	}

	@Test
	public void adjacencyTest() {
		
		// Top left
		ArrayList<Integer> testList = testBoard.getAdjList(0);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(1));
		Asser.asserEquals(2, testList.size());
		
		// Bottom Right
		ArrayList<Integer> testList = testBoard.getAdjList(15);
		Assert.assertTrue(testList.contains(14));
		Assert.assertTrue(testList.contains(11));
		Asser.asserEquals(2, testList.size());
		
		// Right Edge
		ArrayList<Integer> testList = testBoard.getAdjList(7);
		Assert.assertTrue(testList.contains(11));
		Assert.assertTrue(testList.contains(3));
		Assert.assertTrue(testList.contains(6));
		Asser.asserEquals(3, testList.size());
		
		// Left Edge
		ArrayList<Integer> testList = testBoard.getAdjList(8);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(9));
		Assert.assertTrue(testList.contains(12));
		Asser.asserEquals(3, testList.size());
		
		// Middle
		ArrayList<Integer> testList = testBoard.getAdjList(5);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(9));
		Assert.assertTrue(testList.contains(6));
		Assert.assertTrue(testList.contains(1));
		Asser.asserEquals(4, testList.size());
		
		ArrayList<Integer> testList = testBoard.getAdjList(10);
		Assert.assertTrue(testList.contains(11));
		Assert.assertTrue(testList.contains(14));
		Assert.assertTrue(testList.contains(9));
		Assert.assertTrue(testList.contains(6));
		Asser.asserEquals(4, testList.size());





	}

}
