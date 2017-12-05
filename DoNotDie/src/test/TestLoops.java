// Author: Justin Herrera

package test;

import org.junit.Test;

import logic.Character;

import static org.junit.Assert.assertEquals;

public class TestLoops {

	logic.Room room1;
	logic.Room room2;
	
	public TestLoops() {
		
		// Treasure Loop Test
		// Room Initialized with 1 Enemy, 1 Treasure, Level 1, and room connections 1
		room1 = new logic.Room(1, 1, 1, 1, 1, 1, 1, 1, 1);
	
		// Enemy Loop Test
		// Room Initialized with 1 Enemy, 1 Treasure, Level 1, and room connections 1
		room2 = new logic.Room(5, 0, 1, 1, 1, 1, 1, 1, 1);
		
		
		
	}
	
	// Treasure Loop Test
	// This method tests the loops that check for all treasures in a Room
	@Test
	public void TestRoom1() {
		
		StringBuilder treasureTest = new StringBuilder();
		StringBuilder treasureExpected = new StringBuilder();
		String treasureIncorrect = "0: Coal";
		treasureExpected.append("0: ");
		treasureExpected.append(room1.treasures[0].getName());
		
		for (int i = 0; i < room1.numtreasures; i++)
			treasureTest.append(i + ": " + room1.treasures[i].getName ());
		
		boolean assertTest = treasureTest.toString().equals(treasureExpected.toString());
		boolean assertTest2 = treasureTest.toString().equals(treasureIncorrect.toString());
		
		assertEquals((boolean)true, assertTest);
		assertEquals((boolean)false, assertTest2);
		
	}
	
	// Enemy Loop Test
	// This method tests the loops that check for all treasures in a Room
	@Test
	public void TestRoom2() {
		
		Character [] enemiesTest = room2.enemies;
		Character [] enemiesExpected = new Character[room2.numenemies];
		StringBuilder test = new StringBuilder();
		StringBuilder expected = new StringBuilder();
		
		for (int i = 0; i < room2.numenemies; i++) {
			enemiesExpected[i] = room2.enemies[i];
		}
		
		for (Character e : enemiesTest) {
			test.append(e);
		}
		
		for (Character e : enemiesExpected) {
			expected.append(e);
		}
		
		boolean assertTest = test.toString().equals(expected.toString());
		assertEquals((boolean)true, assertTest);
	}
}
