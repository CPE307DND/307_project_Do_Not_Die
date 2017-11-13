/*
 * Author: Justin Herrera
 */


package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestBoots
{
	public TestBoots ()
	{
		chiton = new logic.Boots ("Chiton");
		chiton2 = new logic.Boots ("Chiton");
		iron = new logic.Boots ("Iron");
		ironstring = "Iron Boots\nAR: 7\nValue: 15\nWeight: 4";
	}
	
	@Test
	public void testToString ()
	{
		assertEquals (ironstring, iron.toString ());
	}
	
	@Test
	public void testEqualsYes ()
	{
		assertEquals (true, chiton.equals (chiton2));
	}
	
	@Test
	public void testEqualsNo ()
	{
		assertEquals (false, chiton.equals (iron));
	}
	
	
	logic.Boots chiton, chiton2, iron;
	String ironstring;
}
