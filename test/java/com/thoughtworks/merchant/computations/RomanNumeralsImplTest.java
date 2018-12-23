package com.thoughtworks.merchant.computations;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.RomanNumerals;

public class RomanNumeralsImplTest {
	
    @Before
    public void setupConfigProperties() {
    	String[] args = {"config"};
		ConfigPropertiesManager.configureProperties(args);
    }

	@Test
	public void testRomanIis1() {

		String romanNum = "i";
		
		int expectedArabicNum = 1;
		
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}

	@Test
	public void testRomanIIis2() {

		String romanNum = "ii";
		
		int expectedArabicNum = 2;
		
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testRomanIIIis3() {

		String romanNum = "iii";
		
		int expectedArabicNum = 3;
		
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testRomanIVis4() {

		String romanNum = "iv";
		
		int expectedArabicNum = 4;
		
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testRomanVis5() {

		String romanNum = "v";
		
		int expectedArabicNum = 5;
		
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testRomanVIis6() {

		String romanNum = "vi";
		
		int expectedArabicNum = 6;
		
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testRomanVIIis7() {

		String romanNum = "VII";
		
		int expectedArabicNum = 7;
		
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testRomanVIIIis8() {

		String romanNum = "VIII";
		
		int expectedArabicNum = 8;
		
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testRomanIXis9() {

		String romanNum = "IX";
		
		int expectedArabicNum = 9;
		
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testRomanXis10() {

		String romanNum = "X";
		
		int expectedArabicNum = 10;
		
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testRomanXXXIXis39() {

		String romanNum = "XXXIX";
		
		int expectedArabicNum = 39;
		
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testRomanMDCCLXXVIis1776() {

		String romanNum = "MDCCLXXVI";
		
		int expectedArabicNum = 1776;
		
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testInvalidRomanIIIIReturnsMinus1() {

		String romanNum = "IIII";
		
		int expectedArabicNum = -1;
		
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testInvalidRomanIIXReturnsMinus1() {

		String romanNum = "IIX";
		
		int expectedArabicNum = -1;
		
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testInvalidRomanDDReturnsMinus1() {

		String romanNum = "DD";
		
		int expectedArabicNum = -1;
		
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testInvalidRomanILReturnsMinus1() {

		String romanNum = "IL";
		
		int expectedArabicNum = -1;
		
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testInvalidRomanJReturnsMinus1() {

		String romanNum = "j";
		
		int expectedArabicNum = -1;
		
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
}
