package com.thoughtworks.merchant.computations;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.AliasMap;
import com.thoughtworks.merchant.interfaces.GalacticNumerals;
import com.thoughtworks.merchant.interfaces.RomanNumerals;

public class GalacticNumeralsImpl implements GalacticNumerals {
	
	AliasMap aliasMap = (AliasMap) Factory.getObject("aliasMap");

	// Example: parameter galacticNum = "glob glob"
	// return arabicNum = 2
	public int galacticToArabic(String galacticNum) {
		int arabicNum = 0;
		
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		
		if (isValidGalacticNum(galacticNum)) {
			String romanNum = galacticToRoman(galacticNum);
			arabicNum = romanNumerals.romanToArabic(romanNum);
		}
		return arabicNum;
	}

	// Example: parameter galacticNum = "glob glob"
	// return romanNum = "II"
	private  String galacticToRoman(String galacticNum) {
		String romanNum = "";
		AliasMap aliasMap = (AliasMap) Factory.getObject("aliasMap");

		String[] galacticSymbolArray = galacticNum.split(" ");

		for (int i = 0; i < galacticSymbolArray.length; i++) {
			romanNum += aliasMap.getRomanSymbol(galacticSymbolArray[i]);
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
		
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		
		String romanNum = "";
		if (areGalacticSymbolsValid){
			romanNum = galacticToRoman(galacticNum);
			if (romanNumerals.isValidRomanNum(romanNum)){
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
		
		AliasMap aliasMap = (AliasMap) Factory.getObject("aliasMap");
		
		String[] galacticSymbolArray = galacticNum.split(" ");

		for (int i = 0; i < galacticSymbolArray.length; i++) {
			// Check if galactic symbol is valid
			if (!aliasMap.isValidGalacticSymbol(galacticSymbolArray[i])) {
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
