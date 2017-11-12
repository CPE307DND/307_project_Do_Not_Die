/*
 * Author: Cristian Rangel
 */


package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestWarhammer
{
	public TestWarhammer ()
	{
		iron = new logic.Warhammer ("Iron");
		iron2 = new logic.Warhammer ("Iron");
		steel = new logic.Warhammer ("Steel");
		ironstring = "Iron Warhammer\nValue: 15\nDamage: 10\nWeight: 10";
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
	
	
	logic.Warhammer iron, iron2, steel;
	String ironstring;
}
