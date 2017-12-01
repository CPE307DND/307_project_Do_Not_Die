package logic;

import java.util.ArrayList;
import java.util.Random;

public class Character implements Comparable <Character>
{
	public Character (String n, int r, Boolean g, int STR, int END, int INT, int WIL, int AGL, int SPD, int LCK, int lvl)
	{
		roller = new Random ();
		level = lvl;
		int rand;
		
		if (r == 1)
		{
			AC = 3;
			race = "Elf";
			gender = g;
			Strength = STR - 2;
			Endurance = END;
			Intelligence = INT + 5;
			Willpower = WIL + 5;
			Agility = AGL + 2;
			Speed = SPD + 1;
			Luck = LCK + 2;
			
			rand = roller.nextInt (2);
			if (gender)
			{
				switch (rand)
				{
					case (0):
					{
						description = "He has long blonde hair, braided back, and a tall, slender build.\n" +
								"There is a keen look in his eye, and you can sense his magic power.\n\n";
						break;
					}
					case (1):
					{
						description = "Definitely a nerd.\nI can see the library card sticking out of " +
								"his fanny pack.\nProbably got beat up as a kid, for bringing mushrooms to eat for lunch.\n\n";
						break;
					}
					case (2):
					{
						description = "He's basically just the most swole dude.\nYou're intimidated just " +
							"standing in front of him.\nHe has all the gains.\nYou can see a pile of empty " +
							"protein shake bottles behind him.\nAnd... wait, is that a needle?\n\n";
						break;
					}
				}
			}
			else
			{
				switch (rand)
				{
					case (0):
					{
						description = "Short black hair, rounded cheeks, and a short, stout build.\n" +
								"There hates everyone, and she lets them all know it.\n\n";
						break;
					}
					case (1):
					{
						description = "What a nerd. I can see the trombone sticking out of her back pocket.\n" +
								"She has a black eye, so I guess she hasn't mastered the art of Illusory magic.\n\n";
						break;
					}
					case (2):
					{
						description = "She has a half-shaved head, the rest is a striking blue.\nShe has a manuscript " +
								"under her arm, the title,\nyou can barely see, is \"How to not mis-gender animals\"\n\n";
						break;
					}
				}
			}
		}
		else if (r == 2)
		{
			AC = 7;
			race = "Orc";
			gender = g;
			Strength = STR + 4;
			Endurance = END + 3;
			Intelligence = INT - 3;
			Willpower = WIL - 4;
			Agility = AGL - 4;
			Speed = SPD - 4;
			Luck = LCK - 1;
			
			rand = roller.nextInt (2);
			if (gender)
			{
				switch (rand)
				{
					case (0):
					{
						description = "Yeah, he's green.\n\n";
						break;
					}
					case (1):
					{
						description = "He's wearing Timbz, with whitewashed, ripped jeans.\nHe's got a black and white flannel tied about his waist.\n" +
								"His hat and shirt say Supreem.\n\n";
						break;
					}
					case (2):
					{
						description = "He's missing his left arm.\nHe has a scar going over his eyepatch on his right eye.\n" +
								"And he has a peg leg.\nAlmost like a pirate without a hook.\n\n";
						break;
					}
				}
			}
			else
			{
				switch (rand)
				{
					case (0):
					{
						description = "Woah, she's blue. I thought she'd be green.\n\n";
						break;
					}
					case (1):
					{
						description = "She's got black leggings and Ug boots, an oversized white sweater that hangs off her shoulders\n" +
								"and says in big italicized black letters: Whatever.\nHer brown hair with " +
								"unnatural looking highlights is pulled back in a messy bun.\n\n";
						break;
					}
					case (2):
					{
						description = "Um, she's in a wheelchair.\nI feel kinda bad about this, but " +
								"she wanted to attack me, so...\n\n";
						break;
					}
				}
			}
		}
		else if (r == 3)
		{
			AC = 1;
			race = "Gnome";
			gender = g;
			Strength = STR - 7;
			Endurance = END - 7;
			Intelligence = INT + 7;
			Willpower = WIL + 6;
			Agility = AGL - 4;
			Speed = SPD - 4;
			Luck = LCK + 4;
			
			rand = roller.nextInt (2);
			if (gender)
			{
				switch (rand)
				{
					case (0):
					{
						description = "He has long curly hair, one sequined white glove on his left hand,\n" +
								"and a he's leaning way too far forward.\n\n";
						break;
					}
					case (1):
					{
						description = "He's just so dry, like how can he be this dry?\nHe's just such a parched dude.\n\n";
						break;
					}
					case (2):
					{
						description = "Ew, this guy's sopping wet...\nAnd he smells like gravy, wtf?!\n\n";
						break;
					}
				}
			}
			else
			{
				switch (rand)
				{
					case (0):
					{
						description = "She has hair twice as long as her body,\nbut it's only down to her knees.\n\n";
						break;
					}
					case (1):
					{
						description = "She's wearing a necklace with a crystal.\nSand is spilling out of both " +
								"of her hands.\nAnd she's mumbling things under her breath.\n\n";
						break;
					}
					case (2):
					{
						description = "She's a walking snowman.\n\"So cold\" she says repeatedly, " +
								"as she is visibly shivering.\n\n";
						break;
					}
				}
			}
		}
		else if (r == 4)
		{
			AC = 6;
			race = "Dwarf";
			gender = g;
			Strength = STR + 3;
			Endurance = END + 1;
			Intelligence = INT - 2;
			Willpower = WIL - 3;
			Agility = AGL - 1;
			Speed = SPD - 3;
			Luck = LCK + 2;
			
			rand = roller.nextInt (2);
			if (gender)
			{
				switch (rand)
				{
					case (0):
					{
						description = "He's on a miniature stallion, brandishing a broom as his weapon.\n\n";
						break;
					}
					case (1):
					{
						description = "He's orange, tubby, and has a short, sparse beard.\nAnd green hair.\n\n";
						break;
					}
					case (2):
					{
						description = "He's got drool all over himself. He's just so wet.\nWith his night cap on, " +
								"it looks like you woke him from his sleep.\nHope you're satisfied.\n\n";
						break;
					}
				}
			}
			else
			{
				switch (rand)
				{
					case (0):
					{
						description = "She is an odd one, she has a bandolier full of carrots,\nwhich " +
								"she periodically pulls out and eats.\n\n";
						break;
					}
					case (1):
					{
						description = "She has antlers and a red nose.\nLooks like she's pretending to " +
								"be an animal or druid.\n\n";
						break;
					}
					case (2):
					{
						description = "Wait, you recognize her.\nShe was the host of the popular game show " +
								"Family Feudalism, wasn't she?\n\n";
						break;
					}
				}
			}
		}
		else if (r == 5)
		{
			AC = 8;
			race = "Dragonborn";
			gender = g;
			Strength = STR + 5;
			Endurance = END + 6;
			Intelligence = INT + 4;
			Willpower = WIL - 5;
			Agility = AGL - 3;
			Speed = SPD + 3;
			Luck = LCK + 3;

			
			rand = roller.nextInt (2);
			if (gender)
			{
				switch (rand)
				{
					case (0):
					{
						description = "He's just a run-of-the-mill Dragonborn man.\nYou'd forget him in an instant.\n\n";
						break;
					}
					case (1):
					{
						description = "This dude's got the biggest afro you've ever seen, an he paired it " +
								"with\nbelbottom jeans, a psychadelic shirt, and purple circlular glasses.\n\n";
						break;
					}
					case (2):
					{
						description = "He's got a beret, a thin straight mustache, a black and white striped shirt,\n" +
								"red pants, and he's holding a paintbrush.\n\n";
						break;
					}
				}
			}
			else
			{
				switch (rand)
				{
					case (0):
					{
						description = "She has an air about her of pomp and circumstance.\nFrizzless hair, " +
								"manicured nails, and fashionable dress.\nToo bad it offers her no protection.\n\n";
						break;
					}
					case (1):
					{
						description = "She's wearing a Ulta-Fighter-Mate belt, and a crown of rat bones.\n\n";
						break;
					}
					case (2):
					{
						description = "She has cute little rectangle glasses on her freckled face, overalls, and a pitchfork.\n\n";
						break;
					}
				}
			}
		}
		else if (r == 6)
		{
			AC = 8;
			race = "Half-Troll";
			gender = g;
			Strength = STR + 7;
			Endurance = END + 8;
			Intelligence = INT - 10;
			Willpower = WIL - 7;
			Agility = AGL - 5;
			Speed = SPD + 4;
			Luck = LCK - 4;
			
			rand = roller.nextInt (2);
			if (gender)
			{
				switch (rand)
				{
					case (0):
					{
						description = "HEY! It's just 3 Dwarfs in a trenchcoat, on stilts!\n\n";
						break;
					}
					case (1):
					{
						description = "His face is gnarled, like the bark of a warped oak tree.\n" +
								"However, you can't help but feel like he's a nice guy once you get to know him.\n\n";
						break;
					}
					case (2):
					{
						description = "Fuzzy and purple, with horns, khakis, and a nametag that says \"Hello, I'm Eduardo.\"\n\n";
						break;
					}
				}
			}
			else
			{
				switch (rand)
				{
					case (0):
					{
						description = "She has a tired face, wrinkled and with sunken eyes.\nShe looks so worn, and world-weary.\n\n";
						break;
					}
					case (1):
					{
						description = "She's got a whole bunch of fur pelts,\nand a tarnished bronze chalice with bubbling liquid.\n\n";
						break;
					}
					case (2):
					{
						description = "It's actually just a tree with a lot of makeup on.\nWhoever did this was bad at it, too.\n\n";
						break;
					}
				}
			}
		}
		else if (r == 7)
		{
			AC = 6;
			race = "Lizard-Folk";
			gender = g;
			Strength = STR;
			Endurance = END + 2;
			Intelligence = INT - 1;
			Willpower = WIL + 1;
			Agility = AGL + 3;
			Speed = SPD;
			Luck = LCK + 3;
			
			rand = roller.nextInt (2);
			if (gender)
			{
				switch (rand)
				{
					case (0):
					{
						description = "He's got long black robes and old man pants and shoes.\n\n";
						break;
					}
					case (1):
					{
						description = "He's got a mask that looks like his own face...\nWhat an idiot.\n\n";
						break;
					}
					case (2):
					{
						description = "He is holding a pamphlet, he's got some guaraches on,\nand he's trying " +
								"to sell you a door.\nHe's a door-to-door door salesman.\n\n";
						break;
					}
				}
			}
			else
			{
				switch (rand)
				{
					case (0):
					{
						description = "She's got a purple flower dress on, and she's singing about touching the grass.\n\n";
						break;
					}
					case (1):
					{
						description = "For some reason, her eyes are just sharks...\nBut how?!\n" +
								"And her mouth is just so full with broccolis... So full.\n\n";
						break;
					}
					case (2):
					{
						description = "Um, she's doing pushups between actions...\nBut why?\n" +
								"What does she hope to gain?\n\n";
						break;
					}
				}
			}
		}
		else if (r == 8)
		{
			AC = 4;
			race = "Cat-Folk";
			gender = g;
			Strength = STR;
			Endurance = END;
			Intelligence = INT;
			Willpower = WIL;
			Agility = AGL + 8;
			Speed = SPD + 8;
			Luck = LCK + 6;
			
			rand = roller.nextInt (2);
			if (gender)
			{
				switch (rand)
				{
					case (0):
					{
						description = "Dude's playing with yarn, in the corner.\n\n";
						break;
					}
					case (1):
					{
						description = "The Catman fell in some water and jumped onto you to try to escape it.\n\n";
						break;
					}
					case (2):
					{
						description = "Looks like Ben Aflec.\n\n";
						break;
					}
				}
			}
			else
			{
				switch (rand)
				{
					case (0):
					{
						description = "She's just licking herself. Just as you enter, she jumps 6 feet in the air.\n\n";
						break;
					}
					case (1):
					{
						description = "She has long talons, a whip, and a black latex suit.\n\n";
						break;
					}
					case (2):
					{
						description = "It's just an old lady, with a bunch of cats...\n\n";
						break;
					}
				}
			}
		}
		else if (r == 9)
		{
			AC = 4;
			race = "Tiefling";
			gender = g;
			Strength = STR + 1;
			Endurance = END - 2;
			Intelligence = INT + 3;
			Willpower = WIL + 2;
			Agility = AGL - 3;
			Speed = SPD - 3;
			Luck = LCK + 7;
			
			rand = roller.nextInt (2);
			if (gender)
			{
				switch (rand)
				{
					case (0):
					{
						description = "Large flaming horns, shirtless, with a large, muscled, runed chest.\n" +
								"He has a spiked tail and razor sharp teeth.\n\n";
						break;
					}
					case (1):
					{
						description = "Is singing Tenacious D under his breath, with a guitar made from a " +
								"human skeleton.\nHe's got goat legs and a leather jacket.\n\n";
						break;
					}
					case (2):
					{
						description = "He has two little puppies, and you are too focused on watching the puppies romp\n" +
								"to pay attention to what the cat-folk looks like.\n\n";
						break;
					}
				}
			}
			else
			{
				switch (rand)
				{
					case (0):
					{
						description = "Looks like your grandma... You wonder why...\n\n";
						break;
					}
					case (1):
					{
						description = "Clearly a seductress. She must be the spawn of a succubus.\n" +
								"She's got a tight black low-cut dress, meant to accent her curves, " +
								"and has a killer 'come hither' look.\n\n";
						break;
					}
					case (2):
					{
						description = "Screaming in pain as she holds steaming potatoes and butternut squash.\n\n";
						break;
					}
				}
			}
		}
		else
		{
			AC = 5;
			race = "Human";
			gender = g;
			Strength = STR;
			Endurance = END;
			Intelligence = INT;
			Willpower = WIL;
			Agility = AGL;
			Speed = SPD;
			Luck = LCK;
			
			rand = roller.nextInt (2);
			if (gender)
			{
				switch (rand)
				{
					case (0):
					{
						description = "Content atop a pile of husks, he sits shucking corn.\n" +
								"He becomes enraged as you disturb him.\n\n";
						break;
					}
					case (1):
					{
						description = "Bald with circular black glasse and a puffy handlebar mustache.\n" +
								"He's got an egg-shaped body, and is wearing a red jumpsuit,\nshouting about some chaos sapphires or something.\n\n";
						break;
					}
					case (2):
					{
						description = "Unkempt brown hair, black circular rimmed glasses,\nand " +
								"black robes with a red and yellow tie to finish off his look.\n" +
								"He seems to have some sort of zig-zaggy scar on his forehead. Strange.\n\n";
						break;
					}
				}
			}
			else
			{
				switch (rand)
				{
					case (0):
					{
						description = "She's just sitting on the floor weeping.\n" +
								"\"WHY DID THEY NAME ME GREGGBERT?!\" she wails.\n\n";
						break;
					}
					case (1):
					{
						description = "Covered in paste and feathers, she sits there softly cooing to herself.\n\n";
						break;
					}
					case (2):
					{
						description = "She is covered in some armor you've never seen before, but it looks lightweight and durable.\n" +
								"She has dual katanas, and you can tell that they are both enchanted to deal elemental damage.\n" +
								"This lady means serious business, and she came equipped to enforce her will.\n\n";
						break;
					}
				}
			}
		}
		
		if (gender)
		{
			Strength++;
			Endurance++;
			Speed++;
			Intelligence--;
			Willpower--;
			Agility--;
		}
		else
		{
			Strength--;
			Endurance--;
			Speed--;
			Intelligence++;
			Willpower++;
			Agility++;
		}
		
		name = n;
		racenum = r;
		health += (Endurance * 5);
		maxhealth = health;
		damage += ((Strength * 5) + (Intelligence * 3)) / 2;
		inventory = new ArrayList <Treasure> ();
	}
	
