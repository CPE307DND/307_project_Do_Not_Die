/*
 * Author: Cristian Rangel
 */


package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestAxe
{
	public TestAxe ()
	{
		iron = new logic.Axe ("Iron");
		iron2 = new logic.Axe ("Iron");
		steel = new logic.Axe ("Steel");
		ironstring = "Iron Axe\nValue: 10\nDamage: 9\nWeight: 9";
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
	
	
	logic.Axe iron, iron2, steel;
	String ironstring;
}
