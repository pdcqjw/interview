package cn.game.util;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SetSortTest {

	@MockBean
	private SetSort setSort;
	
	@Test
	public void testSort() {
		Set<Integer> testSet = new HashSet<Integer>();
		testSet.add(5);
		testSet.add(3);
		testSet.add(15);
		Assert.assertArrayEquals(new Integer[] { 15, 5, 3 }, setSort.sort(testSet));
	}

}
