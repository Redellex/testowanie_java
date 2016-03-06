package com.example;

import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

class TelephoneManagerTest
{
	// Utworzenie obiektu ktory mamy zamiar testowac
	private TelephoneManager TelephoneList = new TelephoneManager();
	
	@Test
	public void addTest(){
		TelephoneList.add(510588, "PawPaw", "PiePie");
		Telephone DodajMnie = new Telephone(51234, "PawPaw", "PiePie");
		List <Telephone> abc = new ArrayList();
		List <Telephone> abc2 = new ArrayList();
		abc.add(DodajMnie);
		assertArrayEquals(abc, abc);
	}
	
	@Test
	public void removeTest()
	{
		//Sprawdzenie czy poprawnie usuwa element z listy
	}
	
}