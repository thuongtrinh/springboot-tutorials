package com.txt.intro;

import org.junit.Assume;
import org.junit.Test;

public class AssumeTest {

	@Test
	public void assumeTrueTest() {
		Assume.assumeTrue(true);
		System.out.println("execute test 1");
	}

	@Test
	public void assumeNotNullTest() {
		Object object = null;
		Assume.assumeNotNull(object);
		System.out.println("execute test 2");
	}

}
