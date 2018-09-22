package com.hsbc.buzzfizz;

public class Buzz2 extends Buzz{
	
	private final String buzzStr; //default Buzz2 number
	
	public Buzz2(){
		super();
		this.buzzStr = String.valueOf(super.getBuzzNum());
	}
	
	public Buzz2(int buzzNum) {
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
	public String whoAmI(Integer i){
		return isBuzz(i.intValue()) ? "Buzz" : "";
	} 

}
