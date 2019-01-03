package com.thoughtworks.merchant.computations;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.factory.FileConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.computations.CommodityCalculator;
import com.thoughtworks.merchant.interfaces.computations.GalacticMap;
import com.thoughtworks.merchant.interfaces.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.factory.GeneralFactory;

public class CommodityCalculatorImplTest {
	
	GalacticMap galacticMap;
    CommodityCalculator commodityCalculator;
	
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
		
	    commodityCalculator = (CommodityCalculator) factory.getObject("CommodityCalculator");
    }
    
	@Test
	public void testCalculatedValuePerUnit() {
		
		String galacticNumber = "pish pish";
		int value = 3910;

		double expectedValuePerUnit = 195.5;
		double calculatedValuePerUnit = commodityCalculator.calculateValuePerUnit(value, galacticNumber);
		assertEquals(expectedValuePerUnit, calculatedValuePerUnit, 0.001);
	}
}
