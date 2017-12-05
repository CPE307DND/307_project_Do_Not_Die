package logic;

import java.util.Random;

public class Treasure
{
	private Treasure trs;
	private int value;
	private int weight;
	private String name;
	
	// Default constructor for Treasure
	// Needed to avoid stack overflow caused by creating instances of the subclasses
	Treasure () {}

	// Randomizing constructor. Pass in the desired level of the treasure
	public Treasure (int level)
	{
		int type = new Random ().nextInt ();
		
		if (type == 0)
			trs = new logic.Misc (level * 10);
		else if (type == 1)
			trs = new logic.Armor (level * 10);
		else
			trs = new logic.Weapon (level * 10);
		
		name = trs.getName ();
		value = trs.getValue ();
		weight = trs.getWeight ();
	}

	// Specific constructor. Pass in the desired level and type of the treasure
	public Treasure (int level, int type)
	{
		if (type == 0)
			trs = new logic.Misc (level * 10);
		else if (type == 1)
			trs = new logic.Armor (level * 10);
		else
			trs = new logic.Weapon (level * 10);
		
		name = trs.getName ();
		value = trs.getValue ();
		weight = trs.getWeight ();
	}
	
	// For some reason, the equals method didn't work unless I had a seperate version for all 3 subtypes
	// Check if it is equal to a weapon
	public Boolean equalsTo (Weapon other)
	{
		return name.equals (other.getName ());
	}
	 // Check if it is equal to an armor
	public Boolean equalsTo (Armor other)
	{
		return name.equals (other.getName ());
	}
	// Check if it is equal to a misc
	public Boolean equalsTo (Misc other)
	{
		return (name.equals (other.getName ()) && (value == other.getValue ()));
	}

	// Getters and no Setters because once it is set, it will not be changed
	// There will be no weapon or armor decay in this game
	public int getValue () { return value; }
	public int getWeight () { return weight; }
	public String getName () { return name; }
	public Treasure getType () { return trs; }
}