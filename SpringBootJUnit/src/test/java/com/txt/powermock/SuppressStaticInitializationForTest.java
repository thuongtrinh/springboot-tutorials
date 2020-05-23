package com.txt.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@SuppressStaticInitializationFor("com.txt.powermock.LoginService")
public class SuppressStaticInitializationForTest {

	@Test
	public void testSuppressStaticInitializer() throws Exception {
		final String message = "com.txt";
		LoginService tested = new LoginService(message);
		Assert.assertEquals(message, tested.getMessage());
	}
}

