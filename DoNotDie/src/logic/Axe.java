package logic;

public class Axe extends Weapon
{
	private int damage;
	private int value;
	private int weight;
	private String name;
	
	// No default constructor, because this cannot be randomized. Pass in material type to create
	public Axe (String type)
	{
		if (type.equals ("Iron"))
		{
			name = "Iron Axe";
			value = 10;
			damage = 9;
			weight = 9;
		}
		else if (type.equals ("Steel"))
		{
			name = "Steel Axe";
			value = 15;
			damage = 13;
			weight = 11;
		}
		else if (type.equals ("Elven"))
		{
			name = "Elven Axe";
			value = 20;
			damage = 16;
			weight = 11;
		}
		else if (type.equals ("Dwarvish"))
		{
			name = "Dwarvish Axe";
			value = 25;
			damage = 19;
			weight = 14;
		}
		else if (type.equals ("Glass"))
		{
			name = "Glass Axe";
			value = 30;
			damage = 22;
			weight = 15;
		}
		else if (type.equals ("Orcish"))
		{
			name = "Orcish Axe";
			value = 35;
			damage = 24;
			weight = 17;
		}
		else if (type.equals ("Ancient"))
		{
			name = "Ancient Axe";
			value = 50;
			damage = 28;
			weight = 21;
		}
		else if (type.equals ("Demonic"))
		{
			name = "Demonic Axe";
			value = 60;
			damage = 35;
			weight = 26;
		}
	}
	
	// Prints out descriptive weapon statistics, as per the usual RPG style
	public String toString ()
	{
		return name + "\nValue: " + value + "\nDamage: " + damage + "\nWeight: " + weight;
	}
	
	// Exactly the same as Armor's equals, for redundancy
	public Boolean equalsTo (Axe other)
	{
		return name.equals (other.getName ());
	}

	// Getters and no Setters because once it is set, it will not be changed
	// There will be no weapon decay in this game
	public int getDamage () { return damage; }
	public int getValue () { return value; }
	public int getWeight () { return weight; }
	public String getName () { return name; }
}