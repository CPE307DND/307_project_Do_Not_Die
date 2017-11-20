package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class DND
{
	public static void main (String [] args)
	{
		Map map = new Map ();
		Character p1 = new Character ("Chaos", 0, true, 200, 2, 2, 2, 2, 2, 2, 1);
		String input = "";
		String BASEMSG = "\nWhat do you want to do?\n[(I)nspect]   [I(N)ventory]", prompt = "";
		String RGTMSG = "   [Move (R)ight]", LEFMSG = "   [Move (L)eft]";
		String UPMSG = "   [Move (U)p]", DWNMSG = "   [Move (D)own]";
		String CTRMSG = "   [Move (F)orward]", BCKMSG = "   [Move (B)ackward]";
		String DIFFICULTY = "[(E)asy]\t[(M)edium]\t[(H)ard]";
		String [] msgs = {BCKMSG, LEFMSG, CTRMSG, RGTMSG, UPMSG, DWNMSG};
		int choice, slow = 0, med = 1, fast = 2, dev = 3, textchoice = dev;
		int [] textspeed = {120, 90, 60, 0};
		
		slowPrint ("Welcome to Do Not Die, 1st Edition!!\n", textspeed [textchoice]);
		slowPrint ("What would you like to do?\n[(C)hoose Character]   [C(H)oose Map]   [(S)ettings]\n> ", textspeed [textchoice]);
		while (inputvalid (input = input ()))
		{
			if (input.equals ("c"))
			{
				p1 = chooseChar (textchoice);
				break;
			}
			/*else if (input.equals ("h"))
				chooseMap (map);
			else if (input.equals ("s"))
				changeSettings ();*/
			else
				slowPrint ("\nNot a choice, please retry:", textspeed [textchoice]);
		}
		
		System.out.println (input);
		
		slowPrint ("Set Difficulty:\n" + DIFFICULTY, textspeed [textchoice]);
		input = input ().toLowerCase ();
		
		while (inputvalid (input) && !p1.isDead() && !map.allCleared ())
		{
			// Fight enemies in the room before you can do anything else
			if (map.current.numenemies > 0 && !map.current.roomCleared ())
			{
				if (map.current.numenemies > 1)
					slowPrint ("There are " + map.current.numenemies +
							" enemies in the room, prepare to fight.", textspeed [textchoice]);
				else
					slowPrint ("There is an enemy in the room, prepare to fight.\n", textspeed [textchoice]);
				
				battle (p1, map);
				p1.setHealth (0);
				continue;
			}
			
			
			prompt = BASEMSG;
			for (int i = 0; i < map.current.connections.length; i++)
			{
				if (map.current.connections [i] >= 0)
					prompt += msgs [i];
			}
			
			slowPrint (prompt + "\n> ", textspeed [textchoice]);
			input = input ().toLowerCase ();
			
			if (!inputvalid (input))
			{
				input = null;
				continue;
			}
			
			switch (input)
			{
				case ("i"):
				{
					if (map.current.hasTreasures ())
					{
						slowPrint ("Treasures:\n", textspeed [textchoice]);
						map.current.listTreasures ();
						slowPrint ("What do you want to pick up?\n> ", textspeed [textchoice]);
						while (inputvalid (input = input()))
						{
							try
							{
								choice = Integer.parseInt (input);
								if (map.current.hasTreasure (choice))
								{
									p1.addToInventory (map.current.getTreasure (choice));
									break;
								}
								else
								{
									slowPrint ("That's not an item you can pick up.\n", textspeed [textchoice]);
									map.current.listTreasures ();
									slowPrint ("What do you want to pick up?\n> ", textspeed [textchoice]);
								}
							}
							catch (NumberFormatException e)
							{
								if (map.current.hasTreasure (input))
								{
									p1.addToInventory (map.current.getTreasure (input));
									break;
								}
								else
								{
									slowPrint ("That's not an item you can pick up.\n", textspeed [textchoice]);
									map.current.listTreasures ();
									slowPrint ("What do you want to pick up?\n> ", textspeed [textchoice]);
								}
							}
						}
					}
					else
						slowPrint ("Nothing in the room.", textspeed [textchoice]);
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
						slowPrint ("Not a valid action.", textspeed [textchoice]);
					break;
				}
				case ("l"):
				{
					if (map.current.connections [1] >= 0)
						map.moveLeft ();
					else
						slowPrint ("Not a valid action.", textspeed [textchoice]);
					break;
				}
				case ("f"):
				{
					if (map.current.connections [2] >= 0)
						map.moveCenter ();
					else
						slowPrint ("Not a valid action.", textspeed [textchoice]);
					break;
				}
				case ("r"):
				{
					if (map.current.connections [3] >= 0)
						map.moveRight ();
					else
						slowPrint ("Not a valid action.", textspeed [textchoice]);
					break;
				}
				case ("u"):
				{
					if (map.current.connections [4] >= 0)
						map.moveUp ();
					else
						slowPrint ("Not a valid action.", textspeed [textchoice]);
					break;
				}
				case ("d"):
				{
					if (map.current.connections [5] >= 0)
						map.moveDown ();
					else
						slowPrint ("Not a valid action.", textspeed [textchoice]);
					break;
				}
				default:
				{
					slowPrint ("Not a valid action.", textspeed [textchoice]);
					break;
				}
			}
		}
	}
	
	static Character chooseChar (int len)
	{
		String input, name = "", racestr = "";
		int race = 0, Str = 0, End = 0, Int = 0, Wil = 0, Agl = 0, Spd = 0, Lck = 0, statpoints = 10;
		Boolean gender = true;
		
		slowPrint ("What would you like to do?\n[(N)ew Character]   [(C)hoose Character]   [(D)elete Character]\n> ", len);
		
		while (inputvalid (input = input ().toLowerCase ()))
		{
			switch (input)
			{
				case ("n"):
				{
					slowPrint ("New Character, Ok.\n", len);
					slowPrint ("Let's go through the steps.\nName:\n> ", len);
					input = input ();
					if (inputvalid (input))
						name = input;
					slowPrint ("Um, " + name + "? You sure? Ok, well whatever, you're the player...\n\n", len);
					
					slowPrint ("Ok, Race:\n", len);
					
					System.out.print ("0:       Human\n1:         Elf\n2:         Orc\n3:       Gnome\n4:       Dwarf\n" +
					"5:  Dragonborn\n6:  Half-Troll\n7: Lizard-Folk\n8:    Cat-Folk\n9:    Tiefling\n> ");
					input = input ().toLowerCase ();
					
					while (inputvalid (input))
						if (input.equals ("human") || input.equals ("0"))
						{
							racestr = "Human";
							race = 0;
							break;
						}
						else if (input.equals ("elf") || input.equals ("1"))
						{
							racestr = "Elf";
							race = 1;
							break;
						}
						else if (input.equals ("orc") || input.equals ("2"))
						{
							racestr = "Orc";
							race = 2;
							break;
						}
						else if (input.equals ("gnome") || input.equals ("3"))
						{
							racestr = "Gnome";
							race = 3;
							break;
						}
						else if (input.equals ("dwarf") || input.equals ("4"))
						{
							racestr = "Dwarf";
							race = 4;
							break;
						}
						else if (input.equals ("dragonborn") || input.equals ("5"))
						{
							racestr = "Dragonborn";
							race = 5;
							break;
						}
						else if (input.equals ("half-troll") || input.equals ("6"))
						{
							racestr = "Half-Troll";
							race = 6;
							break;
						}
						else if (input.equals ("lizard-folk") || input.equals ("7"))
						{
							racestr = "Lizard-Folk";
							race = 7;
							break;
						}
						else if (input.equals ("cat-folk") || input.equals ("8"))
						{
							racestr = "Cat-Folk";
							race = 8;
							break;
						}
						else if (input.equals ("tiefling") || input.equals ("9"))
						{
							racestr = "Tiefling";
							race = 9;
							break;
						}
						else
						{
							slowPrint ("Nope. That's not a race. Try again:\n> ", len);
							input = input ().toLowerCase ();
						}
					slowPrint ("Oh, " + racestr + " huh?\nI kinda thought so, but " +
							"I wanted to make sure.\n\n", len);
					
					slowPrint ("So um, what's.... uh, what's your gender?:\n", len);
					System.out.print ("0: Male\n1: Female\n> ");
					input = input ().toLowerCase ();
					
					while (inputvalid (input))
						if (input.equals ("male") || input.equals ("0"))
						{
							gender = true;
							break;
						}
						else if (input.equals ("female") || input.equals ("1"))
						{
							gender = false;
							break;
						}
						else
						{
							slowPrint ("We're not that inclusive. Only male or female.\n> ", len);
							input = input ().toLowerCase ();
						}
					
					if (gender)
						slowPrint ("You're a dude?\nOk, Ok, yes you do look manly, I didn't want to assume.\n\n", len);
					else
						slowPrint ("You're a dudette?\nNo, you don't look too manly, I just didn't want to assume.\n\n", len);
					
					slowPrint ("Ok, now you have to allocate stat points.\nYou get 10 points to" +
							" use across all 7 stats, be wise.\n", len);
					System.out.println ("    Strength\n   Endurance\nIntelligence\n    Willpower\n     Agility\n       Speed\n        Luck");
					
					while (statpoints != 0)
					{
						slowPrint ("Strength:\n> ", len);
						input = input ();
						
						try
						{
							Str = Integer.parseInt (input);
							statpoints -= Str;
						}
						catch (NumberFormatException e)
						{
							slowPrint ("That's not a number, can you put a number?\nSpecifically" +
									" an integer less than or equal to the number of stat points left?\n", len);
							continue;
						}
						
						slowPrint ("Endurance:\n> ", len);
						input = input ();
						
						try
						{
							End = Integer.parseInt (input);
							if (statpoints >= End)
								statpoints -= End;
							else
							{
								statpoints = 10;
								slowPrint ("Um, that's more points than you have...\n" +
								"You spent too many points on Strength already.\nSTART OVER!\n", len);
								continue;
							}
						}
						catch (NumberFormatException e)
						{
							slowPrint ("That's not a number, can you put in a number?\nSpecifically" +
									" an integer less than or equal to the number of stat points left?\n", len);
							statpoints = 10;
							continue;
						}
						
						slowPrint ("Intelligence:\n> ", len);
						input = input ();
						
						try
						{
							Int = Integer.parseInt (input);
							if (statpoints >= Int)
								statpoints -= Int;
							else
							{
								statpoints = 10;
								slowPrint ("Um, that's more points than you have... START OVER!\n", len);
								continue;
							}
						}
						catch (NumberFormatException e)
						{
							slowPrint ("That's not a number, can you put in a number?\nSpecifically" +
									" an integer less than or equal to the number of stat points left?\n", len);
							statpoints = 10;
							continue;
						}
						
						slowPrint ("Willpower:\n> ", len);
						input = input ();
						
						try
						{
							Wil = Integer.parseInt (input);
							if (statpoints >= Wil)
								statpoints -= Wil;
							else
							{
								statpoints = 10;
								slowPrint ("Um, that's more points than you have... START OVER!\n", len);
								continue;
							}
						}
						catch (NumberFormatException e)
						{
							slowPrint ("That's not a number, can you put in a number?\nSpecifically" +
									" an integer less than or equal to the number of stat points left?\n", len);
							statpoints = 10;
							continue;
						}
						
						slowPrint ("Agility:\n> ", len);
						input = input ();
						
						try
						{
							Agl = Integer.parseInt (input);
							if (statpoints >= Agl)
								statpoints -= Agl;
							else
							{
								statpoints = 10;
								slowPrint ("Um, that's more points than you have... START OVER!\n", len);
								continue;
							}
						}
						catch (NumberFormatException e)
						{
							slowPrint ("That's not a number, can you put in a number?\nSpecifically" +
									" an integer less than or equal to the number of stat points left?\n", len);
							statpoints = 10;
							continue;
						}
						
						slowPrint ("Speed:\n> ", len);
						input = input ();
						
						try
						{
							Spd = Integer.parseInt (input);
							if (statpoints >= Spd)
								statpoints -= Spd;
							else
							{
								statpoints = 10;
								slowPrint ("Um, that's more points than you have... START OVER!\n", len);
								continue;
							}
						}
						catch (NumberFormatException e)
						{
							slowPrint ("That's not a number, can you put in a number?\nSpecifically" +
									" an integer less than or equal to the number of stat points left?\n", len);
							statpoints = 10;
							continue;
						}
						
						slowPrint ("Luck:\n> ", len);
						input = input ();
						
						try
						{
							Lck = Integer.parseInt (input);
							if (statpoints >= Lck)
								statpoints -= Lck;
							else
							{
								statpoints = 10;
								slowPrint ("Um, that's more points than you have... START OVER!\n", len);
								continue;
							}
						}
						catch (NumberFormatException e)
						{
							slowPrint ("That's not a number, can you put in a number?\nSpecifically" +
									" an integer less than or equal to the number of stat points left?\n", len);
							statpoints = 10;
							continue;
						}
						
						if (statpoints > 0)
							slowPrint ("Um, you have " + statpoints + " point(s) left... " +
									"Why didn't you use them?\nStart over, you scrub, and use " +
									"all your points, that's why you get them!\n", len);
					}
					
					return new Character (name, race, gender, Str, End, Int, Wil, Agl, Spd, Lck, 1);
				}
				case ("c"):
				{
					slowPrint ("Select Which Character?\n", len);
					input = null;
					break;
				}
				case ("d"):
				{
					slowPrint ("Delete Which Character?\n", len);
					input = null;
					break;
				}
				default:
				{
					slowPrint ("Invalid Input, please retry\n", len);
					slowPrint ("What would you like to do?\n[(N)ew Character]   [(C)hoose Character]   [(D)elete Character]\n> ", len);
					break;
				}
			}
			
			System.out.print ("Ok, what do you want to do now?\n[(N)ew Character]   " +
					"[(C)hoose Character]   [(D)elete Character]\n> ");
		}
		return null;
	}
	
	// Takes in the player, and the current map.
	// Returns true if the player died
	static Boolean battle (Character p1, Map map)
	{
		Character enemy = null, hold = null;
		Character [] order = new Character [map.current.numenemies + 1];
		Character [] aselect = new Character [map.current.numenemies + 1];
		int turn = 0, roll = 0, aselected, maxinit, infiniteloopstopper = 0, slow = 0, med = 1, fast = 2, dev = 3, textchoice = dev;
		int [] textspeed = {120, 90, 60, 0};
		String input = "";
		
		// Put player and enemies into a priority queue based on initiative
		slowPrint ("You rolled " + p1.initiative () + " for initiative.", textspeed [textchoice]);
		order [0] = p1;
		
		for (int i = 0; i < map.current.numenemies; i++)
		{
			if (map.current.numenemies > 1)
				slowPrint (map.current.enemies[i].getRace() + " " + i + " rolled " +
			map.current.enemies [i].initiative () + " for initiative.", textspeed [textchoice]);
			else
				slowPrint (map.current.enemies [i].getRace() + " rolled " +
			map.current.enemies [i].initiative () + " for initiative.", textspeed [textchoice]);
			
			order [i + 1] = map.current.enemies [i];
		}
		
		for (int i = 0; i <= map.current.numenemies; i++)
		{
			maxinit = i;
			for (int j = i; j <= map.current.numenemies && order [j] != null; j++)
			{
				if (order [j].compareTo (order [maxinit]) > 0)
					maxinit = j;
			}
			hold = order [i];
			order [i] = order [maxinit];
			order [maxinit] = hold;
		}
		
		hold = null;
		
		for (int i = 0; i < map.current.numenemies - 1; i++)
		{
			if (order [i].getName () == null)
				aselect [i] = order [i];
			else if (order [i].getName () != null)
			{
				aselect [i] = order [i + 1];
				i++;
			}
		}
		
		// Removing player from order, to allow for easier target select
		for (int i = 0; i < aselect.length - 1 && aselect [i] != null; i++)
		{
			if (!(aselect [i].getName () == null && hold == null))
			{
				hold = aselect [i];
				aselect [i] = aselect [i + 1];
				aselect [i + 1] = hold;
			}
		}
		aselect [aselect.length - 1] = null;
		
		
		slowPrint ("\nThe order is: ", textspeed [textchoice]);
		
		// Print order
		for (int i = 0; i < order.length && order [i] != null; i++)
			if (order [i].getName () != null)
				slowPrint (order [i].getName (), textspeed [textchoice]);
			else
				slowPrint (order [i].getRace (), textspeed [textchoice]);
		
		
		// Battle manager
		while (!p1.isDead () && !map.current.roomCleared () && ++infiniteloopstopper < 25)
		{
			if (turn >= order.length || order [turn] == null)
			{
				slowPrint ("\nTop of the order again.\n", textspeed [textchoice]);
				turn = 0;
			}
			
			input = input.toLowerCase ();
			
			if (order [turn].getName () != null)
			{
				while (!input.equals ("a") && !input.equals ("attack"))
				{
					slowPrint ("\nIt's your turn, what do you want to do?\n[(A)ttack][(C)heck Bag]", textspeed [textchoice]);
					input = input ().toLowerCase ();
					if (input.equals ("a") || input.equals ("attack"))
					{
						slowPrint ("Attack who?", textspeed [textchoice]);
						map.current.enemiesAlive ();
						/*for (int i = 0; i < aselect.length && aselect [i] != null; i++)
							if (aselect [i].getHealth () > 0)
								slowPrint (i + ": " + aselect [i].printEnemy ());*/
						
						while (inputvalid (input = input ().toLowerCase ()))
						{
							try
							{
								aselected = Integer.parseInt (input);
								
								if (aselected >= aselect.length || aselect [aselected] == null)
								{
									slowPrint ("Number not valid. Can you like be nice please?\n", textspeed [textchoice]);
									for (int i = 0; i < aselect.length && aselect [i] != null; i++)
										if (!map.current.enemyDead (i))
											slowPrint (i + ": " + aselect [i].printEnemy (), textspeed [textchoice]);
								}
								else
								{
									enemy = aselect [aselected];
									break;
								}
							}
							catch (NumberFormatException e)
							{
								aselected = map.current.hasEnemy (input);
								if (aselected < 0)
								{
									slowPrint ("That's not an enemy. Play nice.\n", textspeed [textchoice]);
									for (int i = 0; i < aselect.length && aselect [i] != null; i++)
										if (!map.current.enemyDead (i))
											slowPrint (i + ": " + aselect [i].printEnemy (), textspeed [textchoice]);
								}
								else
								{
									enemy  = aselect [aselected];
									break;
								}
							}
						}
						
						roll = p1.rolld20 ();
						slowPrint ("\nYou rolled " + roll + " verses the " + enemy.getRace () + "'s AC", textspeed [textchoice]);
						if (roll > enemy.getAC ())
						{
							slowPrint ("Attacked for " + p1.getDamage (), textspeed [textchoice]);
							if (enemy.attacked (p1.getDamage ()))
							{
								slowPrint ("\n\nThe weapon swung true\nThe " +
							enemy.getRace () + " fell\nA fatal blow\nTo the left pinky toe.\n", textspeed [textchoice]);
							}
							else
								slowPrint (enemy.getRace () + " has " + enemy.getHealth () + " health left.", textspeed [textchoice]);
						}
						else
							slowPrint ("That ain't gonna cut it.", textspeed [textchoice]);
						input = "";
						break;
					}
					else if (input.equals ("c") || input.equals ("check") || input.equals ("check bag"))
						p1.inventoryCheck ();
					else
						slowPrint ("Invalid input", textspeed [textchoice]);
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
					slowPrint ("\nIt's " + order [turn].getRace () + "'s turn.", textspeed [textchoice]);
			}
			
			if (((turn + 1) > order.length))
			{
				slowPrint ("\nTop of the order again.", textspeed [textchoice]);
				turn = 0;
			}
			else
				turn++;
		}
		
		return p1.isDead ();
	}
	
	// Print things out in a slow, epic way
	public static void slowPrint (String str, int len)
	{
		for (int i = 0; i < str.length (); i++)
		{
			System.out.print (str.charAt (i));
			
			try { TimeUnit.MILLISECONDS.sleep (len); }
			catch (InterruptedException e) { e.printStackTrace(); }
		}
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
 	
 	// Checks if input is any exit keyword
 	// returns true if not
 	static Boolean inputvalid (String input)
 	{
 		if (input == null)
 			return false;
 		input = input.toLowerCase ();
 		return !(input.equals ("no") || input.equals ("nothing") || input.equals ("q") ||
 				input.equals ("quit") || input.equals ("cancel") || input.equals ("exit"));
 	}
}
