package com.example;

import java.util.*;

class TelephoneManager
{
	private List<Telephone> TelephoneList = new ArrayList(); 
	
	public void add(Telephone Telefon)
	{
		TelephoneList.add(Telefon);
	}
	
	public void remove(Telephone Telefon)
	{
		TelephoneList.remove(Telefon);
	}
	
	public List <Telephone> TelephoneGetter()
	{
		return TelephoneList;
	}
	
	public void setList(List<Telephone> TelephoneList)
	{
		this.TelephoneList = TelephoneList;
	}
}