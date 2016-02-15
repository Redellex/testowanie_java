package com.example;

import static org.junit.Assert.*;

import org.junit.Test;

public class LiczbaRzymskaTest {
	private LiczbaRzymska Rzymianin = new LiczbaRzymska(3);
	
	@Test
	public void Check(){
		System.out.println("Twoja liczba 3 w rzymskim to: " + Rzymianin.toString());
	}
}
