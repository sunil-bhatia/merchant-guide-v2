package com.thoughtworks.merchant.interfaces.computations;

public interface CommodityCalculator {

	public double calculateValuePerUnit(int value, String galacticNumber);

	public double calculateTotalValue(String commodity, String galacticNumber);
}
