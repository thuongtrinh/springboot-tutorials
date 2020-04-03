package com.txt.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.txt.entity.User;

@Service
public class UserService {

	public static List<User> listUser = new ArrayList<User>();

	static {
		User userAdmin = new User(1, "smith", "123456");
		userAdmin.setRoles(new String[] { "ROLE_ADMIN" });

		User userUser = new User(2, "alice", "123456");
		userUser.setRoles(new String[] { "ROLE_USER" });

		listUser.add(userAdmin);
		listUser.add(userUser);
	}

	public List<User> findAll() {
		return listUser;
	}

	public User findById(int id) {
		for (User user : listUser) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public boolean add(User user) {
		if(user.getId() == 0){
			user.setId(listUser.size() + 1);
		}

		for (User userExist : listUser) {
			if (user.getId() == userExist.getId() || StringUtils.equals(user.getUsername(), userExist.getUsername())) {
				return false;
			}
		}
		listUser.add(user);
		return true;
	}

	public void delete(int id) {
		listUser.removeIf(user -> user.getId() == id);
	}

	public User loadUserByUsername(String username) {
		for (User user : listUser) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

	public boolean checkLogin(User user) {
		for (User userExist : listUser) {
			if (StringUtils.equals(user.getUsername(), userExist.getUsername())
					&& StringUtils.equals(user.getPassword(), userExist.getPassword())) {
				return true;
			}
		}
		return false;
	}
}
