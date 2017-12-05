package logic;

public class Cuirass extends Armor
{
	private int ar;
	private int value;
	private int weight;
	private String name;
	
	// No default constructor, because this cannot be randomized. Pass in material type to create
	public Cuirass (String material)
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

	// Exactly the same as Armor's toString, for redundancy
	public String toString ()
	{
		return name + "\nValue: " + value + "\nArmor Rating: " + ar + "\nWeight: " + weight;
	}

	// Exactly the same as Armor's equals, for redundancy
	public Boolean equalsTo (Cuirass other)
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