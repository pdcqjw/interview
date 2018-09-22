package com.hsbc.buzzfizz;

public class Buzz implements IBuzzFizz<Integer> {

	private int buzzNum = 5; //default Buzz number
	
	public Buzz(){
		
	}
	
	public Buzz(int buzzNum){
		this.buzzNum = buzzNum;
	}
	
	public int getBuzzNum(){
		return this.buzzNum;
	}
	
	public boolean isBuzz(int num){
		
		if(buzzNum == 0) {
			System.out.println("buzzNum is 0, can't divide by 0!");
			return false;
		}
		
		return (num % buzzNum == 0 ? true : false);
	}
	
	public String whatIs(Integer i){
		return isBuzz(i.intValue()) ? "Buzz" : "";
	}
}
