package com.txt.intro;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class IgnoreTest1 {

	@Test
	@Ignore("This test case will be ignored")
	public void testEquals() {
		String expected = "com.txt";
		Assert.assertEquals(expected, "com.txt");
	}

	@Test
	public void testTrue() {
		Assert.assertTrue(true);
	}

	@Test
	public void testFalse() {
		Assert.assertFalse(false);
	}

}
