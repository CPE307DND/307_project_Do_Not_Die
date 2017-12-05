/*
 * Author: Cristian Rangel
 */


package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestHelm
{
	public TestHelm ()
	{
		iron = new logic.Helm ("Iron");
		iron2 = new logic.Helm ("Iron");
		steel = new logic.Helm ("Steel");
		ironstring = "Iron Helm\nValue: 15\nArmor Rating: 7\nWeight: 4";
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
	
	
	logic.Helm iron, iron2, steel;
	String ironstring;
}