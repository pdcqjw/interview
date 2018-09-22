package com.hsbc.buzzfizz;

import org.junit.Assert;
import org.junit.Test;

public class SimpleFactoryTest {
	
	@Test
	public void buzzFizzOutTest(){
		
		SimpleFactory simpleFactory = new SimpleFactory();
		
		Assert.assertEquals("3 is fizz", simpleFactory.buzzFizzOut(3),"Fizz");
		Assert.assertEquals("5 is buzz", simpleFactory.buzzFizzOut(5),"Buzz");
		Assert.assertEquals("9 is fizz", simpleFactory.buzzFizzOut(9),"Fizz");
		Assert.assertEquals("15 is buzz and fizz", simpleFactory.buzzFizzOut(15),"BuzzFizz");
		Assert.assertEquals("35 is buzz", simpleFactory.buzzFizzOut(35),"Buzz");
		Assert.assertEquals("1 is not buzz and fizz", simpleFactory.buzzFizzOut(1),"");
		Assert.assertEquals("7 is not buzz and fizz", simpleFactory.buzzFizzOut(7),"");
		Assert.assertEquals("53 is not buzz and fizz", simpleFactory.buzzFizzOut(53),"");
	}
	
	@Test
	public void buzzFizzOut2Test(){
		
		SimpleFactory simpleFactory = new SimpleFactory();
		
		Assert.assertEquals("3 is fizz", simpleFactory.buzzFizz2Out(3),"Fizz");
		Assert.assertEquals("5 is buzz", simpleFactory.buzzFizz2Out(5),"Buzz");
		Assert.assertEquals("9 is fizz", simpleFactory.buzzFizz2Out(9),"Fizz");
		Assert.assertEquals("15 is buzz and fizz", simpleFactory.buzzFizz2Out(15),"BuzzFizz");
		Assert.assertEquals("35 is buzz and fizz", simpleFactory.buzzFizz2Out(35),"BuzzFizz");
		Assert.assertEquals("1 is not buzz and fizz", simpleFactory.buzzFizz2Out(1),"");
		Assert.assertEquals("7 is not buzz and fizz", simpleFactory.buzzFizz2Out(7),"");
		Assert.assertEquals("53 is buzz and fizz", simpleFactory.buzzFizz2Out(53),"BuzzFizz");
	}

}
