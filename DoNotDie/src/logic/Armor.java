package logic;

import java.util.Random;

public class Armor extends Treasure
{
	private String [] material = {"Chiton", "Leather", "Iron", "Chainmail",
			"Steel", "Elven", "Dwarvish", "Glass", "Orcish", "Ancient", "Demonic", "Platemail"};
	private Armor a;
	private int light;
	private int heavy;
	private int ar;
	private int value;
	private int weight;
	private String name;
	
	// Default constructor for Armor
	// Needed to avoid stack overflow caused by creating instances of the subclasses
	Armor () {}

	// Randomizing constructor. Pass in the desired level of the armor
	public Armor (int level)
	{
		Random r = new Random ();
		int piece = r.nextInt (4);
		//True is light
		Boolean choose = r.nextBoolean ();
		
		if (level < 5)
		{
			light = 0;
			heavy = 1;
		}
		else if (level < 10)
		{
			light = 2;
			heavy = 3;
		}
		else if (level < 15)
		{
			light = 4;
			heavy = 5;
		}
		else if (level < 20)
		{
			light = 6;
			heavy = 7;
		}
		else if (level < 25)
		{
			light = 8;
			heavy = 9;
		}
		else
		{
			light = 10;
			heavy = 11;
		}
		
		switch (piece)
		{
			default:
				if (choose)
				{
					a = new Helm (material[light]);
					name = a.getName ();
					ar = a.getAR ();
					weight = a.getWeight ();
				}
				else
				{
					a = new Helm (material [heavy]);
					name = a.getName ();
					ar = a.getAR ();
					weight = a.getWeight ();
				}
			case (1):
				if (choose)
				{
					a = new Cuirass (material [light]);
					name = a.getName ();
					ar = a.getAR ();
					weight = a.getWeight ();
				}
				else
				{
					a = new Cuirass (material [heavy]);
					name = a.getName ();
					ar = a.getAR ();
					weight = a.getWeight ();
				}
			case (2):
				if (choose)
				{
					a = new Gauntlets (material [light]);
					name = a.getName ();
					ar = a.getAR ();
					weight = a.getWeight ();
				}
				else
				{
					a = new Gauntlets (material [heavy]);
					name = a.getName ();
					ar = a.getAR ();
					weight = a.getWeight ();
				}
			case (3):
				if (choose)
				{
					a = new Greaves (material [light]);
					name = a.getName ();
					ar = a.getAR ();
					weight = a.getWeight ();
				}
				else
				{
					a = new Greaves (material [heavy]);
					name = a.getName ();
					ar = a.getAR ();
					weight = a.getWeight ();
				}
			case (4):
				if (choose)
				{
					a = new Boots (material [light]);
					name = a.getName ();
					ar = a.getAR ();
					weight = a.getWeight ();
				}
				else
				{
					a = new Boots (material [heavy]);
					name = a.getName ();
					ar = a.getAR ();
					weight = a.getWeight ();
				}
		}
	}

	// Specific constructor
	// Pass in the desired level, type of the armor, and true for light armor or false for heavy armor
	public Armor (int level, int piece, Boolean choose)
	{
		if (level < 5)
		{
			light = 0;
			heavy = 1;
		}
		else if (level < 10)
		{
			light = 2;
			heavy = 3;
		}
		else if (level < 15)
		{
			light = 4;
			heavy = 5;
		}
		else if (level < 20)
		{
			light = 6;
			heavy = 7;
		}
		else if (level < 25)
		{
			light = 8;
			heavy = 9;
		}
		else
		{
			light = 10;
			heavy = 11;
		}
		
		switch (piece)
		{
		default:
			if (choose)
			{
				a = new Helm (material[light]);
				name = a.getName ();
				ar = a.getAR ();
				weight = a.getWeight ();
			}
			else
			{
				a = new Helm (material [heavy]);
				name = a.getName ();
				ar = a.getAR ();
				weight = a.getWeight ();
			}
		case (1):
			if (choose)
			{
				a = new Cuirass (material [light]);
				name = a.getName ();
				ar = a.getAR ();
				weight = a.getWeight ();
			}
			else
			{
				a = new Cuirass (material [heavy]);
				name = a.getName ();
				ar = a.getAR ();
				weight = a.getWeight ();
			}
		case (2):
			if (choose)
			{
				a = new Gauntlets (material [light]);
				name = a.getName ();
				ar = a.getAR ();
				weight = a.getWeight ();
			}
			else
			{
				a = new Gauntlets (material [heavy]);
				name = a.getName ();
				ar = a.getAR ();
				weight = a.getWeight ();
			}
		case (3):
			if (choose)
			{
				a = new Greaves (material [light]);
				name = a.getName ();
				ar = a.getAR ();
				weight = a.getWeight ();
			}
			else
			{
				a = new Greaves (material [heavy]);
				name = a.getName ();
				ar = a.getAR ();
				weight = a.getWeight ();
			}
		case (4):
			if (choose)
			{
				a = new Boots (material [light]);
				name = a.getName ();
				ar = a.getAR ();
				weight = a.getWeight ();
			}
			else
			{
				a = new Boots (material [heavy]);
				name = a.getName ();
				ar = a.getAR ();
				weight = a.getWeight ();
			}
		}
	}

	// Prints out descriptive armor statistics, as per the usual RPG style
	public String toString ()
	{
		return name + "\nValue: " + value + "\nArmor Rating: " + ar + "\nWeight: " + weight;
	}

	// Checks if this armor is the same type as another armor, returns true if so, false if not
	public Boolean equalsTo (Armor other)
	{
		return name.equals (other.getName ());
	}

	// Getters and no Setters because once it is set, it will not be changed
	// There will be no weapon decay in this game
	public int getAR () { return ar; }
	public int getValue () { return value; }
	public int getWeight () { return weight; }
	public String getName () { return name; }
	public Armor getType () { return a; }
}