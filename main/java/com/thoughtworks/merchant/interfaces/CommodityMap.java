package com.thoughtworks.merchant.interfaces;

public interface CommodityMap {

	public void addValuePerUnit(String commodity, double valuePerUnit);

	public double getValuePerUnit(String commodity);

	public boolean isCommodityValid(String commodity);
}
