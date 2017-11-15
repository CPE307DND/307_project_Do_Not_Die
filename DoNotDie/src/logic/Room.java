package logic;

import java.util.Random;

public class Room
{
	public Room (int numenem, int numtre, int level, int back, int l, int c, int r, int u, int d)
	{
		Random rand = new Random ();
		int seed = rand.nextInt (3), race;
		Boolean g = rand.nextBoolean ();
		
		if (numenem > 0)
		{
			enemies = new Character [numenem];
			enemydead = new Boolean [numenem];
			
			for (int i = 0; i < numenem; i++)
			{
				race = rand.nextInt (10);
				enemies [i] = new Character (null, race, g, level - seed, level - seed, level - seed, level - seed, level - seed, level - seed, level - seed);
				enemies [i].setInd (i);
				enemydead [i] = false;
			}
		}
		
		for (int i = 0; i < numtre; i++)
		{
			if (seed == 0)
				treasures [i] = new Misc ((level * 10) + 1);
			else if (seed == 1)
				treasures [i] = new Weapon (level);
			else
				treasures [i] = new Armor (level);
		}
		
		connections [0] = back;
		connections [1] = l;
		connections [2] = c;
		connections [3] = r;
		connections [4] = u;
		connections [5] = d;

		numenemies = numenem;
	}
	
	public String toString ()
	{
		String ret = "Connections:";
		if (connections [0] != -1)
			ret += "\nBack: " + connections [0];
		if (connections [1] != -1)
			ret += "\nLeft: " + connections [1];
		if (connections [2] != -1)
			ret += "\nCenter: " + connections [2];
		if (connections [3] != -1)
			ret += "\nRight: " + connections [3];
		if (connections [4] != -1)
			ret += "\nUp: " + connections [4];
		if (connections [5] != -1)
			ret += "\nDown: " + connections [5];
		
		return ret;
	}
	
	public Boolean roomCleared ()
	{
		for (int i = 0; i < numenemies; i++)
		{
			if (!enemydead [i])
				return false;
		}
		return true;
	}
	
	public void enemiesAlive ()
	{
		for (int i = 0; i < numenemies; i++)
		{
			if (!enemydead [i])
				System.out.println (enemies [i]);
		}
	}
	
	public void enemyKilled (int i) { enemydead [i] = true; }
	public Boolean enemyDead (int i) { return enemydead [i]; }
	
	
	public String description;
	public Character [] enemies;
	public Treasure [] treasures;
	//There will be a back, left, center, right, up, down, in that order
	public int [] connections = {-1, -1, -1, -1, -1, -1};
	public int numenemies;
	private Boolean [] enemydead;
}
