package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;

import com.thoughtworks.merchant.factory.FactoryImpl;
import com.thoughtworks.merchant.interfaces.CommodityCalculator;
import com.thoughtworks.merchant.interfaces.Factory;

//Example Commodity Assignment Line: "glob glob Silver is 34 Credits"
public class CommodityAssignmentLine extends AssignmentLine {
	
	private int value;
	private double valuePerUnit;
	
	Factory factory = new FactoryImpl();
	private CommodityCalculator commodityCalculator = (CommodityCalculator) factory.getObject("CommodityCalculator");

	public CommodityAssignmentLine() {
		super();
	}

	@Override
	protected void extractData(Matcher mcher) {
		galacticNumber = mcher.group(1);
		commodity = mcher.group(2);
		value = Integer.parseInt(mcher.group(3).trim());
	}
	
	@Override
	protected boolean isDataValid() {
		return isGalacticNumValid();
	}
	
	@Override
	protected void calculateAssignedData(){
		valuePerUnit = commodityCalculator.calculateValuePerUnit(value, galacticNumber);
	}
	
	@Override
	protected void addAssignedData(){
		commodityMap.addValuePerUnit(commodity, valuePerUnit);
	}
}
