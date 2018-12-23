package com.thoughtworks.merchant.interfaces;

public interface CommodityCalculator {

	public double calculateValuePerUnit(int value, String qtyGalactic);

	public double calculateTotalValue(String commodity, String qtyGalactic);

}
