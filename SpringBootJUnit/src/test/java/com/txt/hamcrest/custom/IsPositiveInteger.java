package com.txt.hamcrest.custom;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

class IsPositiveInteger extends TypeSafeMatcher<Integer> {

	@Factory
	public static Matcher<Integer> isAPositiveInteger() {
		return new IsPositiveInteger();
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("a positive integer");
	}

	@Override
	protected boolean matchesSafely(Integer integer) {
		return integer > 0;
	}
}
