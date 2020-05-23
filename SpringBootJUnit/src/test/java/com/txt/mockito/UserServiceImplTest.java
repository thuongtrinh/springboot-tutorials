package com.txt.mockito;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.txt.dao.UserDao;
import com.txt.service.UserService;
import com.txt.service.UserServiceImpl;

//@RunWith attaches a runner with the test class to initialize the mock data
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	// Create a mock object
	@Mock
	private UserDao userDao;
	private UserService userService;

	@Before
	public void setUp() {
//		MockitoAnnotations.initMocks(this);
		userService = new UserServiceImpl(userDao);
	}

	@Test
	public void createUser_WhenEmailExisted_ReturnFailed() {
		// Define return value for method createUser()
		Mockito.when(userDao.createUser("abc@com.txt")).thenReturn(false);

		// Use mock in test
		Assert.assertEquals("FAILED", userService.createUser("abc@com.txt"));
	}

	@Test
	public void createUser_WhenEmailNotExisted_ReturnSuccess() {
		// Define return value for method createUser()
		Mockito.when(userDao.createUser("abc@com.txt")).thenReturn(true);

		// Use mock in test
		Assert.assertEquals("SUCCESS", userService.createUser("abc@com.txt"));
	}
}
