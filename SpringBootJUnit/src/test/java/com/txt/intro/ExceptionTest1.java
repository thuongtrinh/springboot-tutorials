package com.txt.intro;

import org.junit.Test;

import com.txt.utils.MathUtil;

public class ExceptionTest1 {

	@Test(expected = IllegalArgumentException.class)
	public void testDivideByZero() throws Exception {
		MathUtil.divide(1, 0);
	}

}
