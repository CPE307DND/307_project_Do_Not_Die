package logic;

public class DND
{
	public static void main (String [] args)
	{
		Map map = new Map ();
		Player p1 = new Player ("Chaos", 0, true, 2, 2, 2, 2, 2, 2, 2);
		
		
		//Inventory tests
		Weapon wpn = new Weapon (5, 0), wpn2 = new Weapon (5, 0);
		System.out.println (wpn + "\n" + wpn2);
		System.out.println ("Adding weapon 1: " + p1.addToInventory (wpn));
		System.out.println ("wpn2 in inventory: " + p1.inInventory (wpn2));
		System.out.println ("Adding weapon 2: " + p1.addToInventory (wpn2));
		System.out.println ("Weapon in inventory: " + p1.inInventory (wpn));
		System.out.println ("Weapon2 in inventory: " + p1.inInventory (wpn2));
		
		
		
		p1.inventoryCheck ();
	}
}
