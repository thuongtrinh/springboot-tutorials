package com.txt.mockito.verify;

public abstract class UserCreationTemplate {

	public void createUser() {
		createUserCode();
		saveUser();
		sendEmail();
		showResult();
	}

	protected abstract String createUserCode();

	protected abstract void saveUser();

	protected abstract void sendEmail();

	protected abstract void showResult();
}