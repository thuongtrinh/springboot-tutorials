package com.txt.intro;

import org.junit.Assert;
import org.junit.Test;

import com.txt.utils.TaskUtils;

public class TimeoutTest1 {

	@Test(timeout = 3000) // 3 seconds
	public void testTimeout1() {
		int expected = 1;
		int actual = TaskUtils.doNormalTask();
		Assert.assertEquals(expected, actual);
	}

	@Test(timeout = 3000) // 3 seconds
	public void testTimeout2() {
		int expected = 1;
		int actual = TaskUtils.doHeavyTask();
		Assert.assertEquals(expected, actual);
	}

}
