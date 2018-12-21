package com.thoughtworks.merchant.computations;

//This class calculates the answer for questions like:
//"how many Credits is glob prok Silver ?"
//It calculates the value (in credits) for a commodity given some quantity of the commodity
public class CommodityCalculator {
	
	// Example:
	// value = 34
	// qtyGalactic = "glob glob"
	public static double calculateValuePerUnit(int value, String qtyGalactic) {
		double valuePerUnit = 0;
		
		int qtyArabic = GalacticNumerals.galacticToArabic(qtyGalactic);
		
		if (qtyArabic != 0){
			valuePerUnit = (double) value / qtyArabic;
		}

		return valuePerUnit;
	}
	
	// Example:
    // commodity = "Silver"
	// qtyGalactic = "glob prok"
	public static double calculateTotalValue(String commodity,  String qtyGalactic){
		
		int qtyArabic = GalacticNumerals.galacticToArabic(qtyGalactic);
		double valuePerUnit = CommodityMapManager.getValuePerUnit(commodity);

		double totalValue = qtyArabic * valuePerUnit;
		
        return totalValue;
	}
}
