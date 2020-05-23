package com.txt.utils;

public class MathUtil {

	private MathUtil() {
		throw new UnsupportedOperationException("Cannot call constructor directly!");
	}

	public static int divide(int dividend, int divisor) {
		if (divisor == 0) {
			throw new IllegalArgumentException("Cannot divide by zero (0).");
		}
		return dividend / divisor;
	}

}
