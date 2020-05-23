package com.txt.mockito.abstracts;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

public class AbstractIndependentTest {

	private class AbstractIndependentImplTest extends AbstractIndependent {

		@Override
		public int abstractMethod() {
			return 0;
		}
	}

	@Test
	public void testDefaultImplUsingConcreteClass() {
		AbstractIndependent testedObj = new AbstractIndependentImplTest();
		assertEquals("txt", testedObj.defaultImpl());
	}

	@Test
	public void testDefaultImplUsingMockito() {
		AbstractIndependent testedObj = Mockito.spy(AbstractIndependent.class);
		assertEquals("txt", testedObj.defaultImpl());
	}
}

