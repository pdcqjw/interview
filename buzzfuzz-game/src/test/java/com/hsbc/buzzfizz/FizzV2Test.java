package com.hsbc.buzzfizz;

import org.junit.Assert;
import org.junit.Test;

public class FizzV2Test {
	
	@Test
	public void checkFizzTest(){
		
		FizzV2 fizz = new FizzV2();
		
		Assert.assertEquals("3 is fizz", fizz.whatIs(3), "Fizz");
		Assert.assertEquals("6 is fizz", fizz.whatIs(6), "Fizz");
		Assert.assertEquals("13 is fizz", fizz.whatIs(13), "Fizz");
		Assert.assertEquals("15 is fizz", fizz.whatIs(15), "Fizz");
		Assert.assertEquals("31 is fizz", fizz.whatIs(31), "Fizz");
		
		Assert.assertEquals("1 is not fizz",fizz.whatIs(1),"");
		Assert.assertEquals("2 is not fizz",fizz.whatIs(2),"");
		Assert.assertEquals("4 is not fizz",fizz.whatIs(4),"");
		Assert.assertEquals("5 is not fizz",fizz.whatIs(5),"");
		
		
		FizzV2 fizz2 = new FizzV2(7);
		
		Assert.assertEquals("7 is fizz", fizz2.whatIs(7),"Fizz");
		Assert.assertEquals("14 is fizz", fizz2.whatIs(14),"Fizz");
		Assert.assertEquals("17 is fizz", fizz2.whatIs(17),"Fizz");
		Assert.assertEquals("35 is fizz", fizz2.whatIs(35),"Fizz");
		Assert.assertEquals("37 is fizz", fizz2.whatIs(37),"Fizz");
		
		Assert.assertEquals("1 is not fizz",fizz2.whatIs(1),"");
		Assert.assertEquals("2 is not fizz",fizz2.whatIs(2),"");
		Assert.assertEquals("3 is not fizz",fizz2.whatIs(3),"");
		Assert.assertEquals("8 is not fizz",fizz2.whatIs(8),"");
		
	}
}
