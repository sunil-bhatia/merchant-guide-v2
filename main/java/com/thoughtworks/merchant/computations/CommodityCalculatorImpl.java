package com.thoughtworks.merchant.computations;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.CommodityCalculator;
import com.thoughtworks.merchant.interfaces.CommodityMap;
import com.thoughtworks.merchant.interfaces.GalacticNumerals;

//This class calculates the answer for questions like:
//"how many Credits is glob prok Silver ?"
//It calculates the value (in credits) for a commodity given some quantity of the commodity
public class CommodityCalculatorImpl implements CommodityCalculator {
	
	// Example:
	// value = 34
	// qtyGalactic = "glob glob"
	public double calculateValuePerUnit(int value, String qtyGalactic) {
		double valuePerUnit = 0;
		
		GalacticNumerals galacticNumerals = (GalacticNumerals) Factory.getObject("galacticNumerals");
		int qtyArabic = galacticNumerals.galacticToArabic(qtyGalactic);
		
		if (qtyArabic != 0){
			valuePerUnit = (double) value / qtyArabic;
		}

		return valuePerUnit;
	}
	
	// Example:
    // commodity = "Silver"
	// qtyGalactic = "glob prok"
	public double calculateTotalValue(String commodity,  String qtyGalactic){
		
		CommodityMap commodityMap = (CommodityMap) Factory.getObject("commodityMap");
		GalacticNumerals galacticNumerals = (GalacticNumerals) Factory.getObject("galacticNumerals");
		int qtyArabic = galacticNumerals.galacticToArabic(qtyGalactic);
		double valuePerUnit = commodityMap.getValuePerUnit(commodity);

		double totalValue = qtyArabic * valuePerUnit;
		
        return totalValue;
	}
}
