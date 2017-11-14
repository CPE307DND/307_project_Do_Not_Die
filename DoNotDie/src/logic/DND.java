package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DND
{
	public static void main (String [] args)
	{
		Map map = new Map ();
		Character p1 = new Character ("Chaos", 0, true, 2, 2, 2, 2, 2, 2, 2);
		//Char enemy = new logic.Char (null, 2, true, 2, 2, 2, 2, 2, 2, 2);
		PriorityQueue <Character> orderq = new PriorityQueue <Character> ();
		Character [] order = new Character [4];
		int turn = 0;
		Boolean deathcheck = false;
		
		map.moveDown ();
		
		System.out.println ("You rolled " + p1.initiative () + " for initiative.");
		orderq.add (p1);
		for (int i = 0; i < map.current.numenemies; i++)
		{
			System.out.println (map.current.enemies[i].getRace() + " " + i + " rolled " + map.current.enemies [i].initiative () + " for initiative.");
			orderq.add (map.current.enemies [i]);
		}
		
		System.out.println ();
		
		orderq.toArray (order);
		
		while (!p1.isDead () && !map.current.roomCleared ())
		{
			if (order [turn] == null)
			{
				System.out.println ("Top of the order again.\n");
				turn = 0;
			}
			
			if (order [turn].getName () != null)
			{
				System.out.println ("It's your turn, what do you want to do?\n[(A)ttack][(C)heck Bag]");
				System.out.println (input ());
			}
			else
			{
				System.out.println ("It's " + order [turn].getRace () + "'s turn.");
				deathcheck = order [turn].attacked (p1.getDamage ());
				if (deathcheck)
					map.current.enemyKilled (order [turn].getInd ());
				
				System.out.println (order [turn].getRace () + " attacked. Dead? - " + deathcheck);
			}
			
			if (((turn + 1) > order.length))
			{
				System.out.println ("Top of the order again.");
				turn = 0;
			}
			else
				turn++;
		}
		
		
		//Room tests
		/*map.moveLeft ();
		System.out.println (map.current);
		map.moveLeft ();
		System.out.println (map.current);
		map.moveDown ();
		System.out.println (map.current);
		map.moveRight ();
		System.out.println (map.current);
		map.moveCenter ();
		System.out.println (map.current);
		map.moveBack ();
		System.out.println (map.current);
		map.moveUp ();
		System.out.println (map.current);
		map.moveRight ();
		System.out.println (map.current);
		map.moveRight ();
		System.out.println (map.current);
		map.moveDown ();
		System.out.println (map.current);
		map.moveLeft ();
		System.out.println (map.current);
		map.moveLeft ();
		System.out.println (map.current);
		map.moveLeft ();
		System.out.println (map.current);
		map.moveUp ();
		System.out.println (map.current);
		map.moveDown ();
		System.out.println (map.current);
		map.moveRight ();
		System.out.println (map.current);
		map.moveUp ();
		System.out.println (map.current);
		map.moveRight ();
		System.out.println (map.current);
		map.moveBack ();
		System.out.println (map.current);
		map.moveCenter ();
		System.out.println (map.current);
		map.moveLeft ();
		System.out.println (map.current);
		map.moveUp ();
		System.out.println (map.current + "\n");*/
		
		
		//System.out.println (p1 + "\n");
		//System.out.println (e1 + "\n");
		
		//Inventory tests
		/*Weapon wpn = new Weapon (5, 0), wpn2 = new Weapon (5, 0);
		Armor a1 = new Armor (5, 0, false), a2 = new Armor (5, 0, false);
		Misc m1 = new Misc (5), m2 = new Misc (5);
		System.out.println (wpn + "\n" + wpn2);
		System.out.println (a1 + "\n" + a2);
		System.out.println (m1 + "\n" + m2);
		System.out.println ("Adding weapon 1: " + p1.addToInventory (wpn));
		System.out.println ("wpn2 in inventory: " + p1.inInventory (wpn2));
		System.out.println ("Adding weapon 2: " + p1.addToInventory (wpn2) + "\n");
		
		System.out.println ("Adding armor 1: " + p1.addToInventory (a1));
		System.out.println ("a1 in inventory: " + p1.inInventory (a1));
		System.out.println ("Adding armor 2: " + p1.addToInventory (a2) + "\n");
		
		System.out.println ("Adding misc 1: " + p1.addToInventory (m1));
		System.out.println ("m1 in inventory: " + p1.inInventory (m1));
		System.out.println ("Adding misc 2: " + p1.addToInventory (m2) + "\n");
		
		System.out.println ("\n\nInventory\n");
		p1.inventoryCheck ();*/
		
		
	}
	
	static String input ()
	{
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		String ret = null;
		
		try
		{
			ret = br.readLine ();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
			
		return ret;
	}
}
