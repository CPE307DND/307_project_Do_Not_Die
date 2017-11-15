/*
 * Author: Cristian Rangel
 */


package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class TestCharacter
{
	public TestCharacter ()
	{
		overdamage = 1000;
		underdamage = 1;
		overheals = 1000;
		underheals = 1;
		p1 = new logic.Character ("Chaos", 2, true, 2, 2, 2, 2, 2, 2, 2);
		item = new logic.Weapon (5, 0);
	}
	
	@Test
	public void testAttackedOverkill ()
	{
		assertEquals (true, p1.attacked (overdamage));
	}
	
	@Test
	public void testAttackedUnder ()
	{
		p1.setHealth (p1.getMaxHealth ());
		assertEquals (false, p1.attacked (underdamage));
	}

	@Test
	public void testHealedOver ()
	{
		p1.setHealth (1);
		p1.healed (overheals);
		assertEquals (p1.getMaxHealth (), p1.getHealth ());
	}

	@Test
	public void testHealedUnder ()
	{
		p1.setHealth (1);
		p1.healed (underheals);
		assertEquals (p1.getHealth (), 2);
	}

	@Test
	public void testIsDeadYes ()
	{
		p1.setHealth (0);
		assertEquals (true, p1.isDead ());
	}

	@Test
	public void testIsDeadNo ()
	{
		p1.setHealth (p1.getMaxHealth ());
		assertEquals (false, p1.isDead ());
	}

	@Test
	public void testAddToInventorySingleAdd ()
	{
		assertEquals (true, p1.addToInventory (item));
	}

	@Test
	public void testAddToInventoryDoubleAdd ()
	{
		p1.addToInventory (item);
		assertEquals (false, p1.addToInventory (item));
	}

	@Test
	public void testInInventoryIsThere ()
	{
		p1.addToInventory (item);
		assertEquals (0, p1.inInventory (item));
	}

	@Test
	public void testInInventoryNotThere ()
	{
		ArrayList <logic.Treasure> hold = p1.inventory;
		p1.inventory = new ArrayList <logic.Treasure> ();
		assertEquals (-1, p1.inInventory (item));
		p1.inventory = hold;
	}

	@Test
	public void testRemoveFromInventoryIsThere ()
	{
		p1.addToInventory (item);
		assertEquals (true, p1.removeFromInventory (item));
	}

	@Test
	public void testRemoveFromInventoryNotThere ()
	{
		p1.removeFromInventory (item);
		assertEquals (false, p1.removeFromInventory (item));
	}
	
	@Test
	public void testRollD20 ()
	{
		assertTrue (p1.rolld20 () <= 20);
	}
	
	@Test
	public void testRollD12 ()
	{
		assertTrue (p1.rolld12 () <= 12);
	}
	
	@Test
	public void testRollD10 ()
	{
		assertTrue (p1.rolld10 () <= 10);
	}
	
	@Test
	public void testRollD8 ()
	{
		assertTrue (p1.rolld8 () <= 8);
	}
	
	@Test
	public void testRollD6 ()
	{
		assertTrue (p1.rolld6 () <= 6);
	}
	
	@Test
	public void testRollD4 ()
	{
		assertTrue (p1.rolld4 () <= 4);
	}
	
	logic.Character p1;
	int overdamage, underdamage, overheals, underheals;
	logic.Treasure item;
}
