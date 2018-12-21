package com.thoughtworks.merchant.computations;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.iomanagers.ConfigPropertiesManager;

public class GalacticNumeralsImplTest {
	
    @Before
    public void setupAliasMap() {
		AliasMap aliasMap = Factory.getAliasMapObject();
		aliasMap.addMapping("glob", 'I');
		aliasMap.addMapping("prok", 'V');
		aliasMap.addMapping("pish", 'X');
		aliasMap.addMapping("tegj", 'L');
    }
    
    @Before
    public void setupConfigProperties() {
    	String[] args = {"config"};
		ConfigPropertiesManager.configureProperties(args);
    }
    
	@Test
	public void testGalacticNumGlobIsValid() {
		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
		String galacticNum = "glob";
		boolean expectedResult = true;
		boolean calculatedResult = galacticNumerals.isValidGalacticNum(galacticNum);
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumGlobGlobIsValid() {
		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
		String galacticNum = "glob glob";
		boolean expectedResult = true;
		boolean calculatedResult = galacticNumerals.isValidGalacticNum(galacticNum);
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumPishPishIsValid() {
		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
		String galacticNum = "pish pish";
		boolean expectedResult = true;
		boolean calculatedResult = galacticNumerals.isValidGalacticNum(galacticNum);
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumEfghIsInvalid() {
		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
		String galacticNum = "efgh";
		boolean expectedResult = false;
		boolean calculatedResult = galacticNumerals.isValidGalacticNum(galacticNum);
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumProkEfghIsInvalid() {
		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
		String galacticNum = "prok efgh";
		boolean expectedResult = false;
		boolean calculatedResult = galacticNumerals.isValidGalacticNum(galacticNum);
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumGlobGlobIsArabic2() {
		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
		String galacticNum = "glob glob";
		int expectedArabicNum = 2;
		int convertedArabicNum = galacticNumerals.galacticToArabic(galacticNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	
	@Test
	public void testGalacticNumGlobProkIsArabic4() {
		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
		String galacticNum = "glob prok";
		int expectedArabicNum = 4;
		int convertedArabicNum = galacticNumerals.galacticToArabic(galacticNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
    @After
    public void teardownAliasMap() {
		AliasMap aliasMap = Factory.getAliasMapObject();
		aliasMap.getAliasMap().clear();
    }
}
