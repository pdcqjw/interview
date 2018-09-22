package com.hsbc.buzzfizz;

import org.junit.Assert;
import org.junit.Test;

public class Buzz2Test {

	@Test
	public void checkBuzzTest(){
		
		Buzz2 buzz = new Buzz2();
		
		Assert.assertEquals("5 is buzz", buzz.whoAmI(5),"Buzz");
		Assert.assertEquals("10 is buzz", buzz.whoAmI(10),"Buzz");
		Assert.assertEquals("15 is buzz", buzz.whoAmI(15),"Buzz");
		Assert.assertEquals("51 is buzz", buzz.whoAmI(51),"Buzz");
		
		Assert.assertEquals("1 is not buzz",buzz.whoAmI(1),"");
		Assert.assertEquals("2 is not buzz",buzz.whoAmI(2),"");
		Assert.assertEquals("3 is not buzz",buzz.whoAmI(3),"");
		Assert.assertEquals("6 is not buzz",buzz.whoAmI(6),"");
		
		
		Buzz2 buzz2 = new Buzz2(6);
		
		Assert.assertEquals("6 is buzz", buzz2.whoAmI(6),"Buzz");
		Assert.assertEquals("12 is buzz", buzz2.whoAmI(12),"Buzz");
		Assert.assertEquals("16 is buzz", buzz2.whoAmI(16),"Buzz");
		Assert.assertEquals("30 is buzz", buzz2.whoAmI(30),"Buzz");
		Assert.assertEquals("61 is buzz", buzz2.whoAmI(61),"Buzz");
		
		Assert.assertEquals("1 is not buzz",buzz2.whoAmI(1),"");
		Assert.assertEquals("2 is not buzz",buzz2.whoAmI(2),"");
		Assert.assertEquals("3 is not buzz",buzz2.whoAmI(3),"");
		Assert.assertEquals("7 is not buzz",buzz2.whoAmI(7),"");
		
	}
}
