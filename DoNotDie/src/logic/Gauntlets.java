package logic;

public class Gauntlets extends Armor
{
	private int ar;
	private int value;
	private int weight;
	private String name;
	
	// No default constructor, because this cannot be randomized. Pass in material type to create
	public Gauntlets (String material)
	{
		if (material.equals ("Leather"))
		{
			name = "Leather Gauntlets";
			ar = 5;
			value = 10;
			weight = 2;
		}
		else if (material.equals ("Chiton"))
		{
			name = "Chiton Gauntlets";
			ar = 6;
			value = 12;
			weight = 3;
		}
		else if (material.equals ("Iron"))
		{
			name = "Iron Gauntlets";
			ar = 7;
			value = 15;
			weight = 4;
		}
		else if (material.equals ("Chainmail"))
		{
			name = "Chainmail Gauntlets";
			ar = 7;
			value = 20;
			weight = 2;
		}
		else if (material.equals ("Steel"))
		{
			name = "Steel Gauntlets";
			ar = 8;
			value = 16;
			weight = 4;
		}
		else if (material.equals ("Elven"))
		{
			name = "Elven Gauntlets";
			ar = 9;
			value = 18;
			weight = 3;
		}
		else if (material.equals ("Dwarvish"))
		{
			name = "Dwarvish Gauntlets";
			ar = 10;
			value = 20;
			weight = 5;
		}
		else if (material.equals ("Glass"))
		{
			name = "Glass Gauntlets";
			ar = 10;
			value = 20;
			weight = 4;
		}
		else if (material.equals ("Orcish"))
		{
			name = "Orcish Gauntlets";
			ar = 11;
			value = 22;
			weight = 5;
		}
		else if (material.equals ("Ancient"))
		{
			name = "Ancient Gauntlets";
			ar = 12;
			value = 24;
			weight = 5;
		}
		else if (material.equals ("Platemail"))
		{
			name = "Platemail Gauntlets";
			ar = 14;
			value = 26;
			weight = 6;
		}
		else if (material.equals ("Demonic"))
		{
			name = "Demonic Gauntlets";
			ar = 15;
			value = 30;
			weight = 6;
		}
	}

	// Exactly the same as Armor's toString, for redundancy
	public String toString ()
	{
		return name + "\nValue: " + value + "\nArmor Rating: " + ar + "\nWeight: " + weight;
	}

	// Exactly the same as Armor's equals, for redundancy
	public Boolean equals (Gauntlets other)
	{
		return name.equals (other.getName ());
	}

	// Getters and no Setters because once it is set, it will not be changed
	// There will be no weapon decay in this game
	public int getAR () { return ar; }
	public int getValue () { return value; }
	public int getWeight () { return weight; }
	public String getName () { return name; }
}