	// Methods for printing
	public String toString ()
	{
		if (name == null)
			return printEnemy ();
		else
			return printPlayer ();
	}
	public String printPlayer () { return String.format ("%11s", name) + ":\tHP (" +
		String.format ("%3d", health) + "/" + String.format ("%3d", maxhealth) + ")"; }
	public String printEnemy () { return String.format ("%11s", race) + ":\tHP (" +
	String.format ("%3d", health) + "/" + String.format ("%3d", maxhealth) + ")"; }
	public String printCharacter ()
	{
		return name + "\n" + racenum + "\n" + gender + "\n" + Strength + "\n" + Endurance +
				"\n" + Intelligence + "\n" + Willpower + "\n" + Agility + "\n" + Speed +
				"\n" + Luck + "\n" + level + "\n";
	}
	public String printStats ()
	{
		return "STR: " + Strength + "\nEND: " + Endurance + "\nINT: " + Intelligence +
				"\nWIL: " + Willpower + "\nAGL: " + Agility + "\nSPD: " + Speed +  "\nLCK: " + Luck + "\n";
	}
	public void printDescription (int len) { DND.slowPrint ("\n" + description, len); }
	
	// Methods for battle
	public Boolean attacked (int dmg)
	{
		if ((health -= dmg) <= 0)
		{
			health = 0;
			return true;
		}
		return false;
	}
	public void healed (int heals) { if ((health += heals) > maxhealth) health = maxhealth; }
	public Boolean isDead () { return (health <= 0); }
	public int initiative ()
	{
		initiative = rolld20 ();
		return initiative;
	}
	public int rolld20 () { return roller.nextInt (20) + 1; }
	public int rolld12 () { return roller.nextInt (12) + 1; }
	public int rolld10 () { return roller.nextInt (10) + 1; }
	public int rolld8 () { return roller.nextInt (8) + 1; }
	public int rolld6 () { return roller.nextInt (6) + 1; }
	public int rolld4 () { return roller.nextInt (4) + 1; }
	
