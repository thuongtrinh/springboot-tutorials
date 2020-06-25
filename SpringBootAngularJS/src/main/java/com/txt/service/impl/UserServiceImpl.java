package com.txt.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.txt.dto.User;
import com.txt.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final AtomicLong counter = new AtomicLong();
	private static final List<User> users;

	static {
		users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(), "Alice", "ALICE", "alice@abc.com"));
		users.add(new User(counter.incrementAndGet(), "Danied", "DANIED", "danied@abc.com"));
		users.add(new User(counter.incrementAndGet(), "Smith", "SMITH", "smith@abc.com"));
		users.add(new User(counter.incrementAndGet(), "Trump", "TRUMP", "trump@abc.com"));
		users.add(new User(counter.incrementAndGet(), "Karik", "KARIK", "karik@abc.com"));
	}

	public UserServiceImpl() {
	}

	@Override
	public User findById(long id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}

		return null;
	}

	@Override
	public User findByName(String name) {
		for (User user : users) {
			if (user.getUsername().equalsIgnoreCase(name)) {
				return user;
			}
		}

		return null;
	}

	@Override
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	@Override
	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	@Override
	public void deleteUserById(long id) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
			}
		}
	}

	@Override
	public List<User> findAllUsers() {
		return users;
	}

	@Override
	public void deleteAllUsers() {
		users.clear();
	}

	@Override
	public boolean isUserExist(User user) {
		return findByName(user.getUsername()) != null;
	}

}
