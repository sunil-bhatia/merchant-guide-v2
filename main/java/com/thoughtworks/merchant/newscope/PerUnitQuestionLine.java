package com.thoughtworks.merchant.newscope;

import java.util.regex.Matcher;

import com.thoughtworks.merchant.lines.GenericLine;

//Example Per Unit Question Line: "how many Credits is Silver per unit quantity ?"
public class PerUnitQuestionLine extends GenericLine {
	
	protected double valuePerUnit;
	
	public PerUnitQuestionLine() {
		super();
	}

    // Example: commodity = "Silver"
	protected void extractData(Matcher mcher) {
		commodity = mcher.group(1).trim();
	}
	
	protected void calculateAnswer() {
		valuePerUnit = commodityMap.getValuePerUnit(commodity);
	}

	protected void formatValidAnswer() {
		validOutputLine = "Per unit quantity " + commodity + " is " + (long) valuePerUnit + " Credits";
	}
}
