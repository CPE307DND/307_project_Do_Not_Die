package logic;

public class Boots extends Armor
{
	public Boots (String material)
	{
		if (material.equals ("Leather"))
		{
			name = "Leather Boots";
			ar = 5;
			value = 10;
			weight = 2;
		}
		else if (material.equals ("Chiton"))
		{
			name = "Chiton Boots";
			ar = 6;
			value = 12;
			weight = 3;
		}
		else if (material.equals ("Iron"))
		{
			name = "Iron Boots";
			ar = 7;
			value = 15;
			weight = 4;
		}
		else if (material.equals ("Chainmail"))
		{
			name = "Chainmail Boots";
			ar = 7;
			value = 20;
			weight = 2;
		}
		else if (material.equals ("Steel"))
		{
			name = "Steel Boots";
			ar = 8;
			value = 16;
			weight = 4;
		}
		else if (material.equals ("Elven"))
		{
			name = "Elven Boots";
			ar = 9;
			value = 18;
			weight = 3;
		}
		else if (material.equals ("Dwarvish"))
		{
			name = "Dwarvish Boots";
			ar = 10;
			value = 20;
			weight = 5;
		}
		else if (material.equals ("Glass"))
		{
			name = "Glass Boots";
			ar = 10;
			value = 20;
			weight = 4;
		}
		else if (material.equals ("Orcish"))
		{
			name = "Orcish Boots";
			ar = 11;
			value = 22;
			weight = 5;
		}
		else if (material.equals ("Ancient"))
		{
			name = "Ancient Boots";
			ar = 12;
			value = 24;
			weight = 5;
		}
		else if (material.equals ("Platemail"))
		{
			name = "Platemail Boots";
			ar = 14;
			value = 26;
			weight = 6;
		}
		else if (material.equals ("Demonic"))
		{
			name = "Demonic Boots";
			ar = 15;
			value = 30;
			weight = 6;
		}
	}

	public String toString ()
	{
		return name + "\nValue: " + value + "\nArmor Rating: " + ar + "\nWeight: " + weight;
	}
	
	public Boolean equals (Boots other)
	{
		return name.equals (other.getName ());
	}
	
	public int getAR () { return ar; }
	public int getValue () { return value; }
	public int getWeight () { return weight; }
	public String getName () { return name; }
	
	private int ar, value, weight;
	private String name;
}
