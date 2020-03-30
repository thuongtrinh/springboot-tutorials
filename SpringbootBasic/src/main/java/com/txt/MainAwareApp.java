package com.txt;

import java.util.List;

import com.txt.entity.User;
import com.txt.service.BeanUtilService;
import com.txt.service.UserService;

/**
 * @author Admin
 * ApplicationContextAware is an interface that allows access to Spring Bean, resource files.
 *
 */
public class MainAwareApp {

	public List<User> findAllUsers() {
		UserService userService = BeanUtilService.getBean(UserService.class);
		return userService.findAll();
	}

}
