package logic;

import java.util.Random;

class Misc extends Treasure
{
	Misc () {}
	
	Misc (int val)
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
				weight = 0.1;
		}
		else if (val < 40)
		{
			name = types [3 + new Random ().nextInt (5)];
			if (name.equals (types [0]))
				weight = 0;
			else
				weight = 0.2;
		}
		else if (val < 70)
		{
			name = types [8 + new Random ().nextInt (3)];
			if (name.equals (types [0]))
				weight = 0;
			else
				weight = 0.3;
		}
		else if (val < 130)
		{
			name = types [11 + new Random ().nextInt (3)];
			if (name.equals (types [0]))
				weight = 0;
			else
				weight = 0.4;
		}
		else if (val < 200)
		{
			name = types [14 + new Random ().nextInt (4)];
			if (name.equals (types [0]))
				weight = 0;
			else
				weight = 0.5;
		}
	}
	
	public Boolean equals (Misc other)
	{
		return (name.equals (other.name) && (value == other.value));
	}
	
	private String [] types = {"Gold", "Brass Ring", "Copper Ring", "Flawed Pearl", "Silver Ring", "Pearl",
			"Flawed Ruby", "Flawed Sapphire", "Flawed Emerald", "Gold Ring", "Copper Ingot", "Ruby",
			"Sapphire", "Emerald", "Flawed Diamond", "Diamond", "Silver Ingot", "Gold Ingot", "Platinum Ingot"};
	public int value;
	public double weight;
	public String name;
}
