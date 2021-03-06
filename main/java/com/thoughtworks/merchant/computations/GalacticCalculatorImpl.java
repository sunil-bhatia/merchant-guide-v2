package com.thoughtworks.merchant.computations;

import com.thoughtworks.merchant.interfaces.computations.GalacticCalculator;
import com.thoughtworks.merchant.interfaces.computations.GalacticMap;
import com.thoughtworks.merchant.interfaces.computations.RomanCalculator;

//This class is used to convert from galactic numbers to arabic numbers
public class GalacticCalculatorImpl implements GalacticCalculator {
	
	private GalacticMap galacticMap;
	private RomanCalculator romanCalculator;

	@Override
	public int galacticToArabic(String galacticNum) {
		int arabicNum = 0;
				
		if (isGalacticNumValid(galacticNum)) {
			String romanNum = galacticToRoman(galacticNum);
			arabicNum = romanCalculator.romanToArabic(romanNum);
		}
		return arabicNum;
	}

	private  String galacticToRoman(String galacticNum) {
		String romanNum = "";

		String[] galacticSymbolArray = galacticNum.split(" ");

		for (int i = 0; i < galacticSymbolArray.length; i++) {
			romanNum += galacticMap.getRomanSymbol(galacticSymbolArray[i]);
		}
		return romanNum;
	}

	@Override
	public boolean isGalacticNumValid(String galacticNum) {
		boolean isGalacticNumValid = false;
		
		//Two conditions to be met for this to be true
		//1. Galactic symbols in this galactic num should be valid
		//2. Roman number should be a valid roman number
		
		boolean areGalacticSymbolsValid = areGalacticSymbolsValid(galacticNum);
		boolean isRomanNumValid = false;
			
		String romanNum = "";
		if (areGalacticSymbolsValid){
			romanNum = galacticToRoman(galacticNum);
			if (romanCalculator.isRomanNumValid(romanNum)){
				isRomanNumValid = true;
			}
		}
		
		if (areGalacticSymbolsValid && isRomanNumValid){
			isGalacticNumValid = true;
		}
		
		return isGalacticNumValid;
	}
	

	private boolean areGalacticSymbolsValid(String galacticNum) {		
		boolean areGalacticSymbolsValid = false;
		boolean invalidGalacticSymbolFound = false;
		
		String[] galacticSymbolArray = galacticNum.split(" ");

		for (int i = 0; i < galacticSymbolArray.length; i++) {
			if (!galacticMap.isGalacticSymbolValid(galacticSymbolArray[i])) {
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
	
	public void setGalacticMap(Object galacticMap) {
		this.galacticMap = (GalacticMap) galacticMap;
	}

	public void setRomanCalculator(Object romanCalculator) {
		this.romanCalculator = (RomanCalculator) romanCalculator;
	}
}
