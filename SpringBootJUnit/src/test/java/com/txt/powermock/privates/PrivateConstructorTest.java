package com.txt.powermock.privates;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PrivateConstructor.class)
public class PrivateConstructorTest {

	@Test
	public void constructorWithoutArgs() throws Exception {
		PrivateConstructor mockedObject = Whitebox.invokeConstructor(PrivateConstructor.class);
		Assert.assertEquals("default value when no argument", mockedObject.getUsername());
	}

	@Test
	public void constructorWithArgs() throws Exception {
		PrivateConstructor mockedObject = Whitebox.invokeConstructor(PrivateConstructor.class, "com.txt");
		Assert.assertEquals("com.txt", mockedObject.getUsername());
	}
}

