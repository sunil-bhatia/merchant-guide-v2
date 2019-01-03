package com.thoughtworks.merchant.computations;

import java.util.HashMap;

import com.thoughtworks.merchant.interfaces.computations.CommodityMap;

// This class manages the map from Commodity to it's Value Per Unit Quantity (in credits)
public class CommodityMapImpl implements CommodityMap {

	private static HashMap<String, Double> commodityMap = new HashMap<String, Double>();

	@Override
	public void addValuePerUnit(String commodity, double valuePerUnit) {
			commodityMap.put(commodity, valuePerUnit);
	}
	
	@Override
	public double getValuePerUnit(String commodity) {
		double valuePerUnit = 0.0;
		if (isCommodityValid(commodity)) {
			valuePerUnit = commodityMap.get(commodity);
		}
		return valuePerUnit;
	}
	
	@Override
	public boolean isCommodityValid(String commodity){
		return commodityMap.containsKey(commodity);
	}
}
