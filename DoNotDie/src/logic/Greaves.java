package logic;

public class Greaves extends Armor
{
	Greaves (String material)
	{
		type = material;
		if (material.equals ("Leather"))
		{
			ar = 5;
			value = 25;
			weight = 5;
		}
		else if (material.equals ("Chiton"))
		{
			ar = 6;
			value = 20;
			weight = 5.5;
		}
		else if (material.equals ("Iron"))
		{
			ar = 15;
			value = 35;
			weight = 13;
		}
		else if (material.equals ("Chainmail"))
		{
			ar = 10;
			value = 28;
			weight = 4;
		}
		else if (material.equals ("Steel"))
		{
			ar = 17;
			value = 40;
			weight = 26;
		}
		else if (material.equals ("Elven"))
		{
			ar = 14;
			value = 50;
			weight = 8;
		}
		else if (material.equals ("Dwarvish"))
		{
			ar = 23;
			value = 60;
			weight = 28;
		}
		else if (material.equals ("Glass"))
		{
			ar = 17;
			value = 55;
			weight = 12;
		}
		else if (material.equals ("Orcish"))
		{
			ar = 25;
			value = 70;
			weight = 33;
		}
		else if (material.equals ("Ancient"))
		{
			ar = 27;
			value = 65;
			weight = 34;
		}
		else if (material.equals ("Platemail"))
		{
			ar = 28;
			value = 80;
			weight = 38;
		}
		else if (material.equals ("Demonic"))
		{
			ar = 35;
			value = 120;
			weight = 45;
		}
	}
	
	public String type;
	public int ar, value;
	public double weight;
}
