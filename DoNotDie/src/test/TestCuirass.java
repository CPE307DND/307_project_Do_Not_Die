/*
 * Author: Justin Herrera
 */


package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestCuirass
{
	public TestCuirass ()
	{
		chiton = new logic.Cuirass ("Chiton");
		chiton2 = new logic.Cuirass ("Chiton");
		iron = new logic.Cuirass ("Iron");
		ironstring = "Iron Cuirass\nValue: 15\nArmor Rating: 7\nWeight: 4";
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
	
	
	logic.Cuirass chiton, chiton2, iron;
	String ironstring;
}
