package logic;

public class Greaves extends Armor
{
	private int ar;
	private int value;
	private int weight;
	private String name;
	
	// No default constructor, because this cannot be randomized. Pass in material type to create
	public Greaves (String material)
	{
		if (material.equals ("Leather"))
		{
			name = "Leather Greaves";
			ar = 5;
			value = 25;
			weight = 5;
		}
		else if (material.equals ("Chiton"))
		{
			name = "Chiton Greaves";
			ar = 6;
			value = 20;
			weight = 6;
		}
		else if (material.equals ("Iron"))
		{
			name = "Iron Greaves";
			ar = 15;
			value = 35;
			weight = 13;
		}
		else if (material.equals ("Chainmail"))
		{
			name = "Chainmail Greaves";
			ar = 10;
			value = 28;
			weight = 5;
		}
		else if (material.equals ("Steel"))
		{
			name = "Steel Greaves";
			ar = 17;
			value = 40;
			weight = 26;
		}
		else if (material.equals ("Elven"))
		{
			name = "Elven Greaves";
			ar = 14;
			value = 50;
			weight = 8;
		}
		else if (material.equals ("Dwarvish"))
		{
			name = "Dwarvish Greaves";
			ar = 23;
			value = 60;
			weight = 28;
		}
		else if (material.equals ("Glass"))
		{
			name = "Glass Greaves";
			ar = 17;
			value = 55;
			weight = 12;
		}
		else if (material.equals ("Orcish"))
		{
			name = "Orcish Greaves";
			ar = 25;
			value = 70;
			weight = 33;
		}
		else if (material.equals ("Ancient"))
		{
			name = "Ancient Greaves";
			ar = 27;
			value = 65;
			weight = 34;
		}
		else if (material.equals ("Platemail"))
		{
			name = "Platemail Greaves";
			ar = 28;
			value = 80;
			weight = 38;
		}
		else if (material.equals ("Demonic"))
		{
			name = "Demonic Greaves";
			ar = 35;
			value = 120;
			weight = 45;
		}
	}
	
	// Prints out descriptive armor statistics, as per the usual RPG style
	public String toString ()
	{
		return name + "\nValue: " + value + "\nArmor Rating: " + ar + "\nWeight: " + weight;
	}

	// Exactly the same as Armor's equals, for redundancy
	public Boolean equalsTo (Greaves other)
	{
		return name.equals (other.getName ());
	}

	// Getters and no Setters because once it is set, it will not be changed
	// There will be no armor decay in this game
	public int getAR () { return ar; }
	public int getValue () { return value; }
	public int getWeight () { return weight; }
	public String getName () { return name; }
}