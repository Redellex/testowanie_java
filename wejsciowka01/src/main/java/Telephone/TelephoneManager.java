package com.example;

import java.util.*;

class TelephoneManager
{
	private List<Telephone> TelephoneList = new ArrayList(); 
	
	public void add(int number, String name, String surname)
	{
		Telephone NewElement = new Telephone(number, name, surname); 
		TelephoneList.add(NewElement);
	}
	
	public void remove(int number, String name, String surname)
	{
		Telephone NewElement = new Telephone(number, name, surname);
		TelephoneList.remove(NewElement);
	}
	
	public List <Telephone> TelephoneGetter()
	{
		return TelephoneList;
	}
}