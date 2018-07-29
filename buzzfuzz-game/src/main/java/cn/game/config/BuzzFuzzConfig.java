package cn.game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.game.po.BuzzFuzzGame;

@Configuration
public class BuzzFuzzConfig {

	@Bean(name = "BuzzFuzzGame", initMethod = "init", destroyMethod = "destroy")
	public BuzzFuzzGame newBuzzFuzzGame() {
		return new BuzzFuzzGame();
	}

}
