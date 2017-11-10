package logic;

public class Warhammer extends Weapon
{
	Warhammer (String type)
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
	
	public Boolean equals (Warhammer other)
	{
		return this.name.equals (other.name);
	}
	
	public int value, damage, weight;
	public String name;
}