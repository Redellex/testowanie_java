package com.example;

import java.util.*;

public class TelephoneManager
{
	private List<Telephone> telephoneList = new ArrayList<Telephone>();

	
	public void add(Telephone telefon)
	{
		telephoneList.add(telefon);
	}
	
	public void remove(Telephone telefon)
	{
		telephoneList.remove(telefon);
	}
	
	public int getSize()
	{
		return telephoneList.size();
	}

	
	public Telephone getTelephone(int index)
	{
		return telephoneList.get(index);
	}
}
