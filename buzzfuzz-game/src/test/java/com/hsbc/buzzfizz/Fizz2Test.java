package com.hsbc.buzzfizz;

import org.junit.Assert;
import org.junit.Test;

public class Fizz2Test {
	
	@Test
	public void checkFizzTest(){
		
		Fizz2 fizz = new Fizz2();
		
		Assert.assertEquals("3 is fizz", fizz.whoAmI(3), "Fizz");
		Assert.assertEquals("6 is fizz", fizz.whoAmI(6), "Fizz");
		Assert.assertEquals("13 is fizz", fizz.whoAmI(13), "Fizz");
		Assert.assertEquals("15 is fizz", fizz.whoAmI(15), "Fizz");
		Assert.assertEquals("31 is fizz", fizz.whoAmI(31), "Fizz");
		
		Assert.assertEquals("1 is not fizz",fizz.whoAmI(1),"");
		Assert.assertEquals("2 is not fizz",fizz.whoAmI(2),"");
		Assert.assertEquals("4 is not fizz",fizz.whoAmI(4),"");
		Assert.assertEquals("5 is not fizz",fizz.whoAmI(5),"");
		
		
		Fizz2 fizz2 = new Fizz2(7);
		
		Assert.assertEquals("7 is fizz", fizz2.whoAmI(7),"Fizz");
		Assert.assertEquals("14 is fizz", fizz2.whoAmI(14),"Fizz");
		Assert.assertEquals("17 is fizz", fizz2.whoAmI(17),"Fizz");
		Assert.assertEquals("35 is fizz", fizz2.whoAmI(35),"Fizz");
		Assert.assertEquals("37 is fizz", fizz2.whoAmI(37),"Fizz");
		
		Assert.assertEquals("1 is not fizz",fizz2.whoAmI(1),"");
		Assert.assertEquals("2 is not fizz",fizz2.whoAmI(2),"");
		Assert.assertEquals("3 is not fizz",fizz2.whoAmI(3),"");
		Assert.assertEquals("8 is not fizz",fizz2.whoAmI(8),"");
		
	}
}
