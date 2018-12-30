package com.thoughtworks.merchant.computations;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.GalacticMap;
import com.thoughtworks.merchant.interfaces.GalacticCalculator;
import com.thoughtworks.merchant.interfaces.RomanCalculator;

public class GalacticCalculatorImpl implements GalacticCalculator {
	
	private GalacticMap galacticMap = (GalacticMap) Factory.getObject("galacticMap");
	private RomanCalculator romanCalculator = (RomanCalculator) Factory.getObject("romanCalculator");

	// Example: parameter galacticNum = "glob glob"
	// return arabicNum = 2
	public int galacticToArabic(String galacticNum) {
		int arabicNum = 0;
				
		if (isValidGalacticNum(galacticNum)) {
			String romanNum = galacticToRoman(galacticNum);
			arabicNum = romanCalculator.romanToArabic(romanNum);
		}
		return arabicNum;
	}

	// Example: parameter galacticNum = "glob glob"
	// return romanNum = "II"
	private  String galacticToRoman(String galacticNum) {
		String romanNum = "";

		String[] galacticSymbolArray = galacticNum.split(" ");

		for (int i = 0; i < galacticSymbolArray.length; i++) {
			romanNum += galacticMap.getRomanSymbol(galacticSymbolArray[i]);
		}
		return romanNum;
	}

	public boolean isValidGalacticNum(String galacticNum) {
		boolean isValidGalacticNum = false;
		
		//Two conditions to be met for this to be true
		//1. Galactic symbols in this galactic num should be valid
		//2. Roman number should be a valid roman number
		
		boolean areGalacticSymbolsValid = areGalacticSymbolsValid(galacticNum);
		boolean isRomanNumValid = false;
			
		String romanNum = "";
		if (areGalacticSymbolsValid){
			romanNum = galacticToRoman(galacticNum);
			if (romanCalculator.isValidRomanNum(romanNum)){
				isRomanNumValid = true;
			}
		}
		
		if (areGalacticSymbolsValid && isRomanNumValid){
			isValidGalacticNum = true;
		}
		
		return isValidGalacticNum;
	}
	

	private boolean areGalacticSymbolsValid(String galacticNum) {		
		boolean areGalacticSymbolsValid = false;
		boolean invalidGalacticSymbolFound = false;
		
		String[] galacticSymbolArray = galacticNum.split(" ");

		for (int i = 0; i < galacticSymbolArray.length; i++) {
			// Check if galactic symbol is valid
			if (!galacticMap.isValidGalacticSymbol(galacticSymbolArray[i])) {
				invalidGalacticSymbolFound = true;
			}
		}
		
		if (invalidGalacticSymbolFound){
			areGalacticSymbolsValid = false;
		} else {
			areGalacticSymbolsValid = true;
		}
			
		return areGalacticSymbolsValid;
	}
}
