package com.example;

import static org.junit.Assert.*;

import org.junit.Test;

public class LiczbaRzymskaTest {
	private LiczbaRzymska Rzym = new LiczbaRzymska();
	
	@Test
	public void checkNumber(){
		Rzym.SetLiczbaRzymska(1);
		assertEquals("I", Rzym.toString());
	}
	
	@Test
	public void checkNumberII(){
		Rzym.SetLiczbaRzymska(3);
		assertEquals("III", Rzym.toString());
	}
	
	@Test
	public void SumOfAllLetters(){
		Rzym.SetLiczbaRzymska(1666);
		assertEquals("MDCLXVI", Rzym.toString());
	}
	
	@Test
	public void FunLetterFromAsterixAndObelix(){
		Rzym.SetLiczbaRzymska(18);
		assertEquals("XVIII", Rzym.toString());
	}
}