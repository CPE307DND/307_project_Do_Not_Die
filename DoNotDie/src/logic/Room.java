package logic;

public class Room
{
	Room (int numenem, int numtre, int level, int back, int l, int c, int r, int u, int d)
	{
		/*int seed;
		for (int i = 0; i < numenem; i++)
			enemies [i] = new Enemy (level);
		for (int i = 0; i < numtre; i++)
		{
			if ((seed = (int) ((Math.random () * 20) / 10)) == 0)
				treasures [i] = new Misc (level * 10);
			else if (seed == 1)
				treasures [i] = new Weapon (level * 10);
			else
				treasures [i] = Armor.getArmor (level * 10);
		}*/
		
		connections [0] = back;
		connections [1] = l;
		connections [2] = c;
		connections [3] = r;
		connections [4] = u;
		connections [5] = d;
		
		if (numenem > 0)
		{
			enemies = new Enemy [numenem];
			enemies [0] = new Enemy ("Orc", 2, true, 2, 2, 2, 2, 2, 2, 2);
		}
		numenemies = numenem;
	}
	
	public String toString ()
	{
		return "Room back: " + connections [0] + " Room forward: " + connections [2];
	}
	
	
	public String description;
	public Enemy [] enemies;
	public Treasure [] treasures;
	//There will be a back, left, center, right, up, down, in that order
	public int [] connections = {-1, -1, -1, -1, -1, -1};
	public int numenemies;
}
