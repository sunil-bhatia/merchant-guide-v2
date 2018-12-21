package com.thoughtworks.merchant.computations;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.computations.CommodityCalculator;
import com.thoughtworks.merchant.factory.Factory;

public class CommodityCalculatorTest {
	
    @Before
    public void setupAliasMap() {
		AliasMap aliasMap = Factory.getAliasMapObject();
		aliasMap.addMapping("glob", 'I');
		aliasMap.addMapping("prok", 'V');
		aliasMap.addMapping("pish", 'X');
		aliasMap.addMapping("tegj", 'L');
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
		AliasMap aliasMap = Factory.getAliasMapObject();
		aliasMap.getAliasMap().clear();
    }
}
