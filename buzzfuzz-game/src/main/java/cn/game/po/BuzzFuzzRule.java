package cn.game.po;

import java.util.HashMap;

public class BuzzFuzzRule {
	public static final int START_NUMBER = 1;
	public static final int END_NUMBER = 100;
	public static int ITERATOR_STEP = 1;

	public static HashMap<Integer, String> step2Word = new HashMap<Integer, String>();

	static {
		step2Word.put(3, "Fizz");
		step2Word.put(5, "BUZZ");
		step2Word.put(15, "FizzBuzz");
	}
}
