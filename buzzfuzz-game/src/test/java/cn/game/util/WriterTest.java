package cn.game.util;

import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class WriterTest {

	@Mock
	private Writer writer;

	private List<Object> list;

	@Before
	public void init() {
		list = Arrays.asList("Fuzz", "Buzz", 5, 3, 15, "FizzBuzz");
		Collections.shuffle(list);
	}

	@Test
	public void testWrite() {
		Object object = list.get(0);
		if(object instanceof String) {
			String obj = (String)object;
			writer.write(obj);
			verify(writer).write(obj);	
		} else if (object instanceof Integer) {
			Integer obj = (Integer) object;
			writer.write(obj);
			verify(writer).write(obj);
		}
		
	}

}
