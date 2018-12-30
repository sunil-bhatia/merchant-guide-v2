package com.thoughtworks.merchant.computations;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.GalacticMap;
import com.thoughtworks.merchant.interfaces.CommodityCalculator;

public class CommodityCalculatorImplTest {
	
	GalacticMap galacticMap;
    CommodityCalculator commodityCalculator;
	
    @Before
    public void setup() {
    	
    	// Configure properties
    	String[] args = {"config"};
		ConfigPropertiesManager.configureProperties(args);
		
		// Set up galactic map
		galacticMap = (GalacticMap) Factory.getObject("galacticMap");
		galacticMap.addMapping("glob", 'I');
		galacticMap.addMapping("prok", 'V');
		galacticMap.addMapping("pish", 'X');
		galacticMap.addMapping("tegj", 'L');
		
	    commodityCalculator = (CommodityCalculator) Factory.getObject("commodityCalculator");
    }
    
	@Test
	public void testCalculatedValuePerUnit() {
		
		String qtyGalactic = "pish pish";
		int value = 3910;

		double expectedValuePerUnit = 195.5;
		double calculatedValuePerUnit = commodityCalculator.calculateValuePerUnit(value, qtyGalactic);
		assertEquals(expectedValuePerUnit, calculatedValuePerUnit, 0.001);
	}
}
