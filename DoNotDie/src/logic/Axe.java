package logic;

public class Axe extends Weapon
{
	Axe (String type)
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
	
	public Boolean equals (Axe other)
	{
		return this.name.equals (other.name);
	}
	
	public int value, damage, weight;
	public String name;
}