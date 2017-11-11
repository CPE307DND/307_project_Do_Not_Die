package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestMace
{
	public TestMace ()
	{
		iron = new logic.Mace ("Iron");
		iron2 = new logic.Mace ("Iron");
		steel = new logic.Mace ("Steel");
		ironstring = "Iron Mace\nValue: 10\nDamage: 8\nWeight: 8";
	}
	
	@Test
	public void testToString ()
	{
		assertEquals (ironstring, iron.toString ());
	}
	
	@Test
	public void testEqualsYes ()
	{
		assertEquals (true, iron.equals (iron2));
	}
	
	@Test
	public void testEqualsNo ()
	{
		assertEquals (false, iron.equals (steel));
	}
	
	
	logic.Mace iron, iron2, steel;
	String ironstring;
}
