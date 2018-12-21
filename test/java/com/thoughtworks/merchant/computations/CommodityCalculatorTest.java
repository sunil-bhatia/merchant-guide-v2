package com.thoughtworks.merchant.computations;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.computations.AliasMapManager;
import com.thoughtworks.merchant.computations.CommodityCalculator;

public class CommodityCalculatorTest {
	
    @Before
    public void setupAliasMap() {
    	AliasMapManager.addMapping("glob", 'I');
    	AliasMapManager.addMapping("prok", 'V');
    	AliasMapManager.addMapping("pish", 'X');
    	AliasMapManager.addMapping("tegj", 'L');
    }

	@Test
	public void testCalculatedValuePerUnit() {
		String qtyGalactic = "pish pish";
		int value = 3910;

		double expectedValuePerUnit = 195.5;

		double calculatedValuePerUnit = CommodityCalculator.calculateValuePerUnit(value, qtyGalactic);
		assertEquals(expectedValuePerUnit, calculatedValuePerUnit, 0.001);
	}
	
    @After
    public void teardownAliasMap() {
    	AliasMapManager.getAliasMap().clear();
    }
}