	// Methods for inventory
	public int inInventory (Treasure item)
	{
		for (int i = 0; i < inventory.size (); i++)
			if (inventory.get (i).getClass ().getName ().equals ("logic.Weapon") && item.getClass ().getName ().equals ("logic.Weapon"))
			{
				if (((Weapon) inventory.get (i)).getType ().getClass ().equals (((Weapon) item).getType ().getClass ()))
				{
					if (inventory.get (i).equals (((Weapon) item).getType ()))
					{
						return i;
					}
				}
			}
			else if (inventory.get (i).getClass ().getName ().equals ("logic.Armor") && item.getClass ().getName ().equals ("logic.Armor"))
			{
				if (inventory.get (i).equals (((Armor) item).getType ()))
				{
					return i;
				}
			}
			else if (inventory.get (i).getClass ().getName ().equals ("logic.Misc") && item.getClass ().getName ().equals ("logic.Misc"))
			{
				if (inventory.get (i).equals (item))
				{
					return i;
				}
			}
		return -1;
	}
	public void addToInventory (Treasure item) { inventory.add (item); }
	public Boolean removeFromInventory (Treasure item)
	{
		int i = inInventory (item);
		
		if (i >= 0)
		{
			inventory.remove (i);
			return true;
		}
		else
			return false;
	}
	public void inventoryCheck ()
	{
		if (inventory.size () > 0)
			for (int i = 0; i < inventory.size (); i++)
				System.out.println (inventory.get (i));
		else
			System.out.println ("You have nothing in your inventory.\n");
	}
	
