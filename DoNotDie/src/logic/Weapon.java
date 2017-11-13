package logic;

import java.util.Random;

public class Weapon extends Treasure
{
	Weapon () {}
	
	public Weapon (int level)
	{
		Random r = new Random ();
		int piece = r.nextInt (4);
		
		
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
	
	public String toString ()
	{
		return name + "\nValue: " + value + "\nDamage: " + damage + "\nWeight: " + weight + "\n";
	}
	
	public Boolean equals (Weapon other)
	{
		return name.equals (other.getName ());
	}
	
	public int getDamage () { return damage; }
	public int getValue () { return value; }
	public int getWeight () { return weight; }
	public String getName () { return name; }
	public Weapon getType () { return w; }
	
	
	private String [] material = {"Iron", "Steel", "Elven", "Dwarvish", "Glass", "Orcish", "Ancient", "Demonic"};
	private Weapon w;
	private int value, damage, weight;
	private String name;
}
