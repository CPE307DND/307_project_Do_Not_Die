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
		Character p1 = new Character ("Chaos", 0, true, 200, 2, 2, 2, 2, 2, 2);
		String input = "";
		String BASEMSG = "\nWhat do you want to do?\n[(I)nspect]\t[I(N)ventory]", prompt = "";
		String RGTMSG = "\t[Move (R)ight]", LEFMSG = "\t[Move (L)eft]";
		String UPMSG = "\t[Move (U)p]", DWNMSG = "\t[Move (D)own]";
		String CTRMSG = "\t[Move (F)orward]", BCKMSG = "\t[Move (B)ackward]";
		String [] msgs = {BCKMSG, LEFMSG, CTRMSG, RGTMSG, UPMSG, DWNMSG};
		
		while (input != null)
		{
			// Fight enemies in the room before you can do anything else
			if (map.current.numenemies > 0 && !map.current.roomCleared ())
			{
				if (map.current.numenemies > 1)
					System.out.println ("There are " + map.current.numenemies +
							" enemies in the room, prepare to fight.");
				else
					System.out.println ("There is an enemy in the room, prepare to fight");
				
				battle (p1, map);
			}
			
			
			prompt = BASEMSG;
			for (int i = 0; i < map.current.connections.length; i++)
			{
				if (map.current.connections [i] >= 0)
					prompt += msgs [i];
			}
			
			System.out.println (prompt);
			input = input ();
			
			if (input == null || input.toLowerCase().equals ("quit") ||
					input.toLowerCase().equals ("q") || input.toLowerCase().equals ("exit") ||
					input.toLowerCase().equals ("e"))
			{
				input = null;
				continue;
			}
			
			switch (input.toLowerCase ())
			{
				case ("i"):
				{
					if (map.current.hasTreasure ())
					{
						System.out.println ("Treasures:\n");
						map.current.listTreasures ();
					}
					else
						System.out.println ("Nothing in the room.");
					break;
				}
				case ("n"):
				{
					p1.inventoryCheck ();
					break;
				}
				case ("b"):
				{
					if (map.current.connections [0] >= 0)
						map.moveBack ();
					else
						System.out.println ("Not a valid action.");
					break;
				}
				case ("l"):
				{
					if (map.current.connections [1] >= 0)
						map.moveLeft ();
					else
						System.out.println ("Not a valid action.");
					break;
				}
				case ("f"):
				{
					if (map.current.connections [2] >= 0)
						map.moveCenter ();
					else
						System.out.println ("Not a valid action.");
					break;
				}
				case ("r"):
				{
					if (map.current.connections [3] >= 0)
						map.moveRight ();
					else
						System.out.println ("Not a valid action.");
					break;
				}
				case ("u"):
				{
					if (map.current.connections [4] >= 0)
						map.moveUp ();
					else
						System.out.println ("Not a valid action.");
					break;
				}
				case ("d"):
				{
					if (map.current.connections [5] >= 0)
						map.moveDown ();
					else
						System.out.println ("Not a valid action.");
					break;
				}
				default:
				{
					System.out.println ("Not a valid action.");
					break;
				}
			}
		}
	}
	
	// Takes in the player, and the current map.
	// Returns true if the player died
	static Boolean battle (Character p1, Map map)
	{
		Character enemy, hold = null;
		PriorityQueue <Character> orderq = new PriorityQueue <Character> ();
		Character [] order = new Character [map.current.numenemies + 1], aselect = new Character [map.current.numenemies + 1];
		int turn = 0, roll = 0, aselected;
		String input = "";
		
		// Put player and enemies into a priority queue based on initiative
		System.out.println ("You rolled " + p1.initiative () + " for initiative.");
		orderq.add (p1);
		
		for (int i = 0; i < map.current.numenemies; i++)
		{
			if (map.current.numenemies > 1)
				System.out.println (map.current.enemies[i].getRace() + " " + i + " rolled " +
			map.current.enemies [i].initiative () + " for initiative.");
			else
				System.out.println (map.current.enemies [i].getRace() + " rolled " +
			map.current.enemies [i].initiative () + " for initiative.");
			
			orderq.add (map.current.enemies [i]);
		}
		
		System.out.println ("\nThe order is: ");
		orderq.toArray (order);
		
		// Removing player from order, to allow for easier target select
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
		
		
		// Print order
		for (int i = 0; i < order.length && order [i] != null; i++)
			if (order [i].getName () != null)
				System.out.println (order [i].getName ());
			else
				System.out.println (order [i].getRace ());
		
		
		// Battle manager
		while (!p1.isDead () && !map.current.roomCleared ())
		{
			if (turn >= order.length || order [turn] == null)
			{
				System.out.println ("\nTop of the order again.\n");
				turn = 0;
			}
			
			if (order [turn].getName () != null)
			{
				while (!input.toLowerCase().equals ("a") && !input.toLowerCase().equals ("attack"))
				{
					System.out.println ("\nIt's your turn, what do you want to do?\n[(A)ttack][(C)heck Bag]");
					input = input ();
					if (input.toLowerCase().equals ("a") || input.toLowerCase().equals ("attack"))
					{
						System.out.println ("Attack who?");
						for (int i = 0; i < aselect.length && aselect [i] != null; i++)
							if (!map.current.enemyDead (i))
								System.out.println (i + ": " + aselect [i].printEnemy ());
						
						while (((aselected = Integer.parseInt (input ())) >= aselect.length) || aselect [aselected] == null)
							System.out.println ("Not a valid number");
						
						enemy = aselect [aselected];
						roll = p1.rolld20 ();
						System.out.println ("\nYou rolled " + roll + " verses the " + enemy.getRace () + "'s AC");
						if (roll > enemy.getAC ())
						{
							System.out.println ("Attacked for " + p1.getDamage ());
							if (enemy.attacked (p1.getDamage ()))
							{
								System.out.println ("\n\nThe weapon swung true\nThe " +
							enemy.getRace () + " fell\nA fatal blow\nTo the left pinky toe.\n");
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
					else if (input.toLowerCase().equals ("c") || input.toLowerCase().equals ("check"))
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
		
		return p1.isDead ();
	}
	
	// Gets input from the user, and returns it as a String
 	static String input ()
	{
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		String ret = null;
		
		try { ret = br.readLine (); }
		catch (IOException e) { e.printStackTrace(); }
		
		return ret;
	}
}
