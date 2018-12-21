package com.thoughtworks.merchant.computations;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.computations.AliasMapManager;

public class AliasMapManagerTest {
	
    @Before
    public void setupAliasMap() {
    	AliasMapManager.addMapping("glob", 'I');
    	AliasMapManager.addMapping("prok", 'V');
    	AliasMapManager.addMapping("pish", 'X');
    	AliasMapManager.addMapping("tegj", 'L');
    }

	@Test
	public void testGalacticSymbolGlobIsValid() {
		String galacticSymbol = "glob";
		boolean expectedResult = true;
		boolean calculatedResult = AliasMapManager.isValidGalacticSymbol(galacticSymbol);
		assertEquals(expectedResult, calculatedResult);
	}
	

	@Test
	public void testGalacticSymbolProkIsValid() {
		String galacticSymbol = "prok";
		boolean expectedResult = true;
		boolean calculatedResult = AliasMapManager.isValidGalacticSymbol(galacticSymbol);
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticSymbolPishIsValid() {
		String galacticSymbol = "pish";
		boolean expectedResult = true;
		boolean calculatedResult = AliasMapManager.isValidGalacticSymbol(galacticSymbol);
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticSymbolAbcdIsInvalid() {
		String galacticSymbol = "abcd";
		boolean expectedResult = false;
		boolean calculatedResult = AliasMapManager.isValidGalacticSymbol(galacticSymbol);
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticSymbolGlotIsInvalid() {
		String galacticSymbol = "glot";
		boolean expectedResult = false;
		boolean calculatedResult = AliasMapManager.isValidGalacticSymbol(galacticSymbol);
		assertEquals(expectedResult, calculatedResult);
	}
	
    @After
    public void teardownAliasMap() {
    	AliasMapManager.getAliasMap().clear();
    }
}
