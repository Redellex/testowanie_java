package com.example;

import java.util.*;

public class TelephoneManager
{
	private  ITelephoneList telephoneList;
	
	public TelephoneManager(ITelephoneList telephoneList)
	{
		this.telephoneList = telephoneList;
	}
	
	public boolean add(Telephone telefon)
	{
		telephoneList.add(telefon);
		return true;
	}
	
	public boolean remove(Telephone telefon)
	{
		telephoneList.remove(telefon);
		return true;
	}
	
	public Telephone getTelephone(int index)
	{
		return telephoneList.get(index);
	}

        public boolean findTelephone(Telephone telefon)
        {
            if(telephoneList.find(telefon)){
                return true;   
            }
            else
            {
                return false;
            }
        }
}
