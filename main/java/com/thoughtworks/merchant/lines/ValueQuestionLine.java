package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.CommodityCalculator;

//Example Value Question Line: "how many Credits is glob prok Silver ?"
public class ValueQuestionLine extends GenericLine {
	
	protected double totalValue;
	
	public ValueQuestionLine(String line, String regex) {
		super(line, regex);
	}

	// Parse this line and extract the two pieces of information
	// qtyGalactic = "glob prok"
    // commodity = "Silver"
	protected void parse() {
		
		isAssignmentLine = false;
		
		Pattern ptn = Pattern.compile(regex);
		
		Matcher mcher = ptn.matcher(line);
		mcher.matches();

		qtyGalactic = mcher.group(1);
		commodity = mcher.group(2).trim();
	}
	
	protected boolean isLineValid() {
		boolean isValid;
		
		validateGalacticNum();
		validateCommodity();
		
		if (isGalacticNumValid && isCommodityValid){
			isValid = true;
		} else {
			isValid = false;
		}
		
		return isValid;
	}
	
	protected void calculateAnswer() {
		// Calculate total value for this commodity for the given quantity
		CommodityCalculator commodityCalculator = Factory.getCommodityCalculatorObject();
		totalValue = commodityCalculator.calculateTotalValue(commodity, qtyGalactic);
	}

	protected void formatValidAnswer() {
		if (totalValue != 0) {
			validOutputLine = qtyGalactic + commodity + " is " + (long) totalValue + " Credits";
		}
	}	 
}
