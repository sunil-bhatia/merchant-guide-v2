package com.thoughtworks.merchant.computations;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.iomanagers.ConfigPropertiesManager;

public class AliasMapImplTest {
	
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
	public void testGalacticSymbolGlobIsValid() {
		AliasMap aliasMap = Factory.getAliasMapObject();
		String galacticSymbol = "glob";
		boolean expectedResult = true;
		boolean calculatedResult = aliasMap.isValidGalacticSymbol(galacticSymbol);
		assertEquals(expectedResult, calculatedResult);
	}
	

	@Test
	public void testGalacticSymbolProkIsValid() {
		AliasMap aliasMap = Factory.getAliasMapObject();
		String galacticSymbol = "prok";
		boolean expectedResult = true;
		boolean calculatedResult = aliasMap.isValidGalacticSymbol(galacticSymbol);
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticSymbolPishIsValid() {
		AliasMap aliasMap = Factory.getAliasMapObject();
		String galacticSymbol = "pish";
		boolean expectedResult = true;
		boolean calculatedResult = aliasMap.isValidGalacticSymbol(galacticSymbol);
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticSymbolAbcdIsInvalid() {
		AliasMap aliasMap = Factory.getAliasMapObject();
		String galacticSymbol = "abcd";
		boolean expectedResult = false;
		boolean calculatedResult = aliasMap.isValidGalacticSymbol(galacticSymbol);
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticSymbolGlotIsInvalid() {
		AliasMap aliasMap = Factory.getAliasMapObject();
		String galacticSymbol = "glot";
		boolean expectedResult = false;
		boolean calculatedResult = aliasMap.isValidGalacticSymbol(galacticSymbol);
		assertEquals(expectedResult, calculatedResult);
	}
	
    @After
    public void teardownAliasMap() {
		AliasMap aliasMap = Factory.getAliasMapObject();
		aliasMap.getAliasMap().clear();
    }
}
