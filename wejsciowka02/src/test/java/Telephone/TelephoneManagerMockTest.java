package com.example;

import org.junit.*;
import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.easymock.EasyMock.*;

public class TelephoneManagerMockTest
{
	private TelephoneManager telephoneManagerTest;
	private final Telephone telephoneElement = new Telephone(125, "Fajnie", true);
	private ITelephoneList mock;
	List<Telephone> telephoneList;
	
	@Before
	public void setUp() {
	    mock = createMock(ITelephoneList.class);
	    telephoneManagerTest = new TelephoneManager(mock);
	}
	
	@Test
	public void mockAddCheck() {
	    expect(mock.add(telephoneElement)).andReturn(true).times(1);
	    expect(mock.get(1)).andReturn(telephoneElement);
	    replay(mock);
	    telephoneManagerTest.add(telephoneElement);
	    assertEquals(telephoneElement, telephoneManagerTest.getTelephone(1));
	    verify(mock);
	}
	
        @Test
        public void mockFindCheck()
        {
            expect(mock.find(telephoneElement)).andReturn(true).times(1);
            replay(mock);
            assertTrue(telephoneManagerTest.findTelephone(telephoneElement));
            verify(mock);
        }

        @Test
        public void mockRemoveCheck()
        {
            expect(mock.remove(telephoneElement)).andReturn(true).times(1);
            expect(mock.find(telephoneElement)).andReturn(false).times(1);
            replay(mock);
            telephoneManagerTest.remove(telephoneElement);
            assertFalse(telephoneManagerTest.findTelephone(telephoneElement));
            verify(mock);
        }
}
