package com.txt.intro;

import org.junit.Assert;
import org.junit.Test;

import com.txt.utils.MathUtil;

public class ExceptionTest2 {

	@Test
	public void testDivideByZero() throws Exception {
		try {
			MathUtil.divide(1, 9);
			Assert.fail("Not throw an exception");
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
			Assert.assertEquals("Cannot divide by zero (0).", e.getMessage());
		}
	}

}
