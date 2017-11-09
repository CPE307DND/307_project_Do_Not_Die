package logic;

public class Helm extends Armor
{
	Helm (String material)
	{
		if (material.equals ("Leather"))
		{
			name = "Leather Helm";
			ar = 5;
			value = 10;
			weight = 2;
		}
		else if (material.equals ("Chiton"))
		{
			name = "Chiton Helm";
			ar = 6;
			value = 12;
			weight = 3;
		}
		else if (material.equals ("Iron"))
		{
			name = "Iron Helm";
			ar = 7;
			value = 15;
			weight = 4;
		}
		else if (material.equals ("Chainmail"))
		{
			name = "Chainmail Helm";
			ar = 7;
			value = 20;
			weight = 2;
		}
		else if (material.equals ("Steel"))
		{
			name = "Steel Helm";
			ar = 8;
			value = 16;
			weight = 4;
		}
		else if (material.equals ("Elven"))
		{
			name = "Elven Helm";
			ar = 9;
			value = 18;
			weight = 3;
		}
		else if (material.equals ("Dwarvish"))
		{
			name = "Dwarvish Helm";
			ar = 10;
			value = 20;
			weight = 5;
		}
		else if (material.equals ("Glass"))
		{
			name = "Glass Helm";
			ar = 10;
			value = 20;
			weight = 4;
		}
		else if (material.equals ("Orcish"))
		{
			name = "Orcish Helm";
			ar = 11;
			value = 22;
			weight = 5;
		}
		else if (material.equals ("Ancient"))
		{
			name = "Ancient Helm";
			ar = 12;
			value = 24;
			weight = 5;
		}
		else if (material.equals ("Platemail"))
		{
			name = "Platemail Helm";
			ar = 14;
			value = 26;
			weight = 6;
		}
		else if (material.equals ("Demonic"))
		{
			name = "Demonic Helm";
			ar = 15;
			value = 30;
			weight = 6;
		}
	}
	
	public Boolean equals (Helm other)
	{
		return this.name.equals (other.name);
	}
	
	public String name;
	public int ar, value, weight;
}
