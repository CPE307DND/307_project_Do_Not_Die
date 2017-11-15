package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class DND
{
	public static void main (String [] args)
	{
		Map map = new Map ();
		Character p1 = new Character ("Chaos", 0, true, 200, 2, 2, 2, 2, 2, 2), enemy; //, hold = null;
		PriorityQueue <Character> orderq = new PriorityQueue <Character> ();
		Character [] order = new Character [4]; //aselect = new Character [4]; was not used
		int turn = 0, roll = 0, aselected, infiniteloopstopper = 0;
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
		
		//Removing player from order, to allow for easier target select
		orderq.toArray (aselect);
		for (int i = 0; i < aselect.length - 1; i++)
		{
			if (aselect [i] == null || (aselect [i].getName () == null && hold == null))
				continue;
			else
			{
				hold = aselect [i];
				aselect [i] = aselect [i + 1];
				aselect [i + 1] = hold;
			}
		}
		aselect [aselect.length - 1] = null;
		
		//Print order
		for (int i = 0; i < order.length && order [i] != null; i++)
			if (order [i].getName () != null)
				System.out.println (order [i].getName ());
			else
				System.out.println (order [i].getRace ());
		
		//Battle manager
		while (!p1.isDead () && !map.current.roomCleared () && infiniteloopstopper < 25)
		{
			infiniteloopstopper++;
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
						for (int i = 0; i < aselect.length && aselect [i] != null; i++)
							if (!map.current.enemyDead (i))
								System.out.println (i + ": " + aselect [i].printEnemy ());
						
						while (((aselected = Integer.parseInt (input ())) >= aselect.length) || aselect [aselected] == null)
							System.out.println ("Not a valid number");
						
						enemy = aselect [aselected];
						roll = p1.rolld20 ();
						System.out.println ("You rolled " + roll + " verses the " + enemy.getRace () + "'s AC");
						if (roll > enemy.getAC ())
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
						input = "";
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
