package com.thoughtworks.merchant.computations;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.GalacticMap;
import com.thoughtworks.merchant.interfaces.GalacticCalculator;

public class GalacticCalculatorImplTest {
	
	GalacticMap galacticMap;
	GalacticCalculator galacticCalculator;
	
    @Before
    public void setup() {
    	
    	// Configure properties
    	String[] args = {"config"};
		ConfigPropertiesManager.configureProperties(args);
		
		// Set up galactic map
		galacticMap = (GalacticMap) Factory.getObject("galacticMap");
		galacticMap.addMapping("glob", 'I');
		galacticMap.addMapping("prok", 'V');
		galacticMap.addMapping("pish", 'X');
		galacticMap.addMapping("tegj", 'L');
		
		galacticCalculator = (GalacticCalculator) Factory.getObject("galacticCalculator");
    }
     
	@Test
	public void testGalacticNumGlobIsValid() {

		String galacticNum = "glob";
		
		boolean expectedResult = true;		
		boolean calculatedResult = galacticCalculator.isValidGalacticNum(galacticNum);	
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumGlobGlobIsValid() {

		String galacticNum = "glob glob";
		
		boolean expectedResult = true;	
		boolean calculatedResult = galacticCalculator.isValidGalacticNum(galacticNum);		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumPishPishIsValid() {

		String galacticNum = "pish pish";
		
		boolean expectedResult = true;		
		boolean calculatedResult = galacticCalculator.isValidGalacticNum(galacticNum);		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumEfghIsInvalid() {

		String galacticNum = "efgh";
		
		boolean expectedResult = false;	
		boolean calculatedResult = galacticCalculator.isValidGalacticNum(galacticNum);	
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumProkEfghIsInvalid() {

		String galacticNum = "prok efgh";
		
		boolean expectedResult = false;	
		boolean calculatedResult = galacticCalculator.isValidGalacticNum(galacticNum);	
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumGlobGlobIsArabic2() {

		String galacticNum = "glob glob";
		
		int expectedArabicNum = 2;	
		int convertedArabicNum = galacticCalculator.galacticToArabic(galacticNum);	
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	
	@Test
	public void testGalacticNumGlobProkIsArabic4() {

		String galacticNum = "glob prok";
		
		int expectedArabicNum = 4;	
		int convertedArabicNum = galacticCalculator.galacticToArabic(galacticNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
}
