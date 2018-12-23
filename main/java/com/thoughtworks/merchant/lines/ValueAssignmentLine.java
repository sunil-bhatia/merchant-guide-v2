package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.CommodityCalculator;
import com.thoughtworks.merchant.interfaces.CommodityMap;

//Example Value Assignment Line: "glob glob Silver is 34 Credits"
public class ValueAssignmentLine extends GenericAssignmentLine {

	private int value;
	private double valuePerUnit;
	
	public ValueAssignmentLine(String line, String regex) {
		super(line, regex);
	}

	// Parse this line and extract the three pieces of information
	// qtyGalactic = "glob glob"
	// commodity = "Silver"
	// value = 34
	protected void parse() {

		Pattern ptn = Pattern.compile(regex);

		Matcher mcher = ptn.matcher(line);
		mcher.matches();

		qtyGalactic = mcher.group(1);
		commodity = mcher.group(2);
		value = Integer.parseInt(mcher.group(3).trim());
	}
	
	@Override
	protected boolean isLineValid() {
		validateGalacticNum();
		return isGalacticNumValid;
	}
	
	protected void calculateAssignedData(){
		// Calculate value per unit quantity for the commodity
		CommodityCalculator commodityCalculator = Factory.getCommodityCalculatorObject();
		valuePerUnit = commodityCalculator.calculateValuePerUnit(value, qtyGalactic);
	}
	
	protected void addAssignedData(){
		// Add commodity and value per unit to the commodity map
		CommodityMap commodityMap = Factory.getCommodityMapObject();
		commodityMap.addValuePerUnit(commodity, valuePerUnit);
	}
}
