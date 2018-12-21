package com.thoughtworks.merchant.computations;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.iomanagers.ConfigPropertiesManager;

public class RomanNumeralsImplTest {
	
    @Before
    public void setupConfigProperties() {
    	String[] args = {"config"};
		ConfigPropertiesManager.configureProperties(args);
    }

	@Test
	public void testRomanIis1() {
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		String romanNum = "i";
		int expectedArabicNum = 1;
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}

	@Test
	public void testRomanIIis2() {
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		String romanNum = "ii";
		int expectedArabicNum = 2;
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testRomanIIIis3() {
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		String romanNum = "iii";
		int expectedArabicNum = 3;
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testRomanIVis4() {
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		String romanNum = "iv";
		int expectedArabicNum = 4;
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testRomanVis5() {
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		String romanNum = "v";
		int expectedArabicNum = 5;
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testRomanVIis6() {
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		String romanNum = "vi";
		int expectedArabicNum = 6;
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testRomanVIIis7() {
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		String romanNum = "VII";
		int expectedArabicNum = 7;
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testRomanVIIIis8() {
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		String romanNum = "VIII";
		int expectedArabicNum = 8;
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testRomanIXis9() {
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		String romanNum = "IX";
		int expectedArabicNum = 9;
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testRomanXis10() {
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		String romanNum = "X";
		int expectedArabicNum = 10;
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testRomanXXXIXis39() {
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		String romanNum = "XXXIX";
		int expectedArabicNum = 39;
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testRomanMDCCLXXVIis1776() {
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		String romanNum = "MDCCLXXVI";
		int expectedArabicNum = 1776;
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testInvalidRomanIIIIReturnsMinus1() {
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		String romanNum = "IIII";
		int expectedArabicNum = -1;
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testInvalidRomanIIXReturnsMinus1() {
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		String romanNum = "IIX";
		int expectedArabicNum = -1;
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testInvalidRomanDDReturnsMinus1() {
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		String romanNum = "DD";
		int expectedArabicNum = -1;
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testInvalidRomanILReturnsMinus1() {
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		String romanNum = "IL";
		int expectedArabicNum = -1;
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	@Test
	public void testInvalidRomanJReturnsMinus1() {
		RomanNumerals romanNumerals = Factory.getRomanNumeralsObject();
		String romanNum = "j";
		int expectedArabicNum = -1;
		int convertedArabicNum = romanNumerals.romanToArabic(romanNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
}
