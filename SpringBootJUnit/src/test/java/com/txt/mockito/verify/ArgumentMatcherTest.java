package com.txt.mockito.verify;

import static org.hamcrest.CoreMatchers.hasItem;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ArgumentMatcherTest {

	@Captor
	private ArgumentCaptor<List<String>> captor;

	@Mock
	private List<String> mockedList;

	@Before
	public void prepareForTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public final void argumentCaptorTest() {
		List<String> asList = Arrays.asList("com.txt", "mockito", "junit");

		mockedList.addAll(asList);

		// Verify value on arguments
		Mockito.verify(mockedList).addAll(captor.capture());
		final List<String> capturedArgument = captor.getValue();
		Assert.assertEquals(3, capturedArgument.size());
		Assert.assertThat(capturedArgument, hasItem("com.txt"));
	}

	@Test
	public void argumentMatcherTest() {
		List<String> asList = Arrays.asList("com.txt", "mockito", "junit");

		mockedList.addAll(asList);

		// Verifies certain behavior happened at at least once with the given value is "com.txt"
		Mockito.verify(mockedList).addAll(Mockito.anyCollection());
		Mockito.verify(mockedList, Mockito.atLeast(1))
				.addAll(Mockito.argThat(collection -> collection.contains("com.txt")));
	}
}

