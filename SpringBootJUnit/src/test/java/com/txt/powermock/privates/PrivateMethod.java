package com.txt.powermock.privates;

public class PrivateMethod {

	private String username;

	public String getUsername() {
		return username;
	}

	private void setUsername(String username) {
		this.username = username;
	}

	public void resetUsername() {
		setUsername("NONAME");
	}

}
