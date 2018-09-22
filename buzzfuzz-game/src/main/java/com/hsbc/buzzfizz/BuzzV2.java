package com.hsbc.buzzfizz;

public class BuzzV2 extends Buzz{
	
	private final String buzzStr; //default Buzz2 number
	
	public BuzzV2(){
		super();
		this.buzzStr = String.valueOf(super.getBuzzNum());
	}
	
	public BuzzV2(int buzzNum) {
		super(buzzNum);
		this.buzzStr = String.valueOf(buzzNum);
	}
	
	public String getBuzzStr(){
		return this.buzzStr;
	}
	
	@Override
	public boolean isBuzz(int num){
		if(super.isBuzz(num))
			return true;
		
		return String.valueOf(num).indexOf(buzzStr)>=0 ? true : false;
	}
	
	@Override
	public String whatIs(Integer i){
		return isBuzz(i.intValue()) ? "Buzz" : "";
	} 

}
