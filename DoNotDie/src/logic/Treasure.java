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
			{
				type = new logic.Misc (level * 10);
				break;
			}
			case (1):
			{
				type = new logic.Armor (level * 10);
				break;
			}
			case (2):
			{
				type = new logic.Weapon (level * 10);
				break;
			}
		}
		
		name = type.getName ();
		value = type.getValue ();
		weight = type.getWeight ();
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
	
	private Treasure type;
	private String name;
	private int value, weight;
}