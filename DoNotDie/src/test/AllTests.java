package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestPlayer.class, TestAxe.class, TestDagger.class, TestMace.class, 
	TestSword.class, TestWarhammer.class, TestBoots.class, TestCuirass.class, 
	TestGauntlets.class})
public class AllTests {

}
