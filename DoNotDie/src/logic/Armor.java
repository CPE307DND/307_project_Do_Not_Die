package logic;

import java.util.Random;

public class Armor extends Treasure
{
	Armor ()
	{
		Random r = new Random ();
		new Armor (r.nextInt (30));
	}
	
	Armor (int level)
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
					new Helm (material[light]);
				else
					new Helm (material [heavy]);
			case (1):
				if (choose)
					new Cuirass (material [light]);
				else
					new Cuirass (material [heavy]);
			case (2):
				if (choose)
					new Gauntlets (material [light]);
				else
					new Gauntlets (material [heavy]);
			case (3):
				if (choose)
					new Greaves (material [light]);
				else
					new Greaves (material [heavy]);
			case (4):
				if (choose)
					new Boots (material [light]);
				else
					new Boots (material [heavy]);
		}
	}
	
	public Boolean equals (Armor other)
	{
		return this.name.equals (other.name);
	}
	
	private String [] material = {"Chiton", "Leather", "Iron", "Chainmail",
			"Steel", "Elven", "Dwarvish", "Glass", "Orcish", "Ancient", "Demonic", "Platemail"};
	
	private int light, heavy;
	
	public int value, ar, weight;
	public String name;
}
