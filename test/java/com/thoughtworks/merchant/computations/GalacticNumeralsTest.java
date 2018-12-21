package com.thoughtworks.merchant.computations;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.computations.GalacticNumerals;
import com.thoughtworks.merchant.factory.Factory;

public class GalacticNumeralsTest {
	
    @Before
    public void setupAliasMap() {
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
		AliasMap aliasMap = Factory.getAliasMapObject();
		aliasMap.getAliasMap().clear();
    }
}
