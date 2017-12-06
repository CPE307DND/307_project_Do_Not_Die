package test;

import org.junit.Test;
import logic.Room; 
import logic.Treasure; 
import static org.junit.Assert.assertEquals;

public class TestIntegration {
	logic.Room room1;
	logic.Room room2;
	logic.Room room3;
	logic.Room room4;
   
    logic.Character p1;
    logic.Sword sword;
    logic.Helm helm;
    logic.Gauntlets gauntlets;
	
	public TestIntegration() {
		room1 = new Room(1, 1, 1, 1, 1, 1, 1, 1, 1);
		room2 = new Room(5, 0, 1, 1, 1, 1, 1, 1, 1);
		room3 = new Room(0, 0, 1, 1, 1, 1, 1, 1, 1);
		room4 = new Room(0, 0, 1, -1, -1, -1, -1, -1, -1);
      p1 = new logic.Character ("Chaos", 2, true, 2, 2, 2, 2, 2, 2, 2, 1, true);
	}
	
    @Test
	public void testSwordEquip ()
	{
		sword = new logic.Sword("Iron");
		p1.equipped[5] = sword;
		assertEquals(sword.toString(), p1.equipped[5].toString());
	}
	
	@Test
	public void testHelmEquip ()
	{
		helm = new logic.Helm("Iron");
		p1.equipped[4] = helm;
		assertEquals(helm.toString(), p1.equipped[4].toString());
	}
	
	@Test
	public void testGauntlets ()
	{
		gauntlets = new logic.Gauntlets("Iron");
		p1.equipped[3] = gauntlets;
		assertEquals(gauntlets.toString(), p1.equipped[3].toString());
	}
   
	@Test 
	public void TestRoomToString() {
		String retString = room1.toString();
		String retString1 = room4.toString();
		
		String expectedString = "Connections:\nBack: 1\nLeft: 1\nCenter: 1\nRight: 1\n"
				+ "Up: 1\nDown: 1";
		String expectedString1 = "Connections:";
		String wrongString = "Connections:\nBack: 1\nLeft: 1\nCenter: 1\nRight: 1\n"
				+ "Up: 0\nDown: 0";
		boolean assertTest = retString.equals(expectedString);
		boolean assertTest2 = retString.equals(wrongString);
		
		boolean assertTest3 = retString1.equals(expectedString1);
		boolean assertTest4 = retString1.equals(wrongString);
		
		assertEquals((boolean)true, assertTest);
		assertEquals((boolean)false, assertTest2);
		
		assertEquals((boolean)true, assertTest3);
		assertEquals((boolean)false, assertTest4);
	}
	
	@Test 
	public void TestRoomCleared() {
		boolean retCleared1 = room1.roomCleared();
		room1.enemies[0].attacked(10000);
		boolean retCleared2 = room1.roomCleared();
		
		assertEquals((boolean)false, retCleared1);
		assertEquals((boolean)true, retCleared2);
	}
	
	@Test 
	public void TestRoomHasEnemy() {
		String enemyName = (room1.enemies[0].getRace()).toLowerCase();
		String madeUp = "Bob";
		
		int retVal = room1.hasEnemy(enemyName);
		int wrongVal = room1.hasEnemy(madeUp);

		assertEquals((boolean)true, (retVal ==0));
		assertEquals((boolean)false, (wrongVal == 0));
		
	}
	
	@Test 
	public void TestRoomHasTreasures1() {
		boolean retTreasures1 = room1.hasTreasures();
		boolean retTreasures2 = room3.hasTreasures();
		
		assertEquals((boolean)true, retTreasures1);
		assertEquals((boolean)false, retTreasures2);
	}
	
	@Test 
	public void TestRoomHasTreasures2() {
		Treasure existingTreasures = room1.treasures[0];
		
		boolean correctTreasures = room1.hasTreasure(existingTreasures.getName());
		boolean wrongTreasures =  room1.hasTreasure("Boots");
		
		assertEquals((boolean)true, correctTreasures);
		assertEquals((boolean)false, wrongTreasures);
	}
	
	@Test 
	public void TestRoomHasTreasures3() {
		boolean yesTreasures = room1.hasTreasure(0);
		boolean noTreasures1 = room1.hasTreasure(1);
		boolean noTreasures2 =  room3.hasTreasure(0);
		boolean noTreasures3 = room1.hasTreasure(-1);

		assertEquals((boolean)true, yesTreasures);
		assertEquals((boolean)false, noTreasures1);
		assertEquals((boolean)false, noTreasures2);
		assertEquals((boolean)false, noTreasures3);
	}
	
	@Test 
	public void TestRoomGetTreasures1() {
		String treas = room1.treasures[0].getName();
		String dne = "JimBob";
		
		Treasure wrongTreasure = room1.getTreasure(dne);
		Treasure retTreasure = room1.getTreasure(treas);
		
				
		boolean assertTest1 = treas.equals(retTreasure.getName());
		boolean assertTest2 = wrongTreasure == null;
		
		assertEquals((boolean)true, assertTest1);
		assertEquals((boolean)true, assertTest2);
	}
	
	@Test 
	public void TestRoomGetTreasure2() {
		String treas = room1.treasures[0].getName();		
		Treasure retTreasure1 = room1.getTreasure(0);
		
		boolean assertTest3 = treas.equals(retTreasure1.getName());

		assertEquals((boolean)true, assertTest3);
	}
}




