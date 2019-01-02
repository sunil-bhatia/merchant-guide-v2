package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;

//Example Commodity Question Line: "how many Credits is glob prok Silver ?"
public class CommodityQuestionLine extends QuestionLine {
	
	private double totalValue;
	
	public CommodityQuestionLine() {
		super();
	}

	@Override
	protected void extractData(Matcher mcher) {
		galacticNumber = mcher.group(1);
		commodity = mcher.group(2).trim();
	}
	
	@Override
	protected boolean isDataValid() {
		return (isGalacticNumValid() && isCommodityValid());
	}
	
	@Override
	protected void calculateAnswer() {
		totalValue = commodityCalculator.calculateTotalValue(commodity, galacticNumber);
	}

	@Override
	protected String formatValidAnswer() {
		return (galacticNumber + commodity + " is " + (long) totalValue + " Credits");
	}	 
}
