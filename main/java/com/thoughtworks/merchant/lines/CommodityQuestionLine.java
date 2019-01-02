package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;

import com.thoughtworks.merchant.factory.FactoryImpl;
import com.thoughtworks.merchant.interfaces.CommodityCalculator;
import com.thoughtworks.merchant.interfaces.Factory;

//Example Commodity Question Line: "how many Credits is glob prok Silver ?"
public class CommodityQuestionLine extends QuestionLine {
	
	private double totalValue;
	
	Factory factory = new FactoryImpl();
	private CommodityCalculator commodityCalculator = (CommodityCalculator) factory.getObject("commodityCalculator");
	
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
