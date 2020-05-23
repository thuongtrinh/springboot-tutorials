package com.txt.mockito;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class MockitoExampleTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Mock
	private List<String> list;

	@Test
	public void shouldDoSomething() {
		list.add("com.txt");
	}
}