	// CompareTo is used for the priorityqueue and nothing more
	// CompareTo is therefore inconsistent with equals
	@Override
	public int compareTo (Character o)
	{
		if (initiative > o.initiative)
			return 1;
		else if (initiative == o.initiative)
			return 0;
		else
			return -1;
	}
	public Boolean equals (Character o) { return race.equals (o.getRace ()) && health == o.getHealth (); }
	public Boolean equals (String str) { return race.equals (str); }
	// Used for comparing saved characters, returns true if they are the exact same
	public static Boolean areEqual (Character p1, Character p2)
	{
		return !((p1.getName () == null  && p2.getName () != null) ||
				(p1.getName () != null && p2.getName () == null) ||
				(!p1.getName ().equals (p2.getName ())) ||
				(p1.getRacenum () != (p2.getRacenum ())) ||
				(p1.getGenderbool () ^ p1.getGenderbool ()) ||
				(p1.getStrength () != p2.getStrength ()) ||
				(p1.getEndurance () != p2.getEndurance ()) ||
				(p1.getIntelligence () != p2.getIntelligence ()) ||
				(p1.getWillpower () != p2.getWillpower ()) ||
				(p1.getAgility () != p2.getAgility ()) ||
				(p1.getSpeed () != p2.getSpeed ()) ||
				(p1.getLuck () != p2.getLuck ()));
	}
	
