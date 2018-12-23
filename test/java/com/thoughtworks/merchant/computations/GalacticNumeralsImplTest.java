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
	
    @Before
    public void setupAliasMap() {
    	
    	// Configure properties
    	String[] args = {"config"};
		ConfigPropertiesManager.configureProperties(args);
		
		// Set up alias map
		AliasMap aliasMap = Factory.getAliasMapObject();
		aliasMap.addMapping("glob", 'I');
		aliasMap.addMapping("prok", 'V');
		aliasMap.addMapping("pish", 'X');
		aliasMap.addMapping("tegj", 'L');
    }
     
	@Test
	public void testGalacticNumGlobIsValid() {

		String galacticNum = "glob";
		
		boolean expectedResult = true;
		
		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
		boolean calculatedResult = galacticNumerals.isValidGalacticNum(galacticNum);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumGlobGlobIsValid() {

		String galacticNum = "glob glob";
		
		boolean expectedResult = true;
		
		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
		boolean calculatedResult = galacticNumerals.isValidGalacticNum(galacticNum);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumPishPishIsValid() {

		String galacticNum = "pish pish";
		
		boolean expectedResult = true;
		
		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
		boolean calculatedResult = galacticNumerals.isValidGalacticNum(galacticNum);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumEfghIsInvalid() {

		String galacticNum = "efgh";
		
		boolean expectedResult = false;
		
		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
		boolean calculatedResult = galacticNumerals.isValidGalacticNum(galacticNum);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumProkEfghIsInvalid() {

		String galacticNum = "prok efgh";
		
		boolean expectedResult = false;
		
		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
		boolean calculatedResult = galacticNumerals.isValidGalacticNum(galacticNum);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumGlobGlobIsArabic2() {

		String galacticNum = "glob glob";
		
		int expectedArabicNum = 2;
		
		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
		int convertedArabicNum = galacticNumerals.galacticToArabic(galacticNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	
	@Test
	public void testGalacticNumGlobProkIsArabic4() {

		String galacticNum = "glob prok";
		
		int expectedArabicNum = 4;
		
		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
		int convertedArabicNum = galacticNumerals.galacticToArabic(galacticNum);
		
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
    @After
    public void teardownAliasMap() {
		AliasMap aliasMap = Factory.getAliasMapObject();
		aliasMap.getAliasMap().clear();
    }
}
