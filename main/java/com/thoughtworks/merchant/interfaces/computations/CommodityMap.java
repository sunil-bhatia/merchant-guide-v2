package com.thoughtworks.merchant.interfaces.computations;

public interface CommodityMap {

	public void addValuePerUnit(String commodity, double valuePerUnit);

	public double getValuePerUnit(String commodity);

	public boolean isCommodityValid(String commodity);
}
