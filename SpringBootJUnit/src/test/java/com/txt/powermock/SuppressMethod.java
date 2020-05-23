package com.txt.powermock;

public class SuppressMethod {

	private final String username;

	public SuppressMethod(String username) {
		this.username = username;
	}

	public String getUsername() {
		checkPermission();
		return "admin@" + username;
	}

	private void checkPermission() {
		throw new UnsupportedOperationException("This function is unimplemented yet");
	}
}

