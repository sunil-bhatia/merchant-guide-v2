package com.thoughtworks.merchant.computations;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.factory.FileConfigPropertiesManager;
import com.thoughtworks.merchant.factory.FactoryImpl;
import com.thoughtworks.merchant.interfaces.ConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.Factory;
import com.thoughtworks.merchant.interfaces.GalacticMap;

public class GalacticMapImplTest {
	
	GalacticMap galacticMap;
	
    @Before
    public void setup() {
    	
    	// Configure properties
    	String[] args = {"config"};
		ConfigPropertiesManager configPropertiesManager = new FileConfigPropertiesManager();
		configPropertiesManager.configureProperties(args);
		
		// Set up galactic map
		Factory factory = new FactoryImpl();
		galacticMap = (GalacticMap) factory.getObject("galacticMap");
		galacticMap.addMapping("glob", 'I');
		galacticMap.addMapping("prok", 'V');
		galacticMap.addMapping("pish", 'X');
		galacticMap.addMapping("tegj", 'L');
    }
    
	@Test
	public void testGalacticSymbolGlobIsValid() {
		
		String galacticSymbol = "glob";
		boolean expectedResult = true;
		boolean calculatedResult = galacticMap.isGalacticSymbolValid(galacticSymbol);
		
		assertEquals(expectedResult, calculatedResult);
	}
	

	@Test
	public void testGalacticSymbolProkIsValid() {

		String galacticSymbol = "prok";		
		boolean expectedResult = true;		
		boolean calculatedResult = galacticMap.isGalacticSymbolValid(galacticSymbol);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticSymbolPishIsValid() {

		String galacticSymbol = "pish";		
		boolean expectedResult = true;		
		boolean calculatedResult = galacticMap.isGalacticSymbolValid(galacticSymbol);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticSymbolAbcdIsInvalid() {

		String galacticSymbol = "abcd";		
		boolean expectedResult = false;		
		boolean calculatedResult = galacticMap.isGalacticSymbolValid(galacticSymbol);
		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticSymbolGlotIsInvalid() {

		String galacticSymbol = "glot";		
		boolean expectedResult = false;		
		boolean calculatedResult = galacticMap.isGalacticSymbolValid(galacticSymbol);
		
		assertEquals(expectedResult, calculatedResult);
	}
}
