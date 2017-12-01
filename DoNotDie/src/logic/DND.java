package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

public class DND
{
	public static void main (String [] args)
	{
		Map map = new Map ();
		Character p1 = new Character ("Chaos", 0, true, 200, 2, 2, 2, 2, 2, 2, 1);
		String input = "";
		String BASEMSG = "\nWhat do you want to do?\n[(I)nspect]   [I(N)ventory]   [(S)ee Stats]", prompt = "";
		String RGTMSG = "   [Move (R)ight]", LEFMSG = "   [Move (L)eft]";
		String UPMSG = "   [Move (U)p]", DWNMSG = "   [Move (D)own]";
		String CTRMSG = "   [Move (F)orward]", BCKMSG = "   [Move (B)ackward]";
		String DIFFICULTY = "[(E)asy]\t[(M)edium]\t[(H)ard]";
		String [] msgs = {BCKMSG, LEFMSG, CTRMSG, RGTMSG, UPMSG, DWNMSG};
		int choice, slow = 0, med = 1, fast = 2, dev = 3, textchoice = dev;
		int [] textspeeds = {120, 90, 40, 0};
		
		slowPrint ("Welcome to Do Not Die, 1st Edition!!\n\n", textspeeds [textchoice]);
		slowPrint ("What would you like to do?\n", textspeeds [textchoice]);
		System.out.print ("[(C)hoose Character]   [C(H)oose Map]   [(S)ettings]\n> ");
		
		while (inputvalid (input = input ().toLowerCase ()))
		{
			if (input.equals ("c"))
			{
				p1 = chooseChar (textchoice);
				break;
			}
			else if (input.equals ("h"))
			{
				System.out.println ("Currently unimplemented.");
			}
			//	chooseMap (map);
			else if (input.equals ("s"))
			{
				textchoice = changeSettings (textchoice);
				slowPrint ("Ok, now what do you want to do?\n", textspeeds [textchoice]);
				System.out.print ("[(C)hoose Character]   [C(H)oose Map]   [(S)ettings]\n> ");
			}
			else if (input.equals ("dev"))
				break;
			else
				slowPrint ("\nNot a choice, please retry:", textspeeds [textchoice]);
		}
		
		/*slowPrint ("Set Difficulty:\n" + DIFFICULTY + "\n> ", textspeeds [textchoice]);
		input = input ().toLowerCase ();*/
		
		while (inputvalid (input) && !p1.isDead() && !map.allCleared ())
		{
			// Fight enemies in the room before you can do anything else
			if (map.current.numenemies > 0 && !map.current.roomCleared ())
			{
				if (map.current.numenemies > 1)
					slowPrint ("There are " + map.current.numenemies +
							" enemies in the room, prepare to fight.\n", textspeeds [textchoice]);
				else
					slowPrint ("There is an enemy in the room, prepare to fight.\n", textspeeds [textchoice]);
				
				battle (p1, map);
			}
			
			
			prompt = BASEMSG;
			for (int i = 0; i < map.current.connections.length; i++)
			{
				if (map.current.connections [i] >= 0)
					prompt += msgs [i];
			}
			
			slowPrint (prompt + "\n> ", textspeeds [textchoice]);
			input = input ().toLowerCase ();
			
			if (!inputvalid (input))
			{
				input = null;
				continue;
			}
			
			switch (input)
			{
				case ("s"):
				{
					slowPrint (p1.printStats (), textspeeds [textchoice]);
					break;
				}
				case ("i"):
				{
					if (map.current.hasTreasures ())
					{
						slowPrint ("Treasures:\n", textspeeds [textchoice]);
						map.current.listTreasures ();
						slowPrint ("What do you want to pick up?\n> ", textspeeds [textchoice]);
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
									slowPrint ("That's not an item you can pick up.\n", textspeeds [textchoice]);
									map.current.listTreasures ();
									slowPrint ("What do you want to pick up?\n> ", textspeeds [textchoice]);
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
									slowPrint ("That's not an item you can pick up.\n", textspeeds [textchoice]);
									map.current.listTreasures ();
									slowPrint ("What do you want to pick up?\n> ", textspeeds [textchoice]);
								}
							}
						}
					}
					else
						slowPrint ("Nothing in the room.", textspeeds [textchoice]);
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
						slowPrint ("Not a valid action.", textspeeds [textchoice]);
					break;
				}
				case ("l"):
				{
					if (map.current.connections [1] >= 0)
						map.moveLeft ();
					else
						slowPrint ("Not a valid action.", textspeeds [textchoice]);
					break;
				}
				case ("f"):
				{
					if (map.current.connections [2] >= 0)
						map.moveCenter ();
					else
						slowPrint ("Not a valid action.", textspeeds [textchoice]);
					break;
				}
				case ("r"):
				{
					if (map.current.connections [3] >= 0)
						map.moveRight ();
					else
						slowPrint ("Not a valid action.", textspeeds [textchoice]);
					break;
				}
				case ("u"):
				{
					if (map.current.connections [4] >= 0)
						map.moveUp ();
					else
						slowPrint ("Not a valid action.", textspeeds [textchoice]);
					break;
				}
				case ("d"):
				{
					if (map.current.connections [5] >= 0)
						map.moveDown ();
					else
						slowPrint ("Not a valid action.", textspeeds [textchoice]);
					break;
				}
				default:
				{
					slowPrint ("Not a valid action.", textspeeds [textchoice]);
					break;
				}
			}
		}
	}
	
	static Character chooseChar (int len)
	{
		String input;
		int choice;
		Character player = null;
		
		slowPrint ("\nWhat would you like to do?\n[(N)ew Character]   [(C)hoose Character]   [(D)elete Character]\n> ", len);
		
		while (inputvalid (input = input ().toLowerCase ()))
		{
			switch (input)
			{
				case ("n"):
				{
					if (loadChar (2) == null)
						player = newChar (len);
					else
						System.out.println ("Sorry, our benevolent overlords have imposed a" +
								" limit of 3 saved characters.\nTry deleting one or all first. :)\n");
					break;
				}
				case ("c"):
				{
					if (loadChar (0) != null)
					{
						slowPrint ("\nSelect Which Character?\n", len);
					
						// Print out first 3 characters, because 3 should be max
						for (int i = 0; i < 3; i++)
						{
							player = loadChar (i);
							if (player != null)
								System.out.println (i + ": " + player);
						}
						System.out.print ("> ");
						
						// Let them choose the character, 0, 1, or 2
						while (input != null)
						{
							input = input ().toLowerCase ();
							try
							{
								choice = Integer.parseInt (input);
							}
							catch (NumberFormatException e)
							{
								slowPrint ("\nThat's not a number, input a valid selection\n> ", len);
								continue;
							}
							
							if (choice == 0 && loadChar (0) != null)
							{
								player = loadChar (0);
								input = null;
							}
							else if (choice == 1 && loadChar (1) != null)
							{
								player = loadChar (1);
								input = null;
							}
							else if (choice == 2 && loadChar (2) != null)
							{
								player = loadChar (2);
								input = null;
							}
							else
							{
								slowPrint ("\nThat's not a valid choice.\n> ", len);
							}
						}
						
						
						System.out.println ("You loaded: " + player);
						/*if (player != null)
							*/return player;
					}
					else
					{
						slowPrint ("\nSorry, there are no saved characters.\nLet me point you in the right direction:\n\n", len);
						return newChar (len);
					}
				}
				case ("d"):
				{
					if (loadChar (0) == null)
					{
						slowPrint ("Ummm, you have no characters to delete.\nMake some first, then you can delete them.\n\n", len);
						break;
					}
					slowPrint ("\nDelete Which Character?\n", len);
					
					// Print out first 3 characters, because 3 should be max
					for (int i = 0; i < 3; i++)
					{
						player = loadChar (i);
						if (player != null)
							System.out.println (i + ": " + player);
					}
					System.out.print ("> ");
					
					// Let them choose the character, 0, 1, or 2
					while (input != null)
					{
						input = input ().toLowerCase ();
						try
						{
							choice = Integer.parseInt (input);
						}
						catch (NumberFormatException e)
						{
							slowPrint ("\nThat's not a number, input a valid selection\n> ", len);
							continue;
						}
						
						if (choice == 0 && loadChar (0) != null)
						{
							player = loadChar (0);
							input = null;
						}
						else if (choice == 1 && loadChar (1) != null)
						{
							player = loadChar (1);
							input = null;
						}
						else if (choice == 2 && loadChar (2) != null)
						{
							player = loadChar (2);
							input = null;
						}
						else
						{
							slowPrint ("\nThat's not a valid choice.\n> ", len);
						}
					}
					
					
					System.out.println ("You deleted: " + player);
					delChar (player);
					break;
				}
				case ("dev"):
				{
					return new Character ("Chaos", 0, true, 200, 2, 2, 2, 2, 2, 2, 2);
				}
				case ("b"):
				{
					return player;
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
	// Create new Character process
	static Character newChar (int len)
	{
		String input, name = "", racestr = "";
		int race = 0, Str = 0, End = 0, Int = 0, Wil = 0, Agl = 0, Spd = 0, Lck = 0, statpoints = 10;
		Boolean gender = true, retry = true;
		Character player;
		
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
		System.out.println ("Strength\nEndurance\nIntelligence\nWillpower\nAgility\nSpeed\nLuck\n");
		
		while (statpoints != 0 && retry)
		{
			slowPrint ("Strength:\n> ", len);
			input = input ();
			
			try
			{
				Str = Integer.parseInt (input);
				if (statpoints >= Str)
					statpoints -= Str;
				else
				{
					statpoints = 10;
					slowPrint ("Um, that's more points than you have... START OVER!\n", len);
					continue;
				}
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
						"Why didn't you use them you scrub?\nThat's what they're for!\n", len);
			
			slowPrint ("Are you sure you're good with this?\n", len);
			System.out.println (String.format ("%14s%2d", "Strength: ", Str));
			System.out.println (String.format ("%14s%2d", "Endurance: ", End));
			System.out.println (String.format ("%14s%2d", "Intelligence: ", Int));
			System.out.println (String.format ("%14s%2d", "Willpower: ", Wil));
			System.out.println (String.format ("%14s%2d", "Agility: ", Agl));
			System.out.println (String.format ("%14s%2d", "Speed: ", Spd));
			System.out.print (String.format ("%14s%2d\n> ", "Luck: ", Lck));
			
			
			if ((input = input ().toLowerCase ()).equals ("y") || input.equals ("yes"))
				retry = false;
			else
				statpoints = 10;
		}
		
		player = new Character (name, race, gender, Str, End, Int, Wil, Agl, Spd, Lck, 1);
		
		saveChar (player);
		
		return player;
	}
	// Output Character to the txt file, if the max of 3 saved characters is not met
	static void saveChar (Character c)
	{
		if (loadChar (2) != null)
			System.out.println ("Sorry, maximum of 3 saved characters.\nDelete one first.\n");
		else
		{
			System.out.println ("Saving character\n");
			try
			{ Files.write (Paths.get ("saved_chars.txt"), c.printCharacter ().getBytes (), StandardOpenOption.APPEND); }
			catch (IOException e)
			{
				PrintWriter out;
				try
				{
					out = new PrintWriter (new BufferedWriter (new FileWriter (new File ("saved_chars.txt"))));
					out.write (c.printCharacter (), 0, c.printCharacter ().length ());
					out.close ();
				}
				catch (IOException g)
				{ System.out.println ("Error saving character, you can play, but will have to make again."); }
			}
		}
	}
	// Load Character from the txt file, if there
	static Character loadChar (int num)
	{
		Path save = Paths.get ("saved_chars.txt");
		Character load = null;

		num *= 11;
		
		try
		{
			for (int i = 0; i <= num; )
			{
				if (input (save.toFile (), i) != null)
				{
					load = new Character (input (save.toFile (), i), Integer.parseInt (input (save.toFile (), i + 1)),
						Boolean.parseBoolean (input (save.toFile (), i + 2)), Integer.parseInt (input (save.toFile (), i + 3)),
						Integer.parseInt (input (save.toFile (), i + 4)), Integer.parseInt (input (save.toFile (), i + 5)),
						Integer.parseInt (input (save.toFile (), i + 6)), Integer.parseInt (input (save.toFile (), i + 7)),
						Integer.parseInt (input (save.toFile (), i + 8)), Integer.parseInt (input (save.toFile (), i + 9)),
						Integer.parseInt (input (save.toFile (), i + 10)));
					i += 11;
				}
				else
					return null;
			}
		}
		catch (IOException e)
		{
			System.out.println ("Sorry, your computer has decided it doesn't enjoy files.\n");
		}
		
		return load;
	}
	// Will delete a Character from the txt file
	static void delChar (Character c)
	{
		Character [] hold = new Character [3];
		int rem = 2;
		PrintWriter out = null;
		
		for (int i = 0; i < 3; i++)
		{
			hold [i] = loadChar (i);
			if (hold [i] != null && Character.areEqual (c, hold [i]))
			{
				rem = i;
				//System.out.println ("setting char to delete to: "+hold[i]);
			}
		}
		
		try
		{
			out = new PrintWriter ("saved_chars.txt", "UTF-8");
		}
		catch (FileNotFoundException | UnsupportedEncodingException e)
		{
			System.out.println ("Error with file");
			e.printStackTrace ();
			return;
		}
		
		for (int i = 0; i < 3; i++)
		{
			if (hold [i] != null && i != rem)
			{
				out.print (hold [i].printCharacter ());
				//System.out.println ("Outputted char");
			}
		}
		
		out.close ();
	}
	
	// Takes in the player, and the current map.
	// Returns true if the player died
	static Boolean battle (Character p1, Map map)
	{
		Character enemy = null, hold = null;
		Character [] order = new Character [map.current.numenemies + 1];
		Character [] ordercpy = new Character [map.current.numenemies + 1];
		Character [] aselect = new Character [map.current.numenemies];
		int turn = 0, roll = 0, aselected, maxinit, infiniteloopstopper = 0;
		int slow = 0, med = 1, fast = 2, dev = 3, textchoice = dev;
		int [] textspeeds = {120, 90, 60, 0};
		String input = "";
		
		// Put player and enemies into a priority queue based on initiative
		slowPrint ("You rolled " + p1.initiative () + " for initiative.\n", textspeeds [textchoice]);
		order [0] = p1;
		
		for (int i = 0; i < map.current.numenemies; i++)
		{
			if (map.current.numenemies > 1)
				slowPrint (map.current.enemies[i].getRace() + " " + i + " rolled " +
			map.current.enemies [i].initiative () + " for initiative.\n", textspeeds [textchoice]);
			else
				slowPrint ("The " + map.current.enemies [i].getRace() + " rolled " +
			map.current.enemies [i].initiative () + " for initiative.\n", textspeeds [textchoice]);
			
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
			ordercpy [i] = order [i];
		}
		
		hold = null;
		
		
		// Removing player from order, to allow for easier target select
		for (int i = 0; i < ordercpy.length - 1 && ordercpy [i] != null; i++)
		{
			if (!(ordercpy [i].getName () == null && hold == null))
			{
				hold = ordercpy [i];
				ordercpy [i] = ordercpy [i + 1];
				ordercpy [i + 1] = hold;
			}
			if (i < aselect.length)
				aselect [i] = ordercpy [i];
		}
		
		
		slowPrint ("\nThe order is: \n", textspeeds [textchoice]);
		
		// Print order
		for (int i = 0; i < order.length && order [i] != null; i++)
			if (order [i].getName () != null)
				slowPrint (order [i].getName () + "\n", textspeeds [textchoice]);
			else
				slowPrint (order [i].getRace () + "\n", textspeeds [textchoice]);
		
		// Battle manager
		while (!p1.isDead () && !map.current.roomCleared ())
		{
			if (turn >= order.length || order [turn] == null)
			{
				slowPrint ("\nTop of the order again.\n", textspeeds [textchoice]);
				turn = 0;
			}
			
			input = input.toLowerCase ();
			
			if (order [turn].getName () != null)
			{
				while (!input.equals ("a") && !input.equals ("attack"))
				{
					slowPrint ("\nIt's your turn, what do you want to do?\n[(A)ttack]   [(C)heck Bag]   [(P)erception Check]\n> ", textspeeds [textchoice]);
					input = input ().toLowerCase ();
					if (input.equals ("a") || input.equals ("attack"))
					{
						slowPrint ("Attack who?\n", textspeeds [textchoice]);
						
						for (int i = 0; i < aselect.length && aselect [i] != null; i++)
							if (!map.current.enemyDead (aselect [i]))
								slowPrint (i + ": " + aselect [i].printEnemy () + "\n", textspeeds [textchoice]);
						
						while (inputvalid (input = input ().toLowerCase ()))
						{
							try
							{
								aselected = Integer.parseInt (input);
								
								if (aselected >= aselect.length || aselect [aselected] == null)
								{
									slowPrint ("Number not valid. Can you like be nice please?\n", textspeeds [textchoice]);
									for (int i = 0; i < aselect.length && aselect [i] != null; i++)
										if (!map.current.enemyDead (aselect [i]))
											slowPrint (i + ": " + aselect [i].printEnemy () + "\n", textspeeds [textchoice]);
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
									slowPrint ("That's not an enemy. Play nice.\n", textspeeds [textchoice]);
									for (int i = 0; i < aselect.length && aselect [i] != null; i++)
										if (!map.current.enemyDead (aselect [i]))
											slowPrint (i + ": " + aselect [i].printEnemy () + "\n", textspeeds [textchoice]);
								}
								else
								{
									enemy  = aselect [aselected];
									break;
								}
							}
						}
						
						roll = p1.rolld20 ();
						slowPrint ("\nYou rolled " + roll + " verses the " + enemy.getRace () + "'s AC\n", textspeeds [textchoice]);
						if (roll > enemy.getAC ())
						{
							slowPrint ("Attacked for " + p1.getDamage () + "\n", textspeeds [textchoice]);
							if (enemy.attacked (p1.getDamage ()))
							{
								slowPrint ("\n\nThe weapon swung true\nThe " +
							enemy.getRace () + " fell\nA fatal blow\nTo the left pinky toe.\n\n", textspeeds [textchoice]);
							}
							else
								slowPrint (enemy.getRace () + " has " + enemy.getHealth () + " health left.\n", textspeeds [textchoice]);
						}
						else
							slowPrint ("That ain't gonna cut it.\n", textspeeds [textchoice]);
						input = "";
						break;
					}
					else if (input.equals ("c") || input.equals ("check") || input.equals ("check bag"))
						p1.inventoryCheck ();
					else if (input.equals ("p") || input.equals ("perception check"))
						for (int i = 0; i < aselect.length; i++)
							aselect [i].printDescription (textspeeds [textchoice]);
					else
						slowPrint ("Invalid input.\n", textspeeds [textchoice]);
				}
			}
			else
			{
				if (map.current.enemyDead (order [turn]))
				{
					turn++;
					continue;
				}
				else
					slowPrint ("\nIt's " + order [turn].getRace () + "'s turn.", textspeeds [textchoice]);
			}
			
			if (((turn + 1) > order.length))
			{
				slowPrint ("\nTop of the order again.\n", textspeeds [textchoice]);
				turn = 0;
			}
			else
				turn++;
		}
		
		return p1.isDead ();
	}
	
	// Change text speed
	// So far only functionality
	static int changeSettings (int current)
	{
		int [] speeds = {120, 90, 40, 0};
		int choice = current;
		String input;
		
		slowPrint ("What do you want to change?\n", speeds [choice]);
		
		System.out.print ("[Text (Sp)eed]   [Text (Si)ze]\n> ");
		
		input = input ().toLowerCase ();
		
		if (input.equals ("sp"))
		{
			slowPrint ("Ok, what speed do you want?\n", speeds [choice]);
			System.out.print ("[(S)low]   [[(M)edium]   [(F)ast]\n> ");
			
			input = input ().toLowerCase ();
			if (input.equals ("s"))
				choice = 0;
			else if (input.equals ("m"))
				choice = 1;
			else if (input.equals ("f"))
				choice = 2;
			else if (input.equals ("d"))
				choice = 3;
			else
				slowPrint ("Not a valid choice", speeds [choice]);
		}
		else if (input.equals ("si"))
		{
			System.out.println ("Not implemented yet.");
		}
		
		return choice;
	}
	
	// Print things out in a slow, epic way
	public static void slowPrint (String str, int len)
	{
		for (int i = 0; i < str.length (); i++)
		{
			System.out.print (str.charAt (i));
			
			try { TimeUnit.MILLISECONDS.sleep (len); }
			catch (InterruptedException e) { System.out.println ("Oops, Java messed up."); }
		}
	}
	
	// Gets input from the user, and returns it as a String
 	static String input ()
	{
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		String ret = null;
		
		try { ret = br.readLine (); }
		catch (IOException e) { e.printStackTrace (); }
		
		return ret;
	}
 	// Gets input from a file, skipping a number of lines indicated by toskip
 	// Used by loadChar to read from text file
 	static String input (File f, int toskip) throws IOException
 	{
 		BufferedReader br = new BufferedReader (new FileReader (f));
 		String ret = br.readLine ();
 		
 		for (int i = 0; i < toskip; i++)
 			ret = br.readLine ();
 		
 		br.close ();
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