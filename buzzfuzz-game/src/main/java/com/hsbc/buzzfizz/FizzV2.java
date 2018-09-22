package com.hsbc.buzzfizz;

public class FizzV2 extends Fizz{

private final String fizzStr; //default Fizz2 number
	
	public FizzV2(){
		super();
		this.fizzStr = String.valueOf(super.getFizzNum());
	}
	
	public FizzV2(int fizzNum) {
		super(fizzNum);
		this.fizzStr = String.valueOf(fizzNum);
	}
	
	public String getBuzzStr(){
		return this.fizzStr;
	}
	
	@Override
	public boolean isFizz(int num){
		if(super.isFizz(num))
			return true;
		
		return String.valueOf(num).indexOf(fizzStr)>=0 ? true : false;
	}
	
	@Override
	public String whatIs(Integer i){
		return isFizz(i.intValue()) ? "Fizz" : "";
	} 
}
