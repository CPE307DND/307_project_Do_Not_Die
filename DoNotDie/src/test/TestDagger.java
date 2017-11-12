/*
 * Author: Cristian Rangel
 */


package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestDagger 
{
	public TestDagger ()
	{
		iron = new logic.Dagger ("Iron");
		iron2 = new logic.Dagger ("Iron");
		steel = new logic.Dagger ("Steel");
		ironstring = "Iron Dagger\nValue: 10\nDamage: 4\nWeight: 2";
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
	
	
	logic.Dagger iron, iron2, steel;
	String ironstring;
}
