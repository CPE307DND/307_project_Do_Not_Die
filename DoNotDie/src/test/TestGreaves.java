/*
 * Author: Justin Herrera
 */


package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestGreaves
{
	public TestGreaves ()
	{
		chiton = new logic.Greaves ("Chiton");
		chiton2 = new logic.Greaves ("Chiton");
		iron = new logic.Greaves ("Iron");
		ironstring = "Iron Greaves\nValue: 35\nArmor Rating: 15\nWeight: 13";
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
	
	
	logic.Greaves chiton, chiton2, iron;
	String ironstring;
}
