package com.thoughtworks.merchant.computations;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.computations.AliasMapManager;
import com.thoughtworks.merchant.computations.GalacticNumerals;

public class GalacticNumeralsTest {
	
    @Before
    public void setupAliasMap() {
    	AliasMapManager.addMapping("glob", 'I');
    	AliasMapManager.addMapping("prok", 'V');
    	AliasMapManager.addMapping("pish", 'X');
    	AliasMapManager.addMapping("tegj", 'L');
    }
    
	@Test
	public void testGalacticNumGlobIsValid() {
		String galacticNum = "glob";
		boolean expectedResult = true;
		boolean calculatedResult = GalacticNumerals.isValidGalacticNum(galacticNum);
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumGlobGlobIsValid() {
		String galacticNum = "glob glob";
		boolean expectedResult = true;
		boolean calculatedResult = GalacticNumerals.isValidGalacticNum(galacticNum);
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumPishPishIsValid() {
		String galacticNum = "pish pish";
		boolean expectedResult = true;
		boolean calculatedResult = GalacticNumerals.isValidGalacticNum(galacticNum);
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumEfghIsInvalid() {
		String galacticNum = "efgh";
		boolean expectedResult = false;
		boolean calculatedResult = GalacticNumerals.isValidGalacticNum(galacticNum);
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumProkEfghIsInvalid() {
		String galacticNum = "prok efgh";
		boolean expectedResult = false;
		boolean calculatedResult = GalacticNumerals.isValidGalacticNum(galacticNum);
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumGlobGlobIsArabic2() {
		String galacticNum = "glob glob";
		int expectedArabicNum = 2;
		int convertedArabicNum = GalacticNumerals.galacticToArabic(galacticNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	
	@Test
	public void testGalacticNumGlobProkIsArabic4() {
		String galacticNum = "glob prok";
		int expectedArabicNum = 4;
		int convertedArabicNum = GalacticNumerals.galacticToArabic(galacticNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
    @After
    public void teardownAliasMap() {
    	AliasMapManager.getAliasMap().clear();
    }
}
