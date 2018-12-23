package com.thoughtworks.merchant.computations;

import java.util.HashMap;

import com.thoughtworks.merchant.interfaces.CommodityMap;

// This class manages the map from Commodity to it's Value Per Unit Quantity (in credits)
public class CommodityMapImpl implements CommodityMap {

	private static HashMap<String, Double> commodityMap = new HashMap<String, Double>();

	// Example:
	// commodity = "Silver"
	// valuePerUnit = 17
	public void addValuePerUnit(String commodity, double valuePerUnit) {
			commodityMap.put(commodity, valuePerUnit);
	}
	
	public double getValuePerUnit(String commodity) {
		double valuePerUnit = 0.0;
		if (isValidCommodity(commodity)) {
			valuePerUnit = commodityMap.get(commodity);
		}
		return valuePerUnit;
	}
	
	public boolean isValidCommodity(String commodity){
		boolean isValid;
		if (commodityMap.containsKey(commodity)){
			isValid = true;
		} else {
			isValid = false;
		}
		return isValid;
	}

	public HashMap<String, Double> getCommodityMap() {
		return commodityMap;
	}
}
