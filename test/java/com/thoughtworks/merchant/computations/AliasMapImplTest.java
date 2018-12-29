package com.thoughtworks.merchant.computations;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.AliasMap;

public class AliasMapImplTest {
	
    @Before
    public void setupAliasMap() {
    	
    	// Configure properties
    	String[] args = {"config"};
		ConfigPropertiesManager.configureProperties(args);
		
		// Set up alias map
		AliasMap aliasMap = (AliasMap) Factory.getObject("aliasMap");
		aliasMap.addMapping("glob", 'I');
		aliasMap.addMapping("prok", 'V');
		aliasMap.addMapping("pish", 'X');
		aliasMap.addMapping("tegj", 'L');
    }
    
	@Test
	public void testGalacticSymbolGlobIsValid() {
		
		String galacticSymbol = "glob";
		
		boolean expectedResult = true;
		
		AliasMap aliasMap = (AliasMap) Factory.getObject("aliasMap");
		boolean calculatedResult = aliasMap.isValidGalacticSymbol(galacticSymbol);
		
		assertEquals(expectedResult, calculatedResult);
	}
	

	@Test
	public void testGalacticSymbolProkIsValid() {

		String galacticSymbol = "prok";
		
		boolean expectedResult = true;
		
		AliasMap aliasMap = (AliasMap) Factory.getObject("aliasMap");
		boolean calculatedResult = aliasMap.isValidGalacticSymbol(galacticSymbol);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticSymbolPishIsValid() {

		String galacticSymbol = "pish";
		
		boolean expectedResult = true;
		
		AliasMap aliasMap = (AliasMap) Factory.getObject("aliasMap");
		boolean calculatedResult = aliasMap.isValidGalacticSymbol(galacticSymbol);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticSymbolAbcdIsInvalid() {

		String galacticSymbol = "abcd";
		
		boolean expectedResult = false;
		
		AliasMap aliasMap = (AliasMap) Factory.getObject("aliasMap");
		boolean calculatedResult = aliasMap.isValidGalacticSymbol(galacticSymbol);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticSymbolGlotIsInvalid() {

		String galacticSymbol = "glot";
		
		boolean expectedResult = false;
		
		AliasMap aliasMap = (AliasMap) Factory.getObject("aliasMap");
		boolean calculatedResult = aliasMap.isValidGalacticSymbol(galacticSymbol);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
    @After
    public void teardownAliasMap() {
		AliasMap aliasMap = (AliasMap) Factory.getObject("aliasMap");
		aliasMap.getAliasMap().clear();
    }
}
