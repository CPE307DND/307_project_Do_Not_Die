package logic;

public class Cuirass extends Armor
{
	Cuirass (String material)
	{
		if (material.equals ("Leather"))
		{
			name = "Leather Cuirass";
			ar = 10;
			value = 25;
			weight = 7;
		}
		else if (material.equals ("Chiton"))
		{
			name = "Chiton Cuirass";
			ar = 11;
			value = 20;
			weight = 8;
		}
		else if (material.equals ("Iron"))
		{
			name = "Iron Cuirass";
			ar = 20;
			value = 35;
			weight = 15;
		}
		else if (material.equals ("Chainmail"))
		{
			name = "Chainmail Cuirass";
			ar = 15;
			value = 28;
			weight = 6;
		}
		else if (material.equals ("Steel"))
		{
			name = "Steel Cuirass";
			ar = 22;
			value = 40;
			weight = 28;
		}
		else if (material.equals ("Elven"))
		{
			name = "Elven Cuirass";
			ar = 19;
			value = 50;
			weight = 10;
		}
		else if (material.equals ("Dwarvish"))
		{
			name = "Dwarvish Cuirass";
			ar = 28;
			value = 60;
			weight = 30;
		}
		else if (material.equals ("Glass"))
		{
			name = "Glass Cuirass";
			ar = 22;
			value = 55;
			weight = 15;
		}
		else if (material.equals ("Orcish"))
		{
			name = "Orcish Cuirass";
			ar = 30;
			value = 70;
			weight = 35;
		}
		else if (material.equals ("Ancient"))
		{
			name = "Ancient Cuirass";
			ar = 32;
			value = 65;
			weight = 36;
		}
		else if (material.equals ("Platemail"))
		{
			name = "Platemail Cuirass";
			ar = 33;
			value = 80;
			weight = 25;
		}
		else if (material.equals ("Demonic"))
		{
			name = "Demonic Cuirass";
			ar = 40;
			value = 120;
			weight = 55;
		}
	}
	
	public Boolean equals (Cuirass other)
	{
		return this.name.equals (other.name);
	}
	
	public String name;
	public int ar, value, weight;
}
