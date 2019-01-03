package com.thoughtworks.merchant.computations;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.factory.FileConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.computations.GalacticCalculator;
import com.thoughtworks.merchant.interfaces.computations.GalacticMap;
import com.thoughtworks.merchant.interfaces.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.factory.GeneralFactory;

public class GalacticCalculatorImplTest {
	
	GalacticMap galacticMap;
	GalacticCalculator galacticCalculator;
	
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
		
		galacticCalculator = (GalacticCalculator) factory.getObject("GalacticCalculator");
    }
     
	@Test
	public void testGalacticNumGlobIsValid() {

		String galacticNum = "glob";
		
		boolean expectedResult = true;		
		boolean calculatedResult = galacticCalculator.isGalacticNumValid(galacticNum);	
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumGlobGlobIsValid() {

		String galacticNum = "glob glob";
		
		boolean expectedResult = true;	
		boolean calculatedResult = galacticCalculator.isGalacticNumValid(galacticNum);		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumPishPishIsValid() {

		String galacticNum = "pish pish";
		
		boolean expectedResult = true;		
		boolean calculatedResult = galacticCalculator.isGalacticNumValid(galacticNum);		
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumEfghIsInvalid() {

		String galacticNum = "efgh";
		
		boolean expectedResult = false;	
		boolean calculatedResult = galacticCalculator.isGalacticNumValid(galacticNum);	
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumProkEfghIsInvalid() {

		String galacticNum = "prok efgh";
		
		boolean expectedResult = false;	
		boolean calculatedResult = galacticCalculator.isGalacticNumValid(galacticNum);	
		assertEquals(expectedResult, calculatedResult);
	}
	
	@Test
	public void testGalacticNumGlobGlobIsArabic2() {

		String galacticNum = "glob glob";
		
		int expectedArabicNum = 2;	
		int convertedArabicNum = galacticCalculator.galacticToArabic(galacticNum);	
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
	
	
	@Test
	public void testGalacticNumGlobProkIsArabic4() {

		String galacticNum = "glob prok";
		
		int expectedArabicNum = 4;	
		int convertedArabicNum = galacticCalculator.galacticToArabic(galacticNum);
		assertEquals(expectedArabicNum, convertedArabicNum);
	}
}
