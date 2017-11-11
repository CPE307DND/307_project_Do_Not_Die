package logic;

public class Dagger extends Weapon
{
	public Dagger (String type)
	{
		System.out.println (type);
		
		if (type.equals ("Iron"))
		{
			name = "Iron Dagger";
			value = 10;
			damage = 4;
			weight = 2;
		}
		else if (type.equals ("Steel"))
		{
			name = "Steel Dagger";
			value = 15;
			damage = 5;
			weight = 3;
		}
		else if (type.equals ("Elven"))
		{
			name = "Elven Dagger";
			value = 20;
			damage = 6;
			weight = 3;
		}
		else if (type.equals ("Dwarvish"))
		{
			name = "Dwarvish Dagger";
			value = 25;
			damage = 7;
			weight = 4;
		}
		else if (type.equals ("Glass"))
		{
			name = "Glass Dagger";
			value = 30;
			damage = 8;
			weight = 5;
		}
		else if (type.equals ("Orcish"))
		{
			name = "Orcish Dagger";
			value = 35;
			damage = 10;
			weight = 6;
		}
		else if (type.equals ("Ancient"))
		{
			name = "Ancient Dagger";
			value = 50;
			damage = 11;
			weight = 7;
		}
		else if (type.equals ("Demonic"))
		{
			name = "Demonic Dagger";
			value = 60;
			damage = 15;
			weight = 8;
		}
	}
	
	public String toString ()
	{
		return name + "\nValue: " + value + "\nDamage: " + damage + "\nWeight: " + weight;
	}
	
	public Boolean equals (Dagger other)
	{
		return name.equals (other.getName ());
	}
	
	public int getDamage () { return damage; }
	public int getValue () { return value; }
	public int getWeight () { return weight; }
	public String getName () { return name; }
	
	private int value, damage, weight;
	private String name;
}
