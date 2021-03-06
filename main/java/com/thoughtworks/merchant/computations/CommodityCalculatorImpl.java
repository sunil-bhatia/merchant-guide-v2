package com.thoughtworks.merchant.computations;

import com.thoughtworks.merchant.interfaces.computations.CommodityCalculator;
import com.thoughtworks.merchant.interfaces.computations.CommodityMap;
import com.thoughtworks.merchant.interfaces.computations.GalacticCalculator;

// This class calculates the value (in credits) for a commodity given some quantity of the commodity
public class CommodityCalculatorImpl implements CommodityCalculator {
	
	private CommodityMap commodityMap;
	private GalacticCalculator galacticCalculator;

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
	
	public void setCommodityMap(Object commodityMap) {
		this.commodityMap = (CommodityMap) commodityMap;
	}

	public void setGalacticCalculator(Object galacticCalculator) {
		this.galacticCalculator = (GalacticCalculator) galacticCalculator;
	}
}
