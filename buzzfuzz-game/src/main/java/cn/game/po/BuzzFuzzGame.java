package cn.game.po;

import java.util.Iterator;
import java.util.Set;

import cn.game.util.SetSort;
import cn.game.util.Writer;

public class BuzzFuzzGame {

	private boolean flag = false;
	private Object[] sort;

	public void init() {
		System.out.println("Welcome to the BuzzFuzz game!");
		Set<Integer> keySet = BuzzFuzzRule.step2Word.keySet();
		sort = new SetSort().sort(keySet);
	}

	public void run() {
		for (int i = BuzzFuzzRule.START_NUMBER; i <= BuzzFuzzRule.END_NUMBER; i++) {
			for (int j = 0; j < sort.length; j++) {
				int key = (int) sort[j];
				if (i % key == 0) {
					Writer.write(BuzzFuzzRule.step2Word.get(key));
					flag = true;
					break;
				}
			}
			if (flag == false) {
				Writer.write(i);
			}
			i = i + (BuzzFuzzRule.ITERATOR_STEP - 1);
			flag = false;
		}
	}

	public void destroy() {
		System.out.println("Welcome next time!");
	}

}
