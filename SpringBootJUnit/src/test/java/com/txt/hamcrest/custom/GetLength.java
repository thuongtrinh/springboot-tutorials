package com.txt.hamcrest.custom;

import org.hamcrest.Factory;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

public class GetLength extends FeatureMatcher<String, Integer> {

	private GetLength(Matcher<? super Integer> subMatcher, String featureDescription, String featureName) {
		super(subMatcher, featureDescription, featureName);
	}

	@Factory
	public static GetLength length(Matcher<? super Integer> subMatcher) {
		return new GetLength(subMatcher, "a string of length that", "length");
	}

	@Override
	protected Integer featureValueOf(String actual) {
		return actual.length();
	}

}
