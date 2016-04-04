package com.example;

import java.util.*;

public class TelephoneManager
{
	private  ITelephoneList telephonelist;
	
	public TelephoneManager(ITelephoneList TelephoneList)
	{
		this.telephonelist = TelephoneList;
	}
	
	public boolean add(Telephone Telefon)
	{
		telephonelist.add(Telefon);
		return true;
	}
	
	public boolean remove(Telephone Telefon)
	{
		telephonelist.remove(Telefon);
		return true;
	}
	
	public Telephone getTelephone(int index)
	{
		return telephonelist.get(index);
	}
}