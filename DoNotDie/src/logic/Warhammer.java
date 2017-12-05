package logic;

public class Warhammer extends Weapon
{
	private int damage;
	private int value;
	private int weight;
	private String name;
	
	// No default constructor, because this cannot be randomized. Pass in material type to create
	public Warhammer (String type)
	{
		if (type.equals ("Iron"))
		{
			name = "Iron Warhammer";
			value = 15;
			damage = 10;
			weight = 10;
		}
		else if (type.equals ("Steel"))
		{
			name = "Steel Warhammer";
			value = 20;
			damage = 15;
			weight = 13;
		}
		else if (type.equals ("Elven"))
		{
			name = "Elven Warhammer";
			value = 25;
			damage = 18;
			weight = 15;
		}
		else if (type.equals ("Dwarvish"))
		{
			name = "Dwarvish Warhammer";
			value = 30;
			damage = 23;
			weight = 18;
		}
		else if (type.equals ("Glass"))
		{
			name = "Glass Warhammer";
			value = 35;
			damage = 25;
			weight = 19;
		}
		else if (type.equals ("Orcish"))
		{
			name = "Orcish Warhammer";
			value = 40;
			damage = 28;
			weight = 21;
		}
		else if (type.equals ("Ancient"))
		{
			name = "Ancient Warhammer";
			value = 60;
			damage = 31;
			weight = 25;
		}
		else if (type.equals ("Demonic"))
		{
			name = "Demonic Warhammer";
			value = 70;
			damage = 37;
			weight = 30;
		}
	}
	
	// Prints out descriptive weapon statistics, as per the usual RPG style
	public String toString ()
	{
		return name + "\nValue: " + value + "\nDamage: " + damage + "\nWeight: " + weight;
	}

	// Exactly the same as Weapon's equals, for redundancy
	public Boolean equalsTo (Warhammer other)
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