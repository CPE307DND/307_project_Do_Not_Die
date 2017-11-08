package logic;

import java.util.Random;

public class Treasure
{
	Treasure ()
	{
		Random r = new Random ();
		new Treasure (r.nextInt (30));
	}
	
	Treasure (int level)
	{
		int i = (int) ((Math.random () * 20) / 10);
		
		switch (i)
		{
			case (0):
				new logic.Misc (level * 10);
			case (1):
				new logic.Armor (level * 10);
			case (2):
				new logic.Weapon (level * 10);
		}
	}
}