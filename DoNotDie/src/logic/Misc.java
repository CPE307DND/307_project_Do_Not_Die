package logic;

public class Misc extends Treasure
{
	Misc ()
	{
		Misc ((int)(Math.random () * 10));
	}
	
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
			name = types [(int) ((Math.random () * 30) / 10)];
			if (name.equals (types [0]))
				weight = 0;
			else
				weight = 0.1;
		}
		else if (val < 40)
		{
			name = types [3 + (int) ((Math.random () * 50) / 10)];
			if (name.equals (types [0]))
				weight = 0;
			else
				weight = 0.2;
		}
		else if (val < 70)
		{
			name = types [8 + (int) ((Math.random () * 30) / 10];
			if (name.equals (types [0]))
				weight = 0;
			else
				weight = 0.3;
		}
		else if (val < 130)
		{
			name = types [11 + (int) ((Math.random () * 30) /10)];
			if (name.equals (types [0]))
				weight = 0;
			else
				weight = 0.4;
		}
		else if (val < 200)
		{
			name = types [14 + (int) ((Math.random () * 40) / 10)];
			if (name.equals (types [0]))
				weight = 0;
			else
				weight = 0.5;
		}
	}
	
	private String [] types = {"Gold", "Brass Ring", "Copper Ring", "Flawed Pearl", "Silver Ring", "Pearl",
			"Flawed Ruby", "Flawed Sapphire", "Flawed Emerald", "Gold Ring", "Copper Ingot", "Ruby",
			"Sapphire", "Emerald", "Flawed Diamond", "Diamond", "Silver Ingot", "Gold Ingot", "Platinum Ingot"};
	public int value;
	public double weight;
	public String name;
}
