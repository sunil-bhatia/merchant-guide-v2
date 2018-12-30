package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.CommodityCalculator;

//Example Value Question Line: "how many Credits is glob prok Silver ?"
public class ValueQuestionLine extends GenericLine {
	
	private double totalValue;
	
	private CommodityCalculator commodityCalculator = (CommodityCalculator) Factory.getObject("commodityCalculator");
	
	public ValueQuestionLine() {
		super();
	}

	// Example: qtyGalactic = "glob prok"
    // 			commodity = "Silver"
	protected void extractData(Matcher mcher) {
		qtyGalactic = mcher.group(1);
		commodity = mcher.group(2).trim();
	}
	
	protected void calculateAnswer() {
		totalValue = commodityCalculator.calculateTotalValue(commodity, qtyGalactic);
	}

	protected String formatValidAnswer() {
		return (qtyGalactic + commodity + " is " + (long) totalValue + " Credits");
	}	 
}
