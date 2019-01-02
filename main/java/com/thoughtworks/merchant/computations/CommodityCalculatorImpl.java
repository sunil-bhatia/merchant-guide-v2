package com.thoughtworks.merchant.computations;

import com.thoughtworks.merchant.factory.FactoryImpl;
import com.thoughtworks.merchant.interfaces.CommodityCalculator;
import com.thoughtworks.merchant.interfaces.CommodityMap;
import com.thoughtworks.merchant.interfaces.Factory;
import com.thoughtworks.merchant.interfaces.GalacticCalculator;

// This class calculates the value (in credits) for a commodity given some quantity of the commodity
public class CommodityCalculatorImpl implements CommodityCalculator {
	
	Factory factory = new FactoryImpl();
	private CommodityMap commodityMap = (CommodityMap) factory.getObject("commodityMap");
	private GalacticCalculator galacticCalculator = (GalacticCalculator) factory.getObject("galacticCalculator");

	@Override
	public double calculateValuePerUnit(int value, String galacticNumber) {
		double valuePerUnit = 0;
		
		int arabicNumber = galacticCalculator.galacticToArabic(galacticNumber);
		
		if (arabicNumber != 0){
			valuePerUnit = (double) value / arabicNumber;
		}

		return valuePerUnit;
	}
	
	@Override
	public double calculateTotalValue(String commodity,  String galacticNumber){
		
		int arabicNumber = galacticCalculator.galacticToArabic(galacticNumber);
		double valuePerUnit = commodityMap.getValuePerUnit(commodity);

		double totalValue = arabicNumber * valuePerUnit;
		
        return totalValue;
	}
}
