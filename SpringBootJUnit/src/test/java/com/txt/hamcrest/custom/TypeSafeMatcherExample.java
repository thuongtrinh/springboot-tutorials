package com.txt.hamcrest.custom;

import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TypeSafeMatcherExample {

	@Test
	public void givenInteger_WhenAPositiveValue_ThenCorrect() {
		int num = 1;
		assertThat(num, IsPositiveInteger.isAPositiveInteger());
	}

	@Test
	public void givenInteger_WhenANegativeValue_ThenIncorrect() {
		int num = -1;
		assertThat(num, IsPositiveInteger.isAPositiveInteger());
	}
}
