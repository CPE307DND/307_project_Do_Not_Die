package logic;

import java.util.ArrayList;
import java.util.Random;

public class Enemy
{
	public Enemy (int t, Boolean g, int STR, int END, int INT, int WIL, int AGL, int SPD, int LCK)
	{
		if (t == 1)
		{
			AC = 3;
			race = "Elf";
			gender = g;
			Strength = STR - 2;
			Endurance = END;
			Intelligence = INT + 5;
			Willpower = WIL + 5;
			Agility = AGL + 2;
			Speed = SPD + 1;
			Luck = LCK + 2;
		}
		else if (t == 2)
		{
			AC = 7;
			race = "Orc";
			gender = g;
			Strength = STR + 4;
			Endurance = END + 3;
			Intelligence = INT - 3;
			Willpower = WIL - 4;
			Agility = AGL - 4;
			Speed = SPD - 4;
			Luck = LCK - 1;
		}
		else if (t == 3)
		{
			AC = 1;
			race = "Gnome";
			gender = g;
			Strength = STR - 7;
			Endurance = END - 7;
			Intelligence = INT + 7;
			Willpower = WIL + 6;
			Agility = AGL - 4;
			Speed = SPD - 4;
			Luck = LCK + 4;
		}
		else if (t == 4)
		{
			AC = 6;
			race = "Dwarf";
			gender = g;
			Strength = STR + 3;
			Endurance = END + 1;
			Intelligence = INT - 2;
			Willpower = WIL - 3;
			Agility = AGL - 1;
			Speed = SPD - 3;
			Luck = LCK + 2;
		}
		else if (t == 5)
		{
			AC = 8;
			race = "Dragonborn";
			gender = g;
			Strength = STR + 5;
			Endurance = END + 6;
			Intelligence = INT + 4;
			Willpower = WIL - 5;
			Agility = AGL - 3;
			Speed = SPD + 3;
			Luck = LCK + 3;
		}
		else if (t == 6)
		{
			AC = 8;
			race = "Half-Troll";
			gender = g;
			Strength = STR + 7;
			Endurance = END + 8;
			Intelligence = INT - 10;
			Willpower = WIL - 7;
			Agility = AGL - 5;
			Speed = SPD + 4;
			Luck = LCK - 4;
		}
		else if (t == 7)
		{
			AC = 6;
			race = "Lizard-Folk";
			gender = g;
			Strength = STR;
			Endurance = END + 2;
			Intelligence = INT - 1;
			Willpower = WIL + 1;
			Agility = AGL + 3;
			Speed = SPD;
			Luck = LCK + 3;
		}
		else if (t == 8)
		{
			AC = 4;
			race = "Cat-Folk";
			gender = g;
			Strength = STR;
			Endurance = END;
			Intelligence = INT;
			Willpower = WIL;
			Agility = AGL + 8;
			Speed = SPD + 8;
			Luck = LCK + 6;
		}
		else if (t == 9)
		{
			AC = 4;
			race = "Tiefling";
			gender = g;
			Strength = STR + 1;
			Endurance = END - 2;
			Intelligence = INT + 3;
			Willpower = WIL + 2;
			Agility = AGL - 3;
			Speed = SPD - 3;
			Luck = LCK + 7;
		}
		else
		{
			AC = 5;
			race = "Human";
			gender = g;
			Strength = STR;
			Endurance = END;
			Intelligence = INT;
			Willpower = WIL;
			Agility = AGL;
			Speed = SPD;
			Luck = LCK;
		}
		
		if (gender)
		{
			Strength++;
			Endurance++;
			Speed++;
			Intelligence--;
			Willpower--;
			Agility--;
		}
		else
		{
			Strength--;
			Endurance--;
			Speed--;
			Intelligence++;
			Willpower++;
			Agility++;
		}
		health += (Endurance * 5);
		maxhealth = health;
		damage += ((Strength * 5) + (Intelligence * 3)) / 2;
		inventory = new ArrayList <Treasure> ();
		roller = new Random ();
	}
	
	public String toString ()
	{
		String ret = "Type: " + race;
		if (gender)
			ret += "\nGender: Male";
		else
			ret += "\nGender: Female";
		ret += "\nHealth: " + health;
		ret += "\nMax Health: " + maxhealth;
		ret += "\nDamage: " + damage;
		ret += "\nAC: " + AC;
		ret += "\nStrength: " + Strength;
		ret += "\nEndurance: " + Endurance;
		ret += "\nIntelligence: " + Intelligence;
		ret += "\nWillpower: " + Willpower;
		ret += "\nAgility: " + Agility;
		ret += "\nSpeed: " + Speed;
		ret += "\nLuck: " + Luck;
		
		return ret;
	}
	
	public Boolean attacked (int dmg) { return ((health -= dmg) <= 0); }
	
	public void healed (int heals) { if ((health += heals) > maxhealth) health = maxhealth; }
	
	public Boolean isDead () { return (health <= 0); }
	
	public int rolld20 () { return roller.nextInt (20) + 1; }
	public int rolld12 () { return roller.nextInt (12) + 1; }
	public int rolld10 () { return roller.nextInt (10) + 1; }
	public int rolld8 () { return roller.nextInt (8) + 1; }
	public int rolld6 () { return roller.nextInt (6) + 1; }
	public int rolld4 () { return roller.nextInt (4) + 1; }
	
	public int maxhealth = 50, health = 50, damage = 20, AC, initiative;
	public ArrayList <Treasure> inventory;
	public String name, description;
	private String race;
	//True is male
	private Boolean gender;
	private int Strength, Endurance, Intelligence, Willpower, Agility, Speed, Luck;
	private Random roller;
}
