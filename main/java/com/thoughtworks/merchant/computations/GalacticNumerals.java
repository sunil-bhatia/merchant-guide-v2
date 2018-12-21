package com.thoughtworks.merchant.computations;

public class GalacticNumerals {

	// Example: parameter galacticNum = "glob glob"
	// return arabicNum = 2
	public static int galacticToArabic(String galacticNum) {
		int arabicNum = 0;
		if (isValidGalacticNum(galacticNum)) {
			String romanNum = galacticToRoman(galacticNum);
			arabicNum = RomanNumerals.romanToArabic(romanNum);
		}
		return arabicNum;
	}

	// Example: parameter galacticNum = "glob glob"
	// return romanNum = "II"
	private static String galacticToRoman(String galacticNum) {
		String romanNum = "";
		String[] galacticSymbolArray = galacticNum.split(" ");

		for (int i = 0; i < galacticSymbolArray.length; i++) {
			romanNum += AliasMapManager.getRomanSymbol(galacticSymbolArray[i]);
		}
		return romanNum;
	}

	public static boolean isValidGalacticNum(String galacticNum) {
		boolean isValidGalacticNum = false;
		
		//Two conditions to be met for this to be true
		//1. Galactic symbols in this galactic num should be valid
		//2. Roman number should be a valid roman number
		
		boolean areGalacticSymbolsValid = areGalacticSymbolsValid(galacticNum);
		boolean isRomanNumValid = false;
		
		String romanNum = "";
		if (areGalacticSymbolsValid){
			romanNum = galacticToRoman(galacticNum);
			if (RomanNumerals.isValidRomanNum(romanNum)){
				isRomanNumValid = true;
			}
		}
		
		if (areGalacticSymbolsValid && isRomanNumValid){
			isValidGalacticNum = true;
		}
		
		return isValidGalacticNum;
	}
	

	private static boolean areGalacticSymbolsValid(String galacticNum) {
		boolean areGalacticSymbolsValid = false;
		boolean invalidGalacticSymbolFound = false;
		
		String[] galacticSymbolArray = galacticNum.split(" ");

		for (int i = 0; i < galacticSymbolArray.length; i++) {
			// Check if galactic symbol is valid
			if (!AliasMapManager.isValidGalacticSymbol(galacticSymbolArray[i])) {
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
