package com.example;
import java.lang.*;

public class LiczbaRzymska{
	private int liczba;
	
	public void SetLiczbaRzymska(int liczba){
		this.liczba = liczba;
	}

	public String toString(){
	//	private final int Arabska[] = {1000, 500, 100, 50, 10, 5, 1};
	//	private final char Rzymska[] = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
		String wynik = "";
		StringBuilder WynikBuilder = new StringBuilder(wynik);
		
		while(liczba > 0){
			if(liczba >= 1000)
			{
				liczba -= 1000;
				WynikBuilder.append("M");
			}
			if(liczba >=500 && liczba <1000){
				liczba -= 500;
				WynikBuilder.append("D");
			}
			if(liczba >=100 && liczba <500){
				liczba -= 100;
				WynikBuilder.append("C");
			}
			if(liczba >=50 && liczba <100){
				liczba -= 50;
				WynikBuilder.append("L");
			}
			if(liczba >=10 && liczba <50){
				liczba -= 10;
				WynikBuilder.append("X");
			}
			if(liczba >=5 && liczba <10){
				liczba -= 5;
				WynikBuilder.append("V");
			}
			if(liczba >=1 && liczba <5){
				liczba -= 1;
				WynikBuilder.append("I");
			}
		}
		wynik = WynikBuilder.toString();
		return wynik;
	}
}