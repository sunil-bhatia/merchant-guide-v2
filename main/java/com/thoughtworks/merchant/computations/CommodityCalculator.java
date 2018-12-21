package com.thoughtworks.merchant.computations;

public interface CommodityCalculator {

	public double calculateValuePerUnit(int value, String qtyGalactic);

	public double calculateTotalValue(String commodity, String qtyGalactic);

}
