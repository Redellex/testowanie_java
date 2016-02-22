package com.example;

import static org.junit.Assert.*;

import org.junit.*;

public class KalkulatorTest {
	
	private Kalkulator calc = new Kalkulator();
	
	@Test	//Test dotyczace dodawania klasy Kalkulator. Przewidywany pass testu.
	public void addCheck(){
		assertEquals(5, calc.add(3, 2)); // ==?
	}
	
	@Test //Test dotyczacy odejmowania klasy Kalkulator. Przewidywany pass testu.
	public void addSub(){
		assertEquals(1, calc.sub(3,2));
	}
	
	@Test //Test dotyczace mnozenia klasy Kalkulator. Przewidywany pass testu.
	public void addMulti(){	
		assertEquals(4, calc.multi(2, 2));
	}

	@Test //Test dotyczace dzielenia klasy Kalkulator. Przewidywany pass testu.
	public void addDiv(){
		assertEquals(4, calc.div(16, 4));
	}
	
	//Test dotyczace dzielenia przez 0 klasy Kalkulator. Przewidywany pass testu
	// poprzez ArithmeticException.
	@Test (expected = ArithmeticException.class) 
	public void addDivZero(){
		calc.div(16, 0);
	}
	
	@Test //Test dotyczacy dzialania porownywania liczb. Przewidywany pass testu.
	public void addGreaterT(){
		assertTrue(calc.greater(40, 5));
	}
	
	@Test //Test dotyczacy dzialania porownywania liczb. Przewidywany pass testu.
	public void addGreaterF(){
		assertFalse(calc.greater(999, 99999));
	}
}
