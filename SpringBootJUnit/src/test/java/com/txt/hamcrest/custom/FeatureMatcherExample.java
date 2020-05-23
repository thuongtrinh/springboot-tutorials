package com.txt.hamcrest.custom;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FeatureMatcherExample {

	@Test
	public void givenString_WhenLengthIs7_ThenCorrect() {
		assertThat("txt", GetLength.length(is(3)));
	}

	@Test
	public void givenString_WhenLengthIs8_ThenIncorrect() {
		assertThat("txt", GetLength.length(is(4)));
	}

}
