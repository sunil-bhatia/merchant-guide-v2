package com.thoughtworks.merchant.computations;

import java.util.HashMap;

import com.thoughtworks.merchant.interfaces.RomanCalculator;

//This class is used to convert from roman numbers to arabic numbers
public class RomanCalculatorImpl implements RomanCalculator{

	private static HashMap<Character, Integer> symbolVal = new HashMap<Character, Integer>();

	static {
		// Roman numbers are based on seven symbols with these values
		symbolVal.put('I', 1);
		symbolVal.put('V', 5);
		symbolVal.put('X', 10);
		symbolVal.put('L', 50);
		symbolVal.put('C', 100);
		symbolVal.put('D', 500);
		symbolVal.put('M', 1000);
	}

	@Override
	public int romanToArabic(String romanNumInput) {

		String romanNum = romanNumInput.toUpperCase();
		
		int totalArabicNumVal = 0;

		if (isRomanNumValid(romanNum)){
			
			char currentSymbol;
			int currentSymbolVal;
			int prevSymbolVal = 0;
			
			// Traverse in reverse, from right to left
			for (int i = romanNum.length() - 1; i >= 0; i--) {
				
				currentSymbol = romanNum.charAt(i);
				currentSymbolVal = symbolVal.get(currentSymbol);

				// if symbol value increasing from right to left, then add, otherwise subtract
				if (symbolValueIncreasing(currentSymbolVal, prevSymbolVal)){
					totalArabicNumVal += currentSymbolVal;
				} else{
					totalArabicNumVal -= currentSymbolVal;
				}
				
				prevSymbolVal = currentSymbolVal;
			}
			
		} else {
			totalArabicNumVal = -1;
		}

		return totalArabicNumVal;
	}
	
	private boolean symbolValueIncreasing(int currentSymbolVal, int prevSymbolVal){
		return (currentSymbolVal >= prevSymbolVal);
	}
	
	@Override
	public boolean isRomanNumValid(String romanNumInput){
		boolean isValid = false;
		
		String romanNum = romanNumInput.toUpperCase();

		if (romanNum != null && !romanNum.isEmpty()
				&& romanNum.matches("^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")) {
			isValid = true;
		}
		return isValid;
	}
}
