package logic;

public class Armor extends Treasure
{
	public Armor getArmor ()
	{
		int piece = (int) ((Math.random () * 40) / 10);
		int type = (int) ((Math.random () * 100) / 10);
		
		return total [piece][type];
	}
	
	public Armor getArmor (int piece, int type)
	{
		return total [piece][type];
	}
	
	private Helm [] helm = {new Helm ("Leather"), new Helm ("Chiton"), new Helm ("Iron"), new Helm ("Chainmail"),
			new Helm ("Steel"), new Helm ("Elven"), new Helm ("Dwarvish"), new Helm ("Orcish"), new Helm ("Ancient"),
			new Helm ("Platemail"), new Helm ("Demonic")};
	private Cuirass [] cuirass = {new Cuirass ("Leather"), new Cuirass ("Chiton"), new Cuirass ("Iron"), new Cuirass ("Chainmail"),
			new Cuirass ("Steel"), new Cuirass ("Elven"), new Cuirass ("Dwarvish"), new Cuirass ("Orcish"), new Cuirass ("Ancient"),
			new Cuirass ("Platemail"), new Cuirass ("Demonic")};
	private Gauntlets [] gauntlets = {new Gauntlets ("Leather"), new Gauntlets ("Chiton"), new Gauntlets ("Iron"), new Gauntlets ("Chainmail"),
			new Gauntlets ("Steel"), new Gauntlets ("Elven"), new Gauntlets ("Dwarvish"), new Gauntlets ("Orcish"), new Gauntlets ("Ancient"),
			new Gauntlets ("Platemail"), new Gauntlets ("Demonic")};
	private Greaves [] greaves = {new Greaves ("Leather"), new Greaves ("Chiton"), new Greaves ("Iron"), new Greaves ("Chainmail"),
			new Greaves ("Steel"), new Greaves ("Elven"), new Greaves ("Dwarvish"), new Greaves ("Orcish"), new Greaves ("Ancient"),
			new Greaves ("Platemail"), new Greaves ("Demonic")};
	private Boots [] boots = {new Boots ("Leather"), new Boots ("Chiton"), new Boots ("Iron"), new Boots ("Chainmail"),
			new Boots ("Steel"), new Boots ("Elven"), new Boots ("Dwarvish"), new Boots ("Orcish"), new Boots ("Ancient"),
			new Boots ("Platemail"), new Boots ("Demonic")};
	
	private Armor [][] total = {helm, cuirass, gauntlets, greaves, boots};
	
	public int value, ar, weight;
	public String name;
}
