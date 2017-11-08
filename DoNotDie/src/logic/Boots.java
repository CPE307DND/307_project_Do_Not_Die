package logic;

public class Boots extends Armor
{
	Boots (String material)
	{
		type = material;
		if (material.equals ("Leather"))
		{
			ar = 5;
			value = 10;
			weight = 2;
		}
		else if (material.equals ("Chiton"))
		{
			ar = 6;
			value = 12;
			weight = 2.3;
		}
		else if (material.equals ("Iron"))
		{
			ar = 7;
			value = 15;
			weight = 4;
		}
		else if (material.equals ("Chainmail"))
		{
			ar = 7;
			value = 20;
			weight = 1.8;
		}
		else if (material.equals ("Steel"))
		{
			ar = 8;
			value = 16;
			weight = 4.5;
		}
		else if (material.equals ("Elven"))
		{
			ar = 9;
			value = 18;
			weight = 3;
		}
		else if (material.equals ("Dwarvish"))
		{
			ar = 10;
			value = 20;
			weight = 5;
		}
		else if (material.equals ("Glass"))
		{
			ar = 10;
			value = 20;
			weight = 4;
		}
		else if (material.equals ("Orcish"))
		{
			ar = 11;
			value = 22;
			weight = 5.3;
		}
		else if (material.equals ("Ancient"))
		{
			ar = 12;
			value = 24;
			weight = 5.4;
		}
		else if (material.equals ("Platemail"))
		{
			ar = 14;
			value = 26;
			weight = 5.5;
		}
		else if (material.equals ("Demonic"))
		{
			ar = 15;
			value = 30;
			weight = 6;
		}
	}
	
	public String type;
	public int ar, value;
	public double weight;
}
