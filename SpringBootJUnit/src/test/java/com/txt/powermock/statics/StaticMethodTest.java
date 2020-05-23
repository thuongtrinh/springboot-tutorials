package com.txt.powermock.statics;

import static org.mockito.Mockito.validateMockitoUsage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DatabaseHelper.class)
public class StaticMethodTest {

	private DatabaseChecker databaseChecker;

	@Before
	public void prepareForTest() {
		databaseChecker = new DatabaseChecker();
	}

	@After
	public void validate() {
		validateMockitoUsage();
	}

	@Test
	public void staticMethodTest() {
		boolean expectedResult = true;
		PowerMockito.mockStatic(DatabaseHelper.class);
		PowerMockito.when(DatabaseHelper.connect()).thenReturn(expectedResult);

		Assert.assertEquals(expectedResult, databaseChecker.isDatabaseConnected());
	}

	@Test
	public void staticVoidMethodTest() throws Exception {
		PowerMockito.mockStatic(DatabaseHelper.class);
		PowerMockito.doNothing().when(DatabaseHelper.class, "executeSQL", Mockito.anyString());

		databaseChecker.checkSql("DELETE FROM ...");
		PowerMockito.verifyStatic(DatabaseHelper.class);
	}
}
