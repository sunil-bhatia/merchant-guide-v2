package com.thoughtworks.merchant.computations;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.factory.FileConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.computations.GalacticMap;
import com.thoughtworks.merchant.interfaces.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.factory.GeneralFactory;

public class GalacticMapImplTest {
	
	GalacticMap galacticMap;
	
    @Before
    public void setup() {
    	
    	// Configure properties
    	String[] args = {"config"};
		ConfigPropertiesManager configPropertiesManager = new FileConfigPropertiesManager();
		configPropertiesManager.readConfigProperties(args);
		
		// Set up galactic map
		GeneralFactory factory = configPropertiesManager.getGeneralFactoryObject();
		galacticMap = (GalacticMap) factory.getObject("GalacticMap");
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
