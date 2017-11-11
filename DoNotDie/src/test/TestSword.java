package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestSword
{
	public TestSword ()
	{
		iron = new logic.Sword ("Iron");
		iron2 = new logic.Sword ("Iron");
		steel = new logic.Sword ("Steel");
		ironstring = "Iron Sword\nValue: 10\nDamage: 8\nWeight: 8";
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
	
	
	logic.Sword iron, iron2, steel;
	String ironstring;
}
