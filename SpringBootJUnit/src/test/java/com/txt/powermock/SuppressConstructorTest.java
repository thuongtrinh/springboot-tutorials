package com.txt.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ChildService.class)
public class SuppressConstructorTest {

	@Test
	public void testSuppressConstructorOfEvilParent() throws Exception {
		PowerMockito.suppress(PowerMockito.constructor(BaseService.class));
		final String message = "com.txt";
		ChildService tested = new ChildService(message);
		Assert.assertEquals(message, tested.getMessage());
	}
}

