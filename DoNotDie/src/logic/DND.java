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
		Character p1 = new Character ("Chaos", 0, true, 200, 2, 2, 2, 2, 2, 2), enemy, hold = null;
		PriorityQueue <Character> orderq = new PriorityQueue <Character> ();
		Character [] order = new Character [4], aselect = new Character [4];
		int turn = 0, roll = 0;
		String input = "";
		
		p1.addToInventory (new Weapon (5));
		map.moveDown ();
		
		System.out.println ("You rolled " + p1.initiative () + " for initiative.");
		orderq.add (p1);
		for (int i = 0; i < map.current.numenemies; i++)
		{
			System.out.println (map.current.enemies[i].getRace() + " " + i + " rolled " + map.current.enemies [i].initiative () + " for initiative.");
			orderq.add (map.current.enemies [i]);
		}
		
		System.out.println ("\nThe order is: ");
		orderq.toArray (order);
		/*orderq.toArray (aselect);
		
		for (int i = 0; i < aselect.length; i++)
		{
			if (aselect [i].getName() != null && )
		}*/
		
		
		for (int i = 0; i < order.length && order [i] != null; i++)
			if (order [i].getName () != null)
				System.out.println (order [i].getName ());
			else
				System.out.println (order [i].getRace ());
		
		while (!p1.isDead () && !map.current.roomCleared ())
		{
			if (order [turn] == null)
			{
				System.out.println ("\nTop of the order again.\n");
				turn = 0;
			}
			
			if (order [turn].getName () != null)
			{
				while (!input.equals ("A"))
				{
					System.out.println ("\nIt's your turn, what do you want to do?\n[(A)ttack][(C)heck Bag]");
					input = input ();
					if (input.equals ("A"))
					{
						System.out.println ("Attack who?");
						for (int i = 0; i < map.current.enemies.length; i++)
							if (!map.current.enemyDead (i))
								System.out.println (map.current.enemies [i].printEnemy());
						
						input = input ();
						
						for (int i = 0; i < map.current.enemies.length; i++)
							if (map.current.enemies [i].equals (input))
							{
								enemy = map.current.enemies [i];
								roll = p1.rolld20 ();
								
								System.out.println ("Your rolled " + roll + " versus the " + enemy.getRace () + "'s AC");
								if (roll > enemy.getAC())
								{
									System.out.println ("Attacked for " + p1.getDamage ());
									if (enemy.attacked (p1.getDamage ()))
									{
										System.out.println ("\nThe weapon swung true\nThe " + enemy.getRace () + " fell\nA fatal blow\nTo the left pinky toe.");
										map.current.enemyKilled (enemy.getInd ());
									}
									else
										System.out.println (enemy.getRace () + " has " + enemy.getHealth () + " health left.");
								}
								else
									System.out.println ("That ain't gonna cut it.");
							}
						break;
					}
					else if (input.equals ("C"))
						p1.inventoryCheck ();
					else
						System.out.println ("Invalid input");
				}
			}
			else
			{
				if (map.current.enemyDead (order [turn].getInd ()))
				{
					turn++;
					continue;
				}
				else
					System.out.println ("\nIt's " + order [turn].getRace () + "'s turn.");
			}
			
			if (((turn + 1) > order.length))
			{
				System.out.println ("\nTop of the order again.");
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
		
		try { ret = br.readLine (); }
		catch (IOException e) { e.printStackTrace(); }
		
		return ret;
	}
}
