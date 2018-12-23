package com.thoughtworks.merchant.computations;

import java.util.HashMap;

import com.thoughtworks.merchant.interfaces.RomanNumerals;

//This class is used to convert from roman numerals to arabic numbers
public class RomanNumeralsImpl implements RomanNumerals{

	private static HashMap<Character, Integer> symbolVal = new HashMap<Character, Integer>();

	static {
		// Roman numerals are based on seven symbols with these values
		symbolVal.put('I', 1);
		symbolVal.put('V', 5);
		symbolVal.put('X', 10);
		symbolVal.put('L', 50);
		symbolVal.put('C', 100);
		symbolVal.put('D', 500);
		symbolVal.put('M', 1000);
	}

	public int romanToArabic(String romanNumInput) {
		int arabicNum = 0;

		String romanNum = romanNumInput.toUpperCase();

		if (isValidRomanNum(romanNum)){
			// Traverse in reverse
			int prevSymbol = 0;
			for (int i = romanNum.length() - 1; i >= 0; i--) {
				int currentSymbol = symbolVal.get(romanNum.charAt(i));
				// When smaller values precede larger values, smaller values are
				// subtracted
				if (currentSymbol < prevSymbol)
					arabicNum -= currentSymbol;
				else
					arabicNum += currentSymbol;
				prevSymbol = currentSymbol;
			}
		} else {
			arabicNum = -1;
		}

		return arabicNum;
	}
	
	public boolean isValidRomanNum(String romanNumInput){
		boolean isValid = false;
		
		String romanNum = romanNumInput.toUpperCase();

		if (romanNum != null && !romanNum.isEmpty()
				&& romanNum.matches("^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")) {
			isValid = true;
		}
		return isValid;
	}
}
