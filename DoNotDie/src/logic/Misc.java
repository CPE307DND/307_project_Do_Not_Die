package logic;

import java.util.Random;

class Misc extends Treasure
{
	// All the different misc items you can get. Not at all balanced
	private String [] types = {"Gold", "Brass Ring", "Copper Ring", "Flawed Pearl", "Silver Ring", "Pearl",
			"Flawed Ruby", "Flawed Sapphire", "Flawed Emerald", "Gold Ring", "Copper Ingot", "Ruby",
			"Sapphire", "Emerald", "Flawed Diamond", "Diamond", "Silver Ingot", "Gold Ingot", "Platinum Ingot"};
	private int value, weight;
	private String name;
	// Randomizing constructor. Pass in the desired value of the misc object
	public Misc (int val)
	{
		value = val;
		
		if (val < 10)
		{
			name = types [0];
			weight = 0;
		}
		else if (val < 20)
		{
			name = types [new Random ().nextInt (3)];
			if (name.equals (types [0]))
				weight = 0;
			else
				weight = 0;
		}
		else if (val < 40)
		{
			name = types [3 + new Random ().nextInt (5)];
			if (name.equals (types [0]))
				weight = 0;
			else
				weight = 1;
		}
		else if (val < 70)
		{
			name = types [8 + new Random ().nextInt (3)];
			if (name.equals (types [0]))
				weight = 0;
			else
				weight = 1;
		}
		else if (val < 130)
		{
			name = types [11 + new Random ().nextInt (3)];
			if (name.equals (types [0]))
				weight = 0;
			else
				weight = 2;
		}
		else if (val < 200)
		{
			name = types [14 + new Random ().nextInt (4)];
			if (name.equals (types [0]))
				weight = 0;
			else
				weight = 2;
		}
	}
	
	public String toString ()
	{
		return name + "\nValue: " + value + "\nWeight: " + weight + "\n";
	}
	
	// Checks if the name and the value are the same, because several items can have different values,
	// despite being the same item
	public Boolean equals (Misc other)
	{
		return (name.equals (other.name) && (value == other.value));
	}

	// Getters and no Setters because once it is set, it will not be changed
	public int getValue () { return value; }
	public int getWeight () { return weight; }
	public String getName () { return name; }
}