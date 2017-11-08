package logic;

public class Cuirass extends Armor
{
	Cuirass (String material)
	{
		type = material;
		if (material.equals ("Leather"))
		{
			ar = 10;
			value = 25;
			weight = 7;
		}
		else if (material.equals ("Chiton"))
		{
			ar = 11;
			value = 20;
			weight = 7.5;
		}
		else if (material.equals ("Iron"))
		{
			ar = 20;
			value = 35;
			weight = 15;
		}
		else if (material.equals ("Chainmail"))
		{
			ar = 15;
			value = 28;
			weight = 6;
		}
		else if (material.equals ("Steel"))
		{
			ar = 22;
			value = 40;
			weight = 28;
		}
		else if (material.equals ("Elven"))
		{
			ar = 19;
			value = 50;
			weight = 10;
		}
		else if (material.equals ("Dwarvish"))
		{
			ar = 28;
			value = 60;
			weight = 30;
		}
		else if (material.equals ("Glass"))
		{
			ar = 22;
			value = 55;
			weight = 15;
		}
		else if (material.equals ("Orcish"))
		{
			ar = 30;
			value = 70;
			weight = 35;
		}
		else if (material.equals ("Ancient"))
		{
			ar = 32;
			value = 65;
			weight = 36;
		}
		else if (material.equals ("Platemail"))
		{
			ar = 33;
			value = 80;
			weight = 25;
		}
		else if (material.equals ("Demonic"))
		{
			ar = 40;
			value = 120;
			weight = 55;
		}
	}
	
	public String type;
	public int ar, value;
	public double weight;
}
