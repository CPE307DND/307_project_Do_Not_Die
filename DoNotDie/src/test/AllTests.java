package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAxe.class, TestDagger.class, TestMace.class, TestSword.class,
	TestWarhammer.class, TestBoots.class, TestGreaves.class, TestCuirass.class, 
	TestGauntlets.class, TestHelm.class, TestCharacter.class})
public class AllTests {

}
