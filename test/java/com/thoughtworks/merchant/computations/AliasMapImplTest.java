package com.thoughtworks.merchant.computations;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.AliasMap;

public class AliasMapImplTest {
	
	AliasMap aliasMap;
	
    @Before
    public void setup() {
    	
    	// Configure properties
    	String[] args = {"config"};
		ConfigPropertiesManager.configureProperties(args);
		
		// Set up alias map
		aliasMap = (AliasMap) Factory.getObject("aliasMap");
		aliasMap.addMapping("glob", 'I');
		aliasMap.addMapping("prok", 'V');
		aliasMap.addMapping("pish", 'X');
		aliasMap.addMapping("tegj", 'L');
    }
    
	@Test
	public void testGalacticSymbolGlobIsValid() {
		
		String galacticSymbol = "glob";
		boolean expectedResult = true;
		boolean calculatedResult = aliasMap.isValidGalacticSymbol(galacticSymbol);
		
		assertEquals(expectedResult, calculatedResult);
	}
	

	@Test
	public void testGalacticSymbolProkIsValid() {

		String galacticSymbol = "prok";		
		boolean expectedResult = true;		
		boolean calculatedResult = aliasMap.isValidGalacticSymbol(galacticSymbol);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticSymbolPishIsValid() {

		String galacticSymbol = "pish";		
		boolean expectedResult = true;		
		boolean calculatedResult = aliasMap.isValidGalacticSymbol(galacticSymbol);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticSymbolAbcdIsInvalid() {

		String galacticSymbol = "abcd";		
		boolean expectedResult = false;		
		boolean calculatedResult = aliasMap.isValidGalacticSymbol(galacticSymbol);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticSymbolGlotIsInvalid() {

		String galacticSymbol = "glot";		
		boolean expectedResult = false;		
		boolean calculatedResult = aliasMap.isValidGalacticSymbol(galacticSymbol);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
    @After
    public void teardown() {
		aliasMap.getAliasMap().clear();
    }
}
