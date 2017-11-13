/*
 * Author: Justin Herrera
 */


package test;

import org.junit.Test;

import logic.Gauntlets;

import static org.junit.Assert.assertEquals;

public class TestGauntlets
{
	public TestGauntlets ()
	{
		chiton = new logic.Gauntlets ("Chiton");
		chiton2 = new logic.Gauntlets ("Chiton");
		iron = new logic.Gauntlets ("Iron");
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
	
	
	logic.Gauntlets chiton, chiton2, iron;
	String ironstring;
}
