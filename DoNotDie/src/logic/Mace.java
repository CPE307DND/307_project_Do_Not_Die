package logic;

public class Mace extends Weapon
{
	Mace (String type)
	{
		if (type.equals ("Iron"))
		{
			name = "Iron Mace";
			value = 10;
			damage = 8;
			weight = 8;
		}
		else if (type.equals ("Steel"))
		{
			name = "Steel Mace";
			value = 15;
			damage = 12;
			weight = 10;
		}
		else if (type.equals ("Elven"))
		{
			name = "Elven Mace";
			value = 20;
			damage = 15;
			weight = 10;
		}
		else if (type.equals ("Dwarvish"))
		{
			name = "Dwarvish Mace";
			value = 25;
			damage = 18;
			weight = 13;
		}
		else if (type.equals ("Glass"))
		{
			name = "Glass Mace";
			value = 30;
			damage = 21;
			weight = 14;
		}
		else if (type.equals ("Orcish"))
		{
			name = "Orcish Mace";
			value = 35;
			damage = 23;
			weight = 16;
		}
		else if (type.equals ("Ancient"))
		{
			name = "Ancient Mace";
			value = 50;
			damage = 27;
			weight = 20;
		}
		else if (type.equals ("Demonic"))
		{
			name = "Demonic Mace";
			value = 60;
			damage = 34;
			weight = 25;
		}
	}
	
	public Boolean equals (Mace other)
	{
		return this.name.equals (other.name);
	}
	
	public int value, damage, weight;
	public String name;
}
