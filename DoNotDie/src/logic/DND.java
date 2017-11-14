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
		PriorityQueue <Character> orderq = new PriorityQueue <Character> ();
		Character [] order = new Character [4];
		int turn = 0;
		Boolean deathcheck = false;
		String input;
		
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
				input = input ();
				if (input.equals ("A"))
						System.out.println ("Attack");
				else if (input.equals ("C"))
					p1.inventoryCheck ();
				else
					System.out.println ("Invalid input");
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
