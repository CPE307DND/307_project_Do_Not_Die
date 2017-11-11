package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

public class TestPlayer
{
	public TestPlayer ()
	{
		overdamage = 1000;
		underdamage = 1;
		overheals = 1000;
		underheals = 1;
		p1 = new logic.Player ("Chaos", 2, true, 2, 2, 2, 2, 2, 2, 2);
		item = new logic.Sword ("Steel");
	}
	
	@Test
	public void testAttackedOverkill ()
	{
		assertEquals (true, p1.attacked (overdamage));
	}
	
	@Test
	public void testAttackedUnder ()
	{
		p1.health = p1.maxhealth;
		assertEquals (false, p1.attacked (underdamage));
	}

	@Test
	public void testHealedOver ()
	{
		p1.health = 1;
		p1.healed (overheals);
		assertEquals (p1.maxhealth, p1.health);
	}

	@Test
	public void testHealedUnder ()
	{
		p1.health = 1;
		p1.healed (underheals);
		assertEquals (p1.health, 2);
	}

	@Test
	public void testIsDeadYes ()
	{
		p1.health = 0;
		assertEquals (true, p1.isDead ());
	}

	@Test
	public void testIsDeadNo ()
	{
		p1.health = p1.maxhealth;
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
	
	logic.Player p1;
	int overdamage, underdamage, overheals, underheals;
	logic.Treasure item;
}
