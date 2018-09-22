package com.hsbc.buzzfizz;

public class SimpleFactory {
	
	public static final int BUZZ_NUM = 5;
	public static final int FIZZ_NUM = 3;
	
	public String buzzFizzOut(int i){
		IBuzzFizz buzz = new Buzz(BUZZ_NUM);
		IBuzzFizz fizz = new Fizz(FIZZ_NUM);
		
		String out = buzz.whatIs(i) + fizz.whatIs(i);
		
		return out;
	}
	
	public String buzzFizz2Out(int i){
		IBuzzFizz buzz = new BuzzV2(BUZZ_NUM);
		IBuzzFizz fizz = new FizzV2(FIZZ_NUM);
		
		String out = buzz.whatIs(i) + fizz.whatIs(i);
		
		return out;
	}
}
