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
	public void initializationTest(){
		assertEquals(TelephoneList.TelephoneGetter(), new ArrayList<Telephone>());
	}
	
	@Test
	public void addTest(){
		Telephone DodajMnie = new Telephone(51234, "PawPaw", true);
		List<Telephone> TelephoneListTest = new ArrayList(); 
		TelephoneList.add(DodajMnie);
		TelephoneListTest.add(DodajMnie);
		List <Telephone> abc2 = TelephoneList.TelephoneGetter();
		assertEquals(abc2, TelephoneListTest);
	}
	
	@Test
	public void removeTest()
	{
		Telephone UsunMnie = new Telephone(51234, "PawPaw", true);
		TelephoneList.remove(UsunMnie);
		List <Telephone> abc = TelephoneList.TelephoneGetter();
		assertEquals(abc, new ArrayList<Telephone>());
	}
	
	@Test
	public void addMultipleTest(){
		Telephone Dodajmnie1 = new Telephone(1, "Pie", false);
		Telephone Dodajmnie2 = new Telephone(2, "Diew", true);
		Telephone Dodajmnie3 = new Telephone(3, "heh..", false);
		
		List TelephoneListTest = new ArrayList<Telephone>();
		TelephoneListTest.add(Dodajmnie1);
		TelephoneListTest.add(Dodajmnie2);
		TelephoneListTest.add(Dodajmnie3);
		
		TelephoneList.add(Dodajmnie1);
		TelephoneList.add(Dodajmnie2);
		TelephoneList.add(Dodajmnie3);
		
		assertEquals(TelephoneListTest, TelephoneList.TelephoneGetter());
	}
}