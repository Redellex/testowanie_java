package com.example;

import org.junit.*;
import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.easymock.EasyMock.*;

public class TelephoneManagerMockTest
{
	private TelephoneManager TelephoneManagerTest;
	private Telephone TelephoneElement = new Telephone(125, "Fajnie", true);
	private ITelephoneList mock;
	List<Telephone> TelephoneList;
	
	@Before
	public void setUp() {
		mock = createMock(ITelephoneList.class);
		TelephoneManagerTest = new TelephoneManager(mock);
	}
	
	@Test
	public void mockCheck() {
		expect(mock.add(TelephoneElement)).andReturn(true).times(1);
		expect(mock.get(1)).andReturn(TelephoneElement);
		replay(mock);
		TelephoneManagerTest.add(TelephoneElement);
		assertEquals(TelephoneElement, TelephoneManagerTest.getTelephone(1));
		verify(mock);
	}
	
}
//findByName(String) 3 atrybuty.