package com.example;

import org.junit.*;
import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.easymock.EasyMock.createMock;

public class TelephoneManagerMockTest
{
	private TelephoneManager mock;
	private Telephone TelephoneElement = new Telephone(125, "Fajnie", true);
	
	@Before
	public void setUp() {
		mock = createMock(TelephoneManager.class);
	}
	
	@Test
	public void calculationCheck() {
		expect(mock.add(TelephoneElement)).times(1);
		verify(mock);
	}
	
}