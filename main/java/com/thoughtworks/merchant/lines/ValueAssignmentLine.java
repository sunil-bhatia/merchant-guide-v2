package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.CommodityCalculator;

//Example Value Assignment Line: "glob glob Silver is 34 Credits"
public class ValueAssignmentLine extends GenericLine {
	
	private int value;
	private double valuePerUnit;
	
	private CommodityCalculator commodityCalculator = (CommodityCalculator) Factory.getObject("commodityCalculator");

	public ValueAssignmentLine() {
		super();
	}

	// Example: qtyGalactic = "glob glob"
	// 			commodity = "Silver"
	// 			value = 34
	protected void extractData(Matcher mcher) {
		qtyGalactic = mcher.group(1);
		commodity = mcher.group(2);
		value = Integer.parseInt(mcher.group(3).trim());
	}
	
	protected void calculateAssignedData(){
		valuePerUnit = commodityCalculator.calculateValuePerUnit(value, qtyGalactic);
	}
	
	protected void addAssignedData(){
		commodityMap.addValuePerUnit(commodity, valuePerUnit);
	}
}
