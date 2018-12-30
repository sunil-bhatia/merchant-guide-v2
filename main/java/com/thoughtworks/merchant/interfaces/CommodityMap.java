package com.thoughtworks.merchant.interfaces;

//This interface defines methods to access the mapping between Commodity and it's Value Per Unit Quantity (in credits)
public interface CommodityMap {

	public void addValuePerUnit(String commodity, double valuePerUnit);

	public double getValuePerUnit(String commodity);

	public boolean isValidCommodity(String commodity);

}
