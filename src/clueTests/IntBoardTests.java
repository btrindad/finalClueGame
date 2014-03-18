package clueTests;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import clueBoard.IntBoard;

public class IntBoardTests {
	
	IntBoard testBoard;
	ArrayList<Integer> testList;
	
	@Before
	public void before() {
		testBoard = new IntBoard();
		testBoard.calcAdjacencies();
	}

	@Test
	public void adjacencyTest0() {
		
		// Top left
		testList = testBoard.getAdjList(0);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(1));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void adjacencyTest15() {
	
		// Bottom Right
		testList = testBoard.getAdjList(15);
		Assert.assertTrue(testList.contains(14));
		Assert.assertTrue(testList.contains(11));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void adjacencyTest7() {
		
		// Right Edge
		testList = testBoard.getAdjList(7);
		Assert.assertTrue(testList.contains(11));
		Assert.assertTrue(testList.contains(3));
		Assert.assertTrue(testList.contains(6));
		Assert.assertEquals(3, testList.size());
	}
	
	@Test
	public void adjacencyTest8() {
		
		// Left Edge
		testList = testBoard.getAdjList(8);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(9));
		Assert.assertTrue(testList.contains(12));
		Assert.assertEquals(3, testList.size());
	}
	
	@Test
	public void adjacencyTest5() {
		
		// Middle
		testList = testBoard.getAdjList(5);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(9));
		Assert.assertTrue(testList.contains(6));
		Assert.assertTrue(testList.contains(1));
		Assert.assertEquals(4, testList.size());
	}
	
	@Test
	public void adjacencyTest10() {
		
		testList = testBoard.getAdjList(10);
		Assert.assertTrue(testList.contains(11));
		Assert.assertTrue(testList.contains(14));
		Assert.assertTrue(testList.contains(9));
		Assert.assertTrue(testList.contains(6));
		Assert.assertEquals(4, testList.size());
	}
	
	@Test
	public void testTargets0_3() {
		testBoard.startTargets(0, 3);
		Set<Integer> targets = testBoard.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(3));
		Assert.assertTrue(targets.contains(4));
	}
	
	@Test
	public void testTargets14_2() {
		testBoard.startTargets(14, 2);
		Set<Integer> targets = testBoard.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(11));
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(12));
	}
	
	@Test
	public void testTargets6_1() {
		testBoard.startTargets(6, 1);
		Set<Integer> targets = testBoard.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(2));
		Assert.assertTrue(targets.contains(7));
		Assert.assertTrue(targets.contains(10));
		Assert.assertTrue(targets.contains(5));
	}
	
	@Test
	public void testTargets5_4() {
		testBoard.startTargets(5, 4);
		Set<Integer> targets = testBoard.getTargets();
		for (Integer i : targets) {
			System.out.println(i);
		}
		Assert.assertEquals(7, targets.size());
		Assert.assertTrue(targets.contains(2));
		Assert.assertTrue(targets.contains(7));
		Assert.assertTrue(targets.contains(10));
		Assert.assertTrue(targets.contains(15));
		Assert.assertTrue(targets.contains(8));
		Assert.assertTrue(targets.contains(0));
		Assert.assertTrue(targets.contains(13));
	}
	
	@Test
	public void testTargets15_2() {
		testBoard.startTargets(15, 2);
		Set<Integer> targets = testBoard.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(7));
		Assert.assertTrue(targets.contains(10));
		Assert.assertTrue(targets.contains(13));
	}
	
	@Test
	public void testTargets2_3() {
		testBoard.startTargets(2, 3);
		Set<Integer> targets = testBoard.getTargets();
		Assert.assertEquals(7, targets.size());
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(11));
		Assert.assertTrue(targets.contains(4));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(14));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(3));
	}
	
	@Test
	public void testCalcIndex0_0() {
		Assert.assertEquals(0, testBoard.calcIndex(0, 0));
	}
	
	@Test
	public void testCalcIndex2_2() {
		Assert.assertEquals(10, testBoard.calcIndex(2, 2));
	}
	
	@Test
	public void testCalcIndex3_1() {
		Assert.assertEquals(13, testBoard.calcIndex(3, 1));
	}

}
