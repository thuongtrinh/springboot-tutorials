package com.txt.powermock.statics;

import com.txt.utils.UserUtils;

public class UserService3 {

	public String insert() {
		final String username = "com.txt";
		UserUtils.validate(username);
		String uuid = UserUtils.generateUniqueId();
		UserUtils.validate(uuid);
		return username;
	}
}
