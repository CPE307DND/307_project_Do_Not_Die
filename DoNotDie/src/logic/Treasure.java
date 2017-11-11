package logic;

import java.util.Random;

public class Treasure
{
	Treasure () {}
	
	public Treasure (int level)
	{
		int i = new Random ().nextInt ();
		
		switch (i)
		{
			default:
				new logic.Misc (level * 10);
			case (1):
				new logic.Armor (level * 10);
			case (2):
				new logic.Weapon (level * 10);
		}
	}
	
	public Boolean equals (Weapon other)
	{
		return name.equals (other.getName ());
	}
	
	public Boolean equals (Armor other)
	{
		return name.equals (other.getName ());
	}
	
	public Boolean equals (Misc other)
	{
		return (name.equals (other.getName ()) && (value == other.getValue ()));
	}
	
	public int getValue () { return value; }
	public int getWeight () { return weight; }
	public String getName () { return name; }
	
	private String name;
	private int value, weight;
}