package cn.game.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.game.config.BuzzFuzzConfig;
import cn.game.po.BuzzFuzzGame;

public class BuzzFuzzApp {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BuzzFuzzConfig.class);
		context.getBean(BuzzFuzzGame.class).run();
		context.close();
	}
}
