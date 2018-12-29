package com.thoughtworks.merchant.computations;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.AliasMap;
import com.thoughtworks.merchant.interfaces.GalacticNumerals;

public class GalacticNumeralsImplTest {
	
	GalacticNumerals galacticNumerals;
	
    @Before
    public void setup() {
    	
    	// Configure properties
    	String[] args = {"config"};
		ConfigPropertiesManager.configureProperties(args);
		
		// Set up alias map
		AliasMap aliasMap = (AliasMap) Factory.getObject("aliasMap");
		aliasMap.addMapping("glob", 'I');
		aliasMap.addMapping("prok", 'V');
		aliasMap.addMapping("pish", 'X');
		aliasMap.addMapping("tegj", 'L');
		
		galacticNumerals = (GalacticNumerals) Factory.getObject("galacticNumerals");
    }
     
	@Test
	public void testGalacticNumGlobIsValid() {

		String galacticNum = "glob";
		
		boolean expectedResult = true;
		
		boolean calculatedResult = galacticNumerals.isValidGalacticNum(galacticNum);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumGlobGlobIsValid() {

		String galacticNum = "glob glob";
		
		boolean expectedResult = true;
		
		boolean calculatedResult = galacticNumerals.isValidGalacticNum(galacticNum);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumPishPishIsValid() {

		String galacticNum = "pish pish";
		
		boolean expectedResult = true;
		
		boolean calculatedResult = galacticNumerals.isValidGalacticNum(galacticNum);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumEfghIsInvalid() {

		String galacticNum = "efgh";
		
		boolean expectedResult = false;
		
		boolean calculatedResult = galacticNumerals.isValidGalacticNum(galacticNum);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumProkEfghIsInvalid() {

		String galacticNum = "prok efgh";
		
		boolean expectedResult = false;
		
		boolean calculatedResult = galacticNumerals.isValidGalacticNum(galacticNum);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumGlobGlobIsArabic2() {

		String galacticNum = "glob glob";
		
		int expectedArabicNum = 2;
		
		int convertedArabicNum = galacticNumerals.galacticToArabic(galacticNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	
	@Test
	public void testGalacticNumGlobProkIsArabic4() {

		String galacticNum = "glob prok";
		
		int expectedArabicNum = 4;
		
		int convertedArabicNum = galacticNumerals.galacticToArabic(galacticNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
    @After
    public void teardownAliasMap() {
		AliasMap aliasMap = (AliasMap) Factory.getObject("aliasMap");
		aliasMap.getAliasMap().clear();
    }
}
