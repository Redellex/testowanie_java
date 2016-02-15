package com.example;

import static org.junit.Assert.*;

import org.junit.Test;

public class KalkulatorDoubleTest {
	private KalkulatorDouble calc = new KalkulatorDouble();
	
	@Test
	public void checkAdd(){
		assertEquals(4.555, calc.add(2.505, 2.050), 0.01);
	}
	
	@Test
	public void checkSub(){
		assertEquals(2.222, calc.sub(2.666, 0.444), 0.0001);
	}
	
	@Test
	public void checkMulti(){
		assertEquals(1.25, calc.multi(5, 0.25), 0.01);
	}
	
	@Test
	public void checkDiv(){
		assertEquals(0.25, calc.div(1.25, 5), 0.01);
	}
}
