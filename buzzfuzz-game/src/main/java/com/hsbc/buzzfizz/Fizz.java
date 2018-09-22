package com.hsbc.buzzfizz;

public class Fizz implements IBuzzFizz<Integer>{
	
private int fizzNum = 3; //default Buzz number
	
	public Fizz(){
		
	}

	public Fizz(int fizzNum){
		this.fizzNum = fizzNum;
	}
	
	public int getFizzNum(){
		return this.fizzNum;
	}
	
	public boolean isFizz(int num){
		
		if(fizzNum == 0) {
			System.out.println("fizzNum is 0, can't divide by 0!");
			return false;
		}
		
		return (num % fizzNum == 0 ? true : false);
	}
	
	public String whatIs(Integer i){
		return isFizz(i.intValue()) ? "Fizz" : "";
	}
}