	public int getInd () { return ind; }
	public void setInd (int i) { ind = i; }
	public int getLevel () { return level; }
	public void setLevel (int i) { level = i; }
	public int getHealth () { return health; }
	public void setHealth (int h) { health = h; }
	public int getMaxHealth () { return maxhealth; }
	public void setMaxHealth (int mh) { maxhealth = mh; }
	public int getDamage () { return damage; }
	public void setDamage (int d) { damage = d; }
	public int getAC () { return AC; }
	public void getAC (int ac) { AC = ac; }
	public int getInitiative () { return initiative; }
	public int getStrength () { return Strength; }
	public int getEndurance () { return Endurance; }
	public int getIntelligence () { return Intelligence; }
	public int getWillpower () { return Willpower; }
	public int getAgility () { return Agility; }
	public int getSpeed () { return Speed; }
	public int getLuck () { return Luck; }
	public int getRacenum () { return racenum; }
	public Boolean getGenderbool () { return gender; }
	public String getName () { return name; }
	public String getRace () { return race; }
	public String getGender () { return gender? "Male" : "Female"; }
	
	public ArrayList <Treasure> inventory;
	private String name, race;
	// For gender, true is male
	private Boolean gender;
	private int AC;
	private int damage = 20;
	private int health = 50;
	private int ind;
	private int initiative;
	private int level;
	private int maxhealth = 50;
	private int racenum;
	private int Agility;
	private int Endurance;
	private int Intelligence;
	private int Luck;
	private int Speed;
	private int Strength;
	private int Willpower;
	private Random roller;
	private String description;
}
