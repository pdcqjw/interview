package com.hsbc.buzzfizz;

public class App {

	 public static void main(String[] args){
		 SimpleFactory simpleFactory = new SimpleFactory();
		 
		 for(int i=1;i<=100;i++){
			 System.out.println(i + ":" + simpleFactory.buzzFizzOut(i)); 
		 }
		 
		 System.out.println("-------------Stage2-------------");
		 
		 //Stage2
		 
		 for(int i=1;i<=100;i++){
			 System.out.println(i + ":" + simpleFactory.buzzFizz2Out(i)); 
		 }
	 }
}
