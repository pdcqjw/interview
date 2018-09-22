package com.hsbc.buzzfizz;

import com.hsbc.buzzfizz.Buzz;
import org.junit.Assert;
import org.junit.Test;


public class BuzzTest {
	
	@Test
	public void checkBuzzTest(){
		
		Buzz buzz = new Buzz();
		
		Assert.assertEquals("5 is buzz", buzz.whatIs(5),"Buzz");
		Assert.assertEquals("10 is buzz", buzz.whatIs(10),"Buzz");
		Assert.assertEquals("15 is buzz", buzz.whatIs(15),"Buzz");
		
		Assert.assertEquals("1 is not buzz",buzz.whatIs(1),"");
		Assert.assertEquals("2 is not buzz",buzz.whatIs(2),"");
		Assert.assertEquals("3 is not buzz",buzz.whatIs(3),"");
		Assert.assertEquals("6 is not buzz",buzz.whatIs(6),"");
		
		
		Buzz buzz2 = new Buzz(6);
		
		Assert.assertEquals("6 is buzz", buzz2.whatIs(6),"Buzz");
		Assert.assertEquals("12 is buzz", buzz2.whatIs(12),"Buzz");
		Assert.assertEquals("30 is buzz", buzz2.whatIs(30),"Buzz");
		
		Assert.assertEquals("1 is not buzz",buzz2.whatIs(1),"");
		Assert.assertEquals("2 is not buzz",buzz2.whatIs(2),"");
		Assert.assertEquals("3 is not buzz",buzz2.whatIs(3),"");
		Assert.assertEquals("7 is not buzz",buzz2.whatIs(7),"");
		
	}
}
