package com.txt.listener;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class IgnoreTest {

	@Test
	@Ignore("This test case will be ignored")
	public void ignoreTest() {
		String expected = "txt.com";
		Assert.assertEquals(expected, "txt.com");
	}
}
