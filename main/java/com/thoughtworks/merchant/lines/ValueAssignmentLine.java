package com.thoughtworks.merchant.lines;

//Example Value Assignment Line: "glob glob Silver is 34 Credits"
public class ValueAssignmentLine extends GenericLine {

	public ValueAssignmentLine(String line, String regex) {
		super(line, regex);
	}

	// Example: qtyGalactic = "glob glob"
	// 			commodity = "Silver"
	// 			value = 34
	protected void extractData() {
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
