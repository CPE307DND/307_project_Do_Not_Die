package logic;

import java.util.Random;

public class Weapon extends Treasure
{
	Weapon () {}
	
	Weapon (int level)
	{
		Random r = new Random ();
		int piece = r.nextInt (4);
		
		switch (piece)
		{
			default:
				new Sword (material [level / 5]);
			case (1):
				new Dagger (material [level / 5]);
			case (2):
				new Axe (material [level / 5]);
			case (3):
				new Mace (material [level / 5]);
			case (4):
				new Warhammer (material [level / 5]);
		}
	}
	
	public Boolean equals (Weapon other)
	{
		return name.equals (other.name);
	}
	
	private String [] material = {"Iron", "Steel", "Elven", "Dwarvish", "Glass", "Orcish", "Ancient", "Demonic"};
	
	public int value, damage, weight;
	public String name;
}
