package logic;

import java.util.Random;

public class Weapon extends Treasure
{
	// List of types of each weapon. Different types available based on (level / 5)
	private String [] material = {"Iron", "Steel", "Elven", "Dwarvish", "Glass", "Orcish", "Ancient", "Demonic"};
	private Weapon w;
	private int damage;
	private int value;
	private int weight;
	private String name;
	
	
	// Default constructor for Weapon
	// Needed to avoid stack overflow caused by creating instances of the subclasses
	Weapon () {}
	
	// Randomizing constructor. Pass in the desired level of the weapon
	public Weapon (int level)
	{
		Random r = new Random ();
		int piece = r.nextInt (4);
		
		// Generate a weapon type at random.
		// Due to the typing system, I have to create and store an instance of the subclass in order to be able
		// to keep its type intact
		if (piece == 0)
		{
			w = new Sword (material [level / 5]);
			name = w.getName ();
			value = w.getValue ();
			damage = w.getDamage ();
			weight = w.getWeight ();
		}
		else if (piece == 1)
		{
			w = new Dagger (material [level / 5]);
			name = w.getName ();
			value = w.getValue ();
			damage = w.getDamage ();
			weight = w.getWeight ();
		}
		else if (piece == 2)
		{
			w = new Axe (material [level / 5]);
			name = w.getName ();
			value = w.getValue ();
			damage = w.getDamage ();
			weight = w.getWeight ();
		}
		else if (piece == 3)
		{
			w = new Mace (material [level / 5]);
			name = w.getName ();
			value = w.getValue ();
			damage = w.getDamage ();
			weight = w.getWeight ();
		}
		else if (piece == 4)
		{
			w = new Warhammer (material [level / 5]);
			name = w.getName ();
			value = w.getValue ();
			damage = w.getDamage ();
			weight = w.getWeight ();
		}
	}

	// Specific constructor. Pass in the desired level and type of the weapon
	public Weapon (int level, int piece)
	{
		if (piece == 0)
		{
			w = new Sword (material [level / 5]);
			name = w.getName ();
			value = w.getValue ();
			damage = w.getDamage ();
			weight = w.getWeight ();
		}
		else if (piece == 1)
		{
			w = new Dagger (material [level / 5]);
			name = w.getName ();
			value = w.getValue ();
			damage = w.getDamage ();
			weight = w.getWeight ();
		}
		else if (piece == 2)
		{
			w = new Axe (material [level / 5]);
			name = w.getName ();
			value = w.getValue ();
			damage = w.getDamage ();
			weight = w.getWeight ();
		}
		else if (piece == 3)
		{
			w = new Mace (material [level / 5]);
			name = w.getName ();
			value = w.getValue ();
			damage = w.getDamage ();
			weight = w.getWeight ();
		}
		else if (piece == 4)
		{
			w = new Warhammer (material [level / 5]);
			name = w.getName ();
			value = w.getValue ();
			damage = w.getDamage ();
			weight = w.getWeight ();
		}
	}
	
	// Prints out descriptive weapon statistics, as per the usual RPG style
	public String toString ()
	{
		return name + "\nValue: " + value + "\nDamage: " + damage + "\nWeight: " + weight + "\n";
	}
	
	// Checks if this weapon is the same type as another weapon, returns true if so, false if not
	public Boolean equals (Weapon other)
	{
		return name.equals (other.getName ());
	}
	
	// Getters and no Setters because once it is set, it will not be changed
	// There will be no weapon decay in this game
	public int getDamage () { return damage; }
	public int getValue () { return value; }
	public int getWeight () { return weight; }
	public String getName () { return name; }
	public Weapon getType () { return w; }
}
