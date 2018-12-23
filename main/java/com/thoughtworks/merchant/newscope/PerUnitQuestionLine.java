package com.thoughtworks.merchant.newscope;

import com.thoughtworks.merchant.lines.GenericLine;

//Example Per Unit Question Line: "how many Credits is Silver per unit quantity ?"
public class PerUnitQuestionLine extends GenericLine {
	
	public PerUnitQuestionLine(String line, String regex) {
		super(line, regex);
	}

    // Example: commodity = "Silver"
	protected void extractData() {
		commodity = mcher.group(1).trim();
	}
	
	protected void determineLineType() {
		isAssignmentLine = false;
	}
	
	protected boolean isLineValid() {
		validateCommodity();
		return isCommodityValid;
	}
	
	protected void calculateAnswer() {
		valuePerUnit = commodityMap.getValuePerUnit(commodity);
	}

	protected void formatValidAnswer() {
		validOutputLine = "Per unit quantity " + commodity + " is " + (long) valuePerUnit + " Credits";
	}
}
