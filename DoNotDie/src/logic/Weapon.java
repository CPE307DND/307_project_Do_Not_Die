package logic;


public class Weapon extends Treasure
{
	Weapon () {}
	
	public Weapon (int level)
	{

		int piece = 0;
		
		
		if (piece == 0)
		{
			System.out.println ("Making Sword");
			new Sword (material [level / 5]);
		}
		else if (piece == 1)
		{
			System.out.println ("Making Dagger");
			new Dagger (material [level / 5]);
		}
		else if (piece == 2)
		{
			System.out.println ("Making Axe");
			new Axe (material [level / 5]);
		}
		else if (piece == 3)
		{
			System.out.println ("Making Mace");
			new Mace (material [level / 5]);
		}
		else if (piece == 4)
		{
			System.out.println ("Making Warhammer");
			new Warhammer (material [level / 5]);
			
		}
	}
	
	public String toString ()
	{
		return name + "\nValue: " + value + "\nDamage: " + damage + "\nWeight: " + weight + "\n";
	}
	
	public Boolean equals (Weapon other)
	{
		return name.equals (other.getName ());
	}
	
	public int getDamage () { return damage; }
	public int getValue () { return value; }
	public int getWeight () { return weight; }
	public String getName () { return name; }
	
	
	private String [] material = {"Iron", "Steel", "Elven", "Dwarvish", "Glass", "Orcish", "Ancient", "Demonic"};
	private int value, damage, weight;
	private String name;
}
