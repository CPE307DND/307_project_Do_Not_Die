package logic;

public class DND
{
	public static void main (String [] args)
	{
		Map map = new Map ();
		Player p1 = new Player ("Chaos", 0, true, 2, 2, 2, 2, 2, 2, 2);
		
		//Player Info
		//System.out.println ("\n" + p1 + "\n");
		
		//Room Tests
		/*for (int i = 0; i < 10; i++)
		{
			System.out.println (map.current);
			map.movecenter ();
		}
		System.out.println (map.current);
		System.out.println ("Move back");
		map.moveback ();
		System.out.println (map.current);
		System.out.println ("Move forward");
		map.movecenter ();
		map.movecenter ();
		System.out.println (map.current);
		System.out.println ("Move left to 5");
		map.moveleft ();
		System.out.println (map.current);*/
		
		//Roll Tests
		/*System.out.println ("\np1 rolld20: " + p1.rolld20 ());
		System.out.println ("p1 rolld20: " + p1.rolld20 ());
		System.out.println ("p1 rolld20: " + p1.rolld20 ());
		System.out.println ("p1 rolld20: " + p1.rolld20 ());
		System.out.println ("p1 rolld20: " + p1.rolld20 ());
		System.out.println ("p1 rolld20: " + p1.rolld20 ());
		System.out.println ("\np1 rolld12: " + p1.rolld12 ());
		System.out.println ("p1 rolld12: " + p1.rolld12 ());
		System.out.println ("p1 rolld12: " + p1.rolld12 ());
		System.out.println ("p1 rolld12: " + p1.rolld12 ());
		System.out.println ("p1 rolld12: " + p1.rolld12 ());
		System.out.println ("p1 rolld12: " + p1.rolld12 ());
		System.out.println ("\np1 rolld10: " + p1.rolld10 ());
		System.out.println ("p1 rolld10: " + p1.rolld10 ());
		System.out.println ("p1 rolld10: " + p1.rolld10 ());
		System.out.println ("p1 rolld10: " + p1.rolld10 ());
		System.out.println ("p1 rolld10: " + p1.rolld10 ());
		System.out.println ("p1 rolld10: " + p1.rolld10 ());
		System.out.println ("\np1 rolld8: " + p1.rolld8 ());
		System.out.println ("p1 rolld8: " + p1.rolld8 ());
		System.out.println ("p1 rolld8: " + p1.rolld8 ());
		System.out.println ("p1 rolld8: " + p1.rolld8 ());
		System.out.println ("p1 rolld8: " + p1.rolld8 ());
		System.out.println ("p1 rolld8: " + p1.rolld8 ());
		System.out.println ("\np1 rolld6: " + p1.rolld6 ());
		System.out.println ("p1 rolld6: " + p1.rolld6 ());
		System.out.println ("p1 rolld6: " + p1.rolld6 ());
		System.out.println ("p1 rolld6: " + p1.rolld6 ());
		System.out.println ("p1 rolld6: " + p1.rolld6 ());
		System.out.println ("p1 rolld6: " + p1.rolld6 ());
		System.out.println ("\np1 rolld4: " + p1.rolld4 ());
		System.out.println ("p1 rolld4: " + p1.rolld4 ());
		System.out.println ("p1 rolld4: " + p1.rolld4 ());
		System.out.println ("p1 rolld4: " + p1.rolld4 ());
		System.out.println ("p1 rolld4: " + p1.rolld4 ());
		System.out.println ("p1 rolld4: " + p1.rolld4 ());*/

		//attacked, isDead, healed tests
		/*System.out.println ("\nAttacked 50, dead?: " + p1.attacked (65) + ". Health left: " + p1.health);
		System.out.println ("isDead?: " + p1.isDead ());
		System.out.print ("Healed 100. ");
		p1.healed (100);
		System.out.println ("Health now: " + p1.health);
		System.out.println ("Attacked 49, dead?: " + p1.attacked (64) + ". Health left: " + p1.health);
		System.out.println ("isDead?: " + p1.isDead ());*/
		
		//Room tests
		for (int i = 0; i < map.current.numenemies; i++)
			System.out.println ("enemy [" + i + "]: " + map.current.enemies [i]);
	}
}
