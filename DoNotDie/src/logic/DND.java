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
	// Main game driver. Makes calls to all other driver methods
	public static void main (String [] args)
	{
		Map map = new Map ();
		Character p1 = null;
		String input = "";
		String prompt = "";
		String BASEMSG = "\nWhat do you want to do?\n[(I)nspect]         [I(N)ventory]       " +
				"[(S)ee Stats]       [(Q)uit]\n";
		String RGTMSG = "[Move (R)ight]";
		String LEFMSG = "[Move (L)eft]";
		String UPMSG = "[Move (U)p]";
		String DWNMSG = "[Move (D)own]";
		String CTRMSG = "[Move (F)orward]";
		String BCKMSG = "[Move (B)ackward]";
		String DIFFICULTY = "[(E)asy]\t[(M)edium]\t[(H)ard]";
		String [] msgs = {BCKMSG, LEFMSG, CTRMSG, RGTMSG, UPMSG, DWNMSG};
		int choice;
		int textchoice = 3;
		int [] textspeeds = {120, 90, 40, 0};
		
		slowPrint ("Welcome to Do Not Die, 1st Edition!!\n" +
				"We hope you enjoy our game!! :D\n\n", textspeeds [textchoice]);
		
		// Main menu loop
		slowPrint ("\nWhat would you like to do?\n\n", textspeeds [textchoice]);
		slowPrint ("[(C)hoose Character]   [C(H)oose Map]   [(P)lay]\n[(S)ettings]           [(Q)uit]\n> ", 0);
		while (inputvalid (input = input ().toLowerCase ()))
		{
			if (input.equals ("c"))
			{
				// Choose Character
				p1 = chooseChar (textspeeds [textchoice], p1);
				slowPrint ("\nWhat would you like to do?\n\n", textspeeds [textchoice]);
				slowPrint ("[(C)hoose Character]   [C(H)oose Map]   [(P)lay]\n[(S)ettings]           [(Q)uit]\n> ", 0);
			}
			else if (input.equals ("h"))
			{
				// Choose Map
				slowPrint ("\nCurrently unimplemented\n\n", 0);
				slowPrint ("\nWhat would you like to do?\n\n", textspeeds [textchoice]);
				slowPrint ("[(C)hoose Character]   [C(H)oose Map]   [(P)lay]\n[(S)ettings]           [(Q)uit]\n> ", 0);
			}
			else if (input.equals ("s"))
			{
				// Change Settings
				textchoice = changeSettings (textchoice);
				slowPrint ("\nWhat would you like to do?\n\n", textspeeds [textchoice]);
				slowPrint ("[(C)hoose Character]   [C(H)oose Map]   [(P)lay]\n[(S)ettings]           [(Q)uit]\n> ", 0);
			}
			else if (input.equals ("p"))
			{
				// Play game
				if (p1 == null)
				{
					slowPrint ("\nHey, you need to choose your character!\n", textspeeds [textchoice]);
					p1 = chooseChar (textspeeds [textchoice], p1);
					break;
				}
				else
				{
					slowPrint ("All right! Ready to play?\n", textspeeds [textchoice]);
					break;
				}
			}
			else
				slowPrint ("\nNot a choice, please retry:\n> ", 0);
		}
		
		// Commented out until implemented or deadline is reached
		/*slowPrint ("Set Difficulty:\n" + DIFFICULTY + "\n> ", textspeeds [textchoice]);
		input = input ().toLowerCase ();*/
		
		// Main gameplay loop
		while (inputvalid (input) && !p1.isDead() && !map.allCleared ())
		{
			// Fight enemies in the room before you can do anything else
			if (map.current.numenemies > 0 && !map.current.roomCleared ())
			{
				if (map.current.numenemies > 1)
					slowPrint ("There are " + map.current.numenemies +
							" enemies in the room, prepare to fight.\n\n", textspeeds [textchoice]);
				else
					slowPrint ("There is an enemy in the room, prepare to fight.\n\n", textspeeds [textchoice]);
				
				battle (p1, map);
			}
			// Room is now cleared
			
			// Build string with all movement options and prompt user
			prompt = BASEMSG;
			for (int i = 0; i < map.current.connections.length; i++)
			{
				if (map.current.connections [i] >= 0)
					prompt += String.format ("%-20s", msgs [i]);
			}
			slowPrint (prompt + "\n> ", 0);
			input = input ().toLowerCase ();
			
			// Check if user wants to quit. If yes, exit loop, so game ends, if no, continue as normal
			if (!inputvalid (input))
				break;
			
			switch (input)
			{
				case ("s"):
				{
					// Print player stats
					slowPrint ("Stats:\n", textspeeds [textchoice]);
					slowPrint ("p1.printStats () + \n", 0);
					break;
				}
				case ("i"):
				{
					// Inspect room
					//map.current.printDescription ();
					// If room has treasures, list them and allow player to pick one up per inspect action
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
									slowPrint ("That's not an item you can pick up.\n" +
											"Here's what you can:\n", textspeeds [textchoice]);
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
									slowPrint ("That's not an item you can pick up.\n" +
											"Here's what you can:\n", textspeeds [textchoice]);
									map.current.listTreasures ();
									slowPrint ("What do you want to pick up?\n> ", textspeeds [textchoice]);
								}
							}
						}
					}
					else
						slowPrint ("Nothing in the room\n", textspeeds [textchoice]);
					break;
				}
				case ("n"):
				{
					p1.inventoryCheck ();
					break;
				}
				case ("b"):
				{
					// Only allow movement if the current room has this connection
					if (map.current.connections [0] >= 0)
						map.moveBack ();
					else
						slowPrint ("Not a valid action\n", textspeeds [textchoice]);
					break;
				}
				case ("l"):
				{
					// Only allow movement if the current room has this connection
					if (map.current.connections [1] >= 0)
						map.moveLeft ();
					else
						slowPrint ("Not a valid action\n", textspeeds [textchoice]);
					break;
				}
				case ("f"):
				{
					// Only allow movement if the current room has this connection
					if (map.current.connections [2] >= 0)
						map.moveCenter ();
					else
						slowPrint ("Not a valid action\n", textspeeds [textchoice]);
					break;
				}
				case ("r"):
				{
					// Only allow movement if the current room has this connection
					if (map.current.connections [3] >= 0)
						map.moveRight ();
					else
						slowPrint ("Not a valid action\n", textspeeds [textchoice]);
					break;
				}
				case ("u"):
				{
					// Only allow movement if the current room has this connection
					if (map.current.connections [4] >= 0)
						map.moveUp ();
					else
						slowPrint ("Not a valid action\n", textspeeds [textchoice]);
					break;
				}
				case ("d"):
				{
					// Only allow movement if the current room has this connection
					if (map.current.connections [5] >= 0)
						map.moveDown ();
					else
						slowPrint ("Not a valid action\n", textspeeds [textchoice]);
					break;
				}
				default:
				{
					// Catch-all failsafe
					slowPrint ("Not a valid action\n", textspeeds [textchoice]);
					break;
				}
			}
		}
		
		slowPrint ("Thank you for playing!", textspeeds [textchoice]);
	}
	
	// Char selection driver
	static Character chooseChar (int len, Character p1)
	{
		String input;
		int choice = -1;
		Character player = p1;

		slowPrint ("\nWhat would you like to do?\n\n", len);
		slowPrint ("[(N)ew Character]      [(C)hoose Character]\n[(D)elete Character]   [(F)inish]\n> ", 0);
		
		// Main choose char loop
		while (inputvalid (input = input ().toLowerCase ()))
		{
			if (input.equals ("n") || input.equals ("new") || input.equals ("new character"))
			{
				// Only make new char if not at max already
				if (loadChar (2) == null)
					player = newChar (len);
				else
					slowPrint ("Sorry, our benevolent overlords have imposed a" +
							" limit of 3 saved characters.\nTry deleting one or all first. :)\n", 0);
			}
			else if (input.equals ("c") || input.equals ("choose") || input.equals ("choose character"))
			{
				// Ensure there are chars saved. If none, direct them to char creation
				if (loadChar (0) == null)
				{
					slowPrint ("\nSorry, there are no saved characters.\n" +
							"Let me point you in the right direction:\n\n", len);
					return newChar (len);
				}
				
				slowPrint ("\nSelect Which Character?\n", len);
				
				// Print out first 3 chars, because we decided 3 is max, but is scalable
				for (int i = 0; i < 3; i++)
				{
					player = loadChar (i);
					if (player != null)
						slowPrint (i + ": " + player + "\n", 0);
					else
						break;
				}
				slowPrint ("> ", 0);
				
				// Let them choose the char by the index or name
				while (input != null)
				{
					input = input ();
					
					// Break if they want to quit, quit is after loop
					if (!inputvalid (input))
						break;
					
					// Turn input into an index, whether they put in the number or name
					try
					{
						// If input is the index
						choice = Integer.parseInt (input);
					}
					catch (NumberFormatException e)
					{
						// If input is the name
						for (int i = 0; i < 3; i++)
						{
							player = loadChar (i);
							if (player != null && player.is (input))
							{
								choice = i;
								break;
							}
							else if (player == null)
								break;
						}
					}
					
					// Load the corresponding char
					if ((player = loadChar (choice)) != null)
						break;
					else
						slowPrint ("\nThat's not a valid choice.\n> ", len);
				}
				// Quit here if they don't want to load
				if (!inputvalid (input))
					break;
				
				// Usability message, letting the user know their choice was confirmed
				slowPrint ("You loaded: " + player + "\n", 0);
				return player;
			}
			else if (input.equals ("d") || input.equals ("delete") || input.equals ("delete character"))
			{
				// Ensure there are chars saved. If none, return them to options
				if (loadChar (0) == null)
				{
					slowPrint ("Ummm, you have no characters to delete.\n" +
							"Make some first, then you can delete them.\n\n", len);
					break;
				}
				slowPrint ("\nDelete Which Character?\n", len);
				
				// Print out first 3 chars, because we decided 3 is max, but is scalable
				for (int i = 0; i < 3; i++)
				{
					player = loadChar (i);
					if (player != null)
						slowPrint (i + ": " + player + "\n", 0);
					else
						break;
				}
				slowPrint ("> ", 0);
				
				// Let them choose the char by the index or name
				while (input != null)
				{
					input = input ();
					
					// Break if they want to quit, quit is after loop
					if (!inputvalid (input))
						break;
					
					// Turn input into an index, whether they put in the number or name
					try
					{
						// If input is the index
						choice = Integer.parseInt (input);
					}
					catch (NumberFormatException e)
					{
						// If input is the name
						for (int i = 0; i < 3; i++)
						{
							player = loadChar (i);
							if (player != null && player.is (input))
							{
								choice = i;
								break;
							}
							else if (player == null)
								break;
						}
					}
					
					// Load the corresponding char
					if ((player = loadChar (choice)) != null)
						break;
					else
						slowPrint ("\nThat's not a valid choice.\n> ", len);
				}
				// Quit here if they don't want to delete
				if (!inputvalid (input))
					break;

				// Usability message, letting the user know their choice was confirmed
				slowPrint ("You deleted: " + player + "\n", 0);
				delChar (player);
			}
			else if (input.equals ("f") || input.equals ("finish"))
			{
				// Check if user has selected a char
				// If no, redo loop. If yes, return them to menu
				if (player == null)
				{
					slowPrint ("Um, you have not selected a character...\nHow do you expect to play?\n" +
							"Try again.\n", len );
				}
				else
					return player;
			}
			else if (input.equals ("dev"))
			{
				// For speeding up the dev process, use a god strength char
				return new Character ("Chaos", 0, true, 200, 2, 2, 2, 2, 2, 2, 2, true);
			}
			else
			{
				// Catch-all failsafe
				slowPrint ("Invalid Input, please retry\n", len);
				slowPrint ("\nWhat would you like to do?\n\n", len);
				slowPrint ("[(N)ew Character]      [(C)hoose Character]\n[(D)elete Character]   [(F)inish]\n> ", 0);
			}

			slowPrint ("\nWhat would you like to do?\n\n", len);
			slowPrint ("[(N)ew Character]      [(C)hoose Character]\n[(D)elete Character]   [(F)inish]\n> ", 0);
		}
		return null;
	}
	// Create new char process
	static Character newChar (int len)
	{
		String input;
		String name = "";
		String racestr = "";
		int race = 0;
		int Str = 0;
		int End = 0;
		int Int = 0;
		int Wil = 0;
		int Agl = 0;
		int Spd = 0;
		int Lck = 0;
		int statpoints = 10;
		Boolean gender = true;
		Boolean retry = true;
		Character player;
		
		// Name input
		slowPrint ("New Character, Ok.\n", len);
		slowPrint ("Let's go through the steps.\nName:\n> ", len);
		input = input ();
		if (inputvalid (input))
			name = input;
		slowPrint ("Um, " + name + "? You sure? Ok, well whatever, you're the player...\n\n", len);
		
		// Race Selection
		slowPrint ("Ok, Race:\n", len);
		slowPrint ("0:       Human\n1:         Elf\n2:         Orc\n3:       Gnome\n4:       Dwarf\n" +
		"5:  Dragonborn\n6:  Half-Troll\n7: Lizard-Folk\n8:    Cat-Folk\n9:    Tiefling\n> ", 0);
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
		
		// Gender selection
		slowPrint ("So um, what's.... uh, what's your gender?:\n", len);
		slowPrint ("0: Male\n1: Female\n> ", 0);
		input = input ().toLowerCase ();
		
		while (inputvalid (input))
		{
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
		}
		if (gender)
			slowPrint ("You're a dude?\nOk, Ok, yes you do look manly, I didn't want to assume.\n\n", len);
		else
			slowPrint ("You're a dudette?\nNo, you don't look too manly, I just didn't want to assume.\n\n", len);
		
		// Stat point allocation
		slowPrint ("Ok, now you have to allocate stat points.\nYou get 10 points to" +
				" use across all 7 stats, be wise.\n", len);
		slowPrint ("Strength\nEndurance\nIntelligence\nWillpower\nAgility\nSpeed\nLuck\n\n", 0);
		
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
					slowPrint ("Um, that's more points than you have... START OVER!\n\n\n", len);
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
					"You spent too many points on Strength already.\nSTART OVER!\n\n\n", len);
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
					slowPrint ("Um, that's more points than you have... START OVER!\n\n\n", len);
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
					slowPrint ("Um, that's more points than you have... START OVER!\n\n\n", len);
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
					slowPrint ("Um, that's more points than you have... START OVER!\n\n\n", len);
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
					slowPrint ("Um, that's more points than you have... START OVER!\n\n\n", len);
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
					slowPrint ("Um, that's more points than you have... START OVER!\n\n\n", len);
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
						"Why didn't you use them you scrub?\nThat's what they're for!\n\n", len);
			
			// Double check the user doesn't want to retry
			slowPrint ("Are you sure you're good with this?\n", len);
			slowPrint (String.format ("%14s%2d\n", "Strength: ", Str ), 0);
			slowPrint (String.format ("%14s%2d\n", "Endurance: ", End), 0);
			slowPrint (String.format ("%14s%2d\n", "Intelligence: ", Int), 0);
			slowPrint (String.format ("%14s%2d\n", "Willpower: ", Wil), 0);
			slowPrint (String.format ("%14s%2d\n", "Agility: ", Agl), 0);
			slowPrint (String.format ("%14s%2d\n", "Speed: ", Spd), 0);
			slowPrint (String.format ("%14s%2d\n", "Luck: ", Lck), 0);
			slowPrint ("[(Y)es]   [(N)o]\n> ", 0);
			
			if ((input = input ().toLowerCase ()).equals ("y") || input.equals ("yes"))
				retry = false;
			else
				statpoints = 10;
		}
		
		// Create and save character
		player = new Character (name, race, gender, Str, End, Int, Wil, Agl, Spd, Lck, 1, true);
		
		if (!charExists (player))
			saveChar (player);
		else
			slowPrint ("\nThis character exists already. I'm not going to save them twice.\n", len);
		
		// Return character so that it will be selected for the user to simply finish
		return player;
	}
	// Output char to the txt file, if the max of 3 saved characters is not met
	static void saveChar (Character c)
	{
		// Check for max chars save already
		// Redundancy for accuracy
		if (loadChar (2) != null)
			slowPrint ("Sorry, maximum of 3 saved characters.\nDelete one first.\n\n", 0);
		else
		{
			slowPrint ("Saving character\n\n", 0);
			// Append to the save file if it exists, create it if it doesn't
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
				{ slowPrint ("Error saving character, you can play, but will have to make again.\n\n", 0); }
			}
		}
	}
	// Will delete a char from the txt file
	static void delChar (Character c)
	{
		Character [] hold = new Character [3];
		int rem = 2;
		PrintWriter out = null;
		
		// Look through saved chars for the char to delete, and save the index
		for (int i = 0; i < 3; i++)
		{
			hold [i] = loadChar (i);
			if (hold [i] != null && Character.areEqual (c, hold [i]))
				rem = i;
			else if (hold [i] == null)
				break;
		}
		
		// Create new file
		try
		{
			out = new PrintWriter ("saved_chars.txt", "UTF-8");
		}
		catch (FileNotFoundException | UnsupportedEncodingException e)
		{
			slowPrint ("Error with file\n\n", 0);
			e.printStackTrace ();
			return;
		}
		
		// Write only the chars that are not the one to delete
		for (int i = 0; i < 3; i++)
		{
			if (hold [i] != null && i != rem)
				out.print (hold [i].printCharacter ());
			else if (hold [i] == null)
				break;
		}
		
		out.close ();
	}
	// Load char from the txt file and return it, if there, return null if not
	// Used by chooseChar, saveChar, and delChar
	static Character loadChar (int num)
	{
		Path save = Paths.get ("saved_chars.txt");
		Character load = null;
		
		// Change the input index to reflect the number of lines that must be skipped
		// Each char takes up 11 lines in the save file
		int i = num * 11;
		
		// Go through each set of 11 lines and build a new Char out of them, without re-editing the stats
		try
		{
			if (input (save.toFile (), i) != null)
				load = new Character (input (save.toFile (), i),
						Integer.parseInt (input (save.toFile (), i + 1)),
						Boolean.parseBoolean (input (save.toFile (), i + 2)),
						Integer.parseInt (input (save.toFile (), i + 3)),
						Integer.parseInt (input (save.toFile (), i + 4)),
						Integer.parseInt (input (save.toFile (), i + 5)),
						Integer.parseInt (input (save.toFile (), i + 6)),
						Integer.parseInt (input (save.toFile (), i + 7)),
						Integer.parseInt (input (save.toFile (), i + 8)),
						Integer.parseInt (input (save.toFile (), i + 9)),
						Integer.parseInt (input (save.toFile (), i + 10)), false);
		}
		catch (IOException e)
		{
			slowPrint ("Sorry, your computer has decided it doesn't enjoy files.\n\n", 0);
		}
		
		return load;
	}
	// Method to check if a char is a duplicate
	// Used by saveChar
	static boolean charExists (Character c)
	{
 		Character player;
 		boolean exists = false;
 		
 		for (int i = 0; i < 3; i++)
 		{
 			player = loadChar (i);
 			
 			if (player != null)
 			{
 				exists = Character.areEqual (c, player);
 				if (exists)
 					break;
 			}
 		}
 		return exists;
	}
	
	// Battle driver method
	// Takes in the player, and the current map.
	// Returns true if the player died
	static Boolean battle (Character p1, Map map)
	{
		Character enemy = null;
		Character hold = null;
		Character [] order = new Character [map.current.numenemies + 1];
		Character [] ordercpy = new Character [map.current.numenemies + 1];
		Character [] enemies = new Character [map.current.numenemies];
		int turn = 0;
		int roll = 0;
		int selected;
		int maxinit;
		int textchoice = 3;
		int [] textspeeds = {120, 90, 60, 0};
		String input = "";
		
		// Put player and enemies into an array for the turn utility
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
		
		// Reordering the array based on initiative rolls, higher rolls first, ties broken by race
		for (int i = 0; i <= map.current.numenemies; i++)
		{
			maxinit = i;
			for (int j = i; j <= map.current.numenemies && order [j] != null; j++)
			{
				if (order [j].compareTo (order [maxinit]) > 0 ||
						order [j].getRace ().compareTo (order [maxinit].getRace ()) < 0)
					maxinit = j;
			}
			hold = order [i];
			order [i] = order [maxinit];
			order [maxinit] = hold;
			ordercpy [i] = order [i];
		}
		hold = null;
		
		// Removing player from order, for target selection
		for (int i = 0; i < ordercpy.length - 1 && ordercpy [i] != null; i++)
		{
			if (!(ordercpy [i].getName () == null && hold == null))
			{
				hold = ordercpy [i];
				ordercpy [i] = ordercpy [i + 1];
				ordercpy [i + 1] = hold;
			}
			if (i < enemies.length)
				enemies [i] = ordercpy [i];
		}
		
		
		slowPrint ("\nThe order is:\n", textspeeds [textchoice]);
		// Print the battle order
		for (int i = 0; i < order.length && order [i] != null; i++)
			if (order [i].getName () != null)
				slowPrint (order [i].getName () + "\n", textspeeds [textchoice]);
			else
				slowPrint (order [i].getRace () + "\n", textspeeds [textchoice]);
		
		
		// Battle manager
		while (!p1.isDead () && !map.current.roomCleared ())
		{
			// Ensure no out of bounds exception or null pointer exception
			if (turn >= order.length || order [turn] == null)
			{
				slowPrint ("\nTop of the order again.\n", textspeeds [textchoice]);
				turn = 0;
			}
			
			input = input.toLowerCase ();
			
			// Player turn
			if (order [turn].getName () != null)
			{
				while (!input.equals ("a") && !input.equals ("attack"))
				{
					slowPrint ("\nIt's your turn, what do you want to do?\n[(A)ttack]   [(C)heck Bag]" +
							"   [(P)erception Check]\n> ", textspeeds [textchoice]);
					input = input ().toLowerCase ();
					
					if (input.equals ("a") || input.equals ("attack"))
					{
						slowPrint ("\nAttack who?\n", textspeeds [textchoice]);
						
						// Print out list of living enemies
						for (int i = 0; i < enemies.length && enemies [i] != null; i++)
							if (!map.current.enemyDead (enemies [i]))
								slowPrint (i + ": " + enemies [i].printEnemy () + "\n", textspeeds [textchoice]);
						
						// Loop to ensure an enemy is selected correctly
						while (inputvalid (input = input ().toLowerCase ()))
						{
							try
							{
								selected = Integer.parseInt (input);
								// Input validation for index selection
								if (selected >= enemies.length || enemies [selected] == null)
								{
									slowPrint ("Number not valid. Can you like be nice please?\n",
											textspeeds [textchoice]);
									for (int i = 0; i < enemies.length && enemies [i] != null; i++)
										if (!map.current.enemyDead (enemies [i]))
											slowPrint (i + ": " + enemies [i].printEnemy () + "\n",
													textspeeds [textchoice]);
								}
								else
								{
									enemy = enemies [selected];
									break;
								}
							}
							catch (NumberFormatException e)
							{
								selected = map.current.hasEnemy (input);
								// Input validation for name selection
								if (selected < 0)
								{
									slowPrint ("That's not an enemy. Play nice.\n", textspeeds [textchoice]);
									for (int i = 0; i < enemies.length && enemies [i] != null; i++)
										if (!map.current.enemyDead (enemies [i]))
											slowPrint (i + ": " + enemies [i].printEnemy () + "\n",
													textspeeds [textchoice]);
								}
								else
								{
									enemy  = enemies [selected];
									break;
								}
							}
						}
						
						// Roll vs. AC and attack if the roll is good enough
						roll = p1.rolld20 ();
						slowPrint ("\nYou rolled " + roll + " verses the " + enemy.getRace () + "'s AC\n",
								textspeeds [textchoice]);
						if (roll > enemy.getAC ())
						{
							slowPrint ("Attacked for " + p1.getDamage () + "\n", textspeeds [textchoice]);
							if (enemy.attacked (p1.getDamage ()))
							{
								slowPrint ("\n\nThe weapon swung true\nThe " +
										enemy.getRace () + " fell\nA fatal blow\nTo the left pinky toe.\n\n",
										textspeeds [textchoice]);
							}
							else
								slowPrint (enemy.getRace () + " has " + enemy.getHealth () + " health left.\n",
										textspeeds [textchoice]);
						}
						else
							slowPrint ("That ain't gonna cut it.\n", textspeeds [textchoice]);
						input = "";
						break;
					}
					else if (input.equals ("c") || input.equals ("check") || input.equals ("check bag"))
						// Check inventory
						p1.inventoryCheck ();
					else if (input.equals ("p") || input.equals ("perception check"))
						// Check enemy description. Has no use other than amusement
						for (int i = 0; i < enemies.length; i++)
							enemies [i].printDescription (textspeeds [textchoice]);
					else
						// Catch-all failsafe
						slowPrint ("Invalid input.\n", textspeeds [textchoice]);
				}
			}
			else
			{
				// Double checking that the enemy isn't dead. If it is, skip it, if not, give it a turn
				if (map.current.enemyDead (order [turn]))
				{
					turn++;
					continue;
				}
				else
					// Enemy turn
					// If the player dies, break out of loop;
					slowPrint ("\nIt's " + order [turn].getRace () + "'s turn.\n", textspeeds [textchoice]);
			}
			
			// Standard wraparound to get back to index 0, start the order over again
			if ((turn + 1) > order.length)
			{
				slowPrint ("\nTop of the order again.\n", textspeeds [textchoice]);
				turn = 0;
			}
			else
				turn++;
		}
		
		// Battle over. Return whether the player died.
		return p1.isDead ();
	}
	
	// Change text speed
	// So far only functionality
	static int changeSettings (int current)
	{
		int [] speeds = {120, 90, 40, 0};
		int choice = current;
		String input;
		
		slowPrint ("\nWhat would you like to do?\n\n", speeds [choice]);
		slowPrint ("[Text (Sp)eed]   [Text (Si)ze]   [(F)inish]\n> ", 0);
		
		// Loop to ensure proper choice
		while (inputvalid (input = input ().toLowerCase ()))
		{
			if (input.equals ("sp"))
			{
				// Change text delay
				slowPrint ("\nOk, what speed do you want?\n", speeds [choice]);
				slowPrint ("[(S)low]   [[(M)edium]\n[(F)ast]   [(N)o Delay]\n> ", 0);
				
				input = input ().toLowerCase ();
				if (input.equals ("s") || input.equals ("slow"))
					choice = 0;
				else if (input.equals ("m") || input.equals ("medium"))
					choice = 1;
				else if (input.equals ("f") || input.equals ("fast"))
					choice = 2;
				else if (input.equals ("n") || input.equals ("no") || input.equals ("no delay"))
					choice = 3;
				else
					slowPrint ("\nNot a valid choice\n\n", speeds [choice]);
			}
			else if (input.equals ("si"))
			{
				// Change text size
				slowPrint ("\nNot implemented yet\n\n", 0);
			}
			else if (input.equals ("f") || input.equals ("finish"))
				// Finish and return
				break;
			else
			{
				// Catch-all failsafe
				slowPrint ("\nNot a valid choice\n\n", speeds [choice]);
			}
			
			slowPrint ("\nWhat would you like to do?\n\n", speeds [choice]);
			slowPrint ("[Text (Sp)eed]   [Text (Si)ze]   [(F)inish]\n> ", 0);
		}
		
		
		return choice;
	}
	
	// Print str out in a slow, epic way, dependent on the delay passed in for len
	// Also can print str without delay
	// Used also in order to facilitate outputting to the UI
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
 	// Used by loadChar to read from save file
 	static String input (File f, int toskip) throws IOException
 	{
 		BufferedReader br = new BufferedReader (new FileReader (f));
 		String ret = br.readLine ();
 		
 		for (int i = 0; i < toskip; i++)
 			ret = br.readLine ();
 		
 		br.close ();
 		return ret;
 	}
 	
 	// Checks if input is any exit keyword, even ones not listed for player options
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