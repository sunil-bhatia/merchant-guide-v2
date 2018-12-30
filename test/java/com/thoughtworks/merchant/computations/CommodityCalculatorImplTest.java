package com.thoughtworks.merchant.computations;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.AliasMap;
import com.thoughtworks.merchant.interfaces.CommodityCalculator;

public class CommodityCalculatorImplTest {
	
	AliasMap aliasMap;
    CommodityCalculator commodityCalculator;
	
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
	
    @After
    public void teardown() {
		aliasMap.getAliasMap().clear();
    }
}
