package cn.game.app;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BuzzFuzzAppTest {
	
	@Mock
	private BuzzFuzzApp buzzFuzzApp;
	
	@Test
	public void testMain() {
		buzzFuzzApp.main(null);
		verify(buzzFuzzApp).main(null);
	}

}
