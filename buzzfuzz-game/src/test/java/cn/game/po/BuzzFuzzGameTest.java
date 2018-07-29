package cn.game.po;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BuzzFuzzGameTest {

	@Mock
	private BuzzFuzzGame game;
	
	@Test
	public void testInit() {
		game.init();
		verify(game).init();
	}

	@Test
	public void testRun() {
		game.run();
		verify(game).run();
	}

	@Test
	public void testDestroy() {
		game.destroy();
		verify(game).destroy();
	}

}
