package com.example;

public interface ITelephoneList {
	public boolean add(Telephone telefon);
	public boolean remove(Telephone telefon);
	public Telephone get(int index);
        public boolean find(Telephone telefon);
}
