package logic;

import java.util.Random;

public class Weapon extends Treasure
{
	Weapon ()
	{
		Random r = new Random ();
		new Weapon (r.nextInt (30));
	}
	
	Weapon (int level)
	{
	}
	
	private int sword, dagger, axe, mace, hammer;
}
