package logic;

import java.util.Random;

public class Treasure
{
	Treasure ()
	{
		new Treasure (new Random ().nextInt (30));
	}
	
	Treasure (int level)
	{
		int i = new Random ().nextInt ();
		
		switch (i)
		{
			case (0):
				new logic.Misc (level * 10);
			case (1):
				new logic.Armor (level * 10);
			case (2):
				new logic.Weapon (level * 10);
		}
	}
	
	public Boolean equals (Treasure other)
	{
		return this.name.equals (other.name);
	}
	
	public String name;
	public int value, weight;
}