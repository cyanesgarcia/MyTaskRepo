package com.dar.everisdarmytasksms.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

class ListMockTest {

	List<String> mock = Mockito.mock(List.class);

	@Test
	public void size_basic() {
		when(mock.size()).thenReturn(3);
		assertEquals(3, mock.size());
	}
	

	@Test
	public void returnDifferentValues() {
		when(mock.size()).thenReturn(5).thenReturn(10);
		assertEquals(5, mock.size());
		assertEquals(10, mock.size());
	}

	@Test
	@Disabled
	public void returnWithParameters() {
		when(mock.get(0)).thenReturn("Pending");
		assertEquals("Pending", mock.get(0));
		assertEquals(null, mock.get(1));
	}


	@Test
	public void argumentCapturing() {
		//SUT
		mock.add("SomeString");

		//Verification
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(mock).add(captor.capture());
		assertEquals("SomeString", captor.getValue());
	}

	@Test
	public void multipleArgumentCapturing() {
		//SUT
		mock.add("SomeString1");
		mock.add("SomeString2");

		//Verification
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(mock, times(2)).add(captor.capture());
		List<String> allValues = captor.getAllValues();
		assertEquals("SomeString1", allValues.get(0));
		assertEquals("SomeString2", allValues.get(1));
	}


	@Test
	public void mocking() {
		ArrayList arrayListMock = Mockito.mock(ArrayList.class);
		System.out.println(arrayListMock.get(0));//null
		System.out.println(arrayListMock.size());//0
		arrayListMock.add("Test");
		arrayListMock.add("Test2");

		System.out.println(arrayListMock.size());//0
		when(arrayListMock.size()).thenReturn(5);
		System.out.println(arrayListMock.size());//5
	}



	@Test
	public void spying() {
		ArrayList arrayListSpy = spy(ArrayList.class);
		arrayListSpy.add("Test0");
		System.out.println(arrayListSpy.get(0));//Test0
		System.out.println(arrayListSpy.size());//1
		arrayListSpy.add("Test");
		arrayListSpy.add("Test2");
		System.out.println(arrayListSpy.size());//3

		when(arrayListSpy.size()).thenReturn(5);
		System.out.println(arrayListSpy.size());//5
		arrayListSpy.add("Test4");
		System.out.println(arrayListSpy.size());//5
		verify(arrayListSpy).add("Test4");

	}
}