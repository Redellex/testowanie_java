package com.example;

import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TelephoneManagerTest
{
	// Utworzenie obiektu ktory mamy zamiar testowac
	private TelephoneManager TelephoneList = new TelephoneManager();
	
	@Test
	public void addTest(){
		Telephone DodajMnie = new Telephone(51234, "PawPaw", "PiePie");
		List<Telephone> TelephoneListTest = new ArrayList(); 
		TelephoneList.add(DodajMnie);
		TelephoneListTest.add(DodajMnie);
		List <Telephone> abc2 = TelephoneList.TelephoneGetter();
		assertEquals(abc2, TelephoneListTest);
	}
	
	@Test
	public void removeTest()
	{
		Telephone UsunMnie = new Telephone(51234, "PawPaw", "PiePie");
		TelephoneList.remove(UsunMnie);
		List <Telephone> abc = TelephoneList.TelephoneGetter();
		assertEquals(abc, new ArrayList<Telephone>());
	}
	
}