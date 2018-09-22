package com.hsbc.buzzfizz;

import org.junit.Assert;
import org.junit.Test;

public class BuzzV2Test {

	@Test
	public void checkBuzzTest(){
		
		BuzzV2 buzz = new BuzzV2();
		
		Assert.assertEquals("5 is buzz", buzz.whatIs(5),"Buzz");
		Assert.assertEquals("10 is buzz", buzz.whatIs(10),"Buzz");
		Assert.assertEquals("15 is buzz", buzz.whatIs(15),"Buzz");
		Assert.assertEquals("51 is buzz", buzz.whatIs(51),"Buzz");
		
		Assert.assertEquals("1 is not buzz",buzz.whatIs(1),"");
		Assert.assertEquals("2 is not buzz",buzz.whatIs(2),"");
		Assert.assertEquals("3 is not buzz",buzz.whatIs(3),"");
		Assert.assertEquals("6 is not buzz",buzz.whatIs(6),"");
		
		
		BuzzV2 buzz2 = new BuzzV2(6);
		
		Assert.assertEquals("6 is buzz", buzz2.whatIs(6),"Buzz");
		Assert.assertEquals("12 is buzz", buzz2.whatIs(12),"Buzz");
		Assert.assertEquals("16 is buzz", buzz2.whatIs(16),"Buzz");
		Assert.assertEquals("30 is buzz", buzz2.whatIs(30),"Buzz");
		Assert.assertEquals("61 is buzz", buzz2.whatIs(61),"Buzz");
		
		Assert.assertEquals("1 is not buzz",buzz2.whatIs(1),"");
		Assert.assertEquals("2 is not buzz",buzz2.whatIs(2),"");
		Assert.assertEquals("3 is not buzz",buzz2.whatIs(3),"");
		Assert.assertEquals("7 is not buzz",buzz2.whatIs(7),"");
		
	}
}
