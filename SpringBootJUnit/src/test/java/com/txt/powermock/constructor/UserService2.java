package com.txt.powermock.constructor;

interface UserDao {

	void insert();
}

class UserDaoImpl implements UserDao {

	public UserDaoImpl() {
		throw new UnsupportedOperationException("Cannot connect database");
	}

	@Override
	public void insert() {
		throw new UnsupportedOperationException("This function is unimplemented yet");
	}
}

public class UserService2 {

	private UserDao userDao;

	private UserService2() {
		userDao = new UserDaoImpl();
	}

	public static UserService2 createInstance() {
		return new UserService2();
	}

	public void insert() {
		userDao.insert();
		System.out.println("insert done");
	}

}
