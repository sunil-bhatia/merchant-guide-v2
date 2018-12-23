package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.CommodityCalculator;
import com.thoughtworks.merchant.interfaces.CommodityMap;
import com.thoughtworks.merchant.interfaces.GalacticNumerals;
import com.thoughtworks.merchant.interfaces.Line;
import com.thoughtworks.merchant.interfaces.ListManager;

//Example Value Question Line: "how many Credits is glob prok Silver ?"
public class ValueQuestionLine implements Line {

	private String line;	
	private String regex;
	
	String commodity;
	String qtyGalactic;
	
	public ValueQuestionLine(String line, String regex) {
		this.line = line;
		this.regex = regex;
	}

	@Override
	public void process() {

		// Parse line and extract key fields
        parse();
        
		// Validate line
		if (isLineValid()){
        
			// Calculate total value for this commodity for the given quantity
			CommodityCalculator commodityCalculator = Factory.getCommodityCalculatorObject();
			double totalValue = commodityCalculator.calculateTotalValue(commodity, qtyGalactic);
        	
			//Format valid answer
			String outputLine = formatValidAnswer(totalValue);
		
			//Add the answer to the output lines
			ListManager outputLinesListManager = Factory.getOutputLinesListManagerObject();
			outputLinesListManager.addObject(outputLine);
		
		} else {
			
			String outputLine = formatInvalidAnswer();

			// Add the answer to the output lines
			ListManager outputLinesListManager = Factory.getOutputLinesListManagerObject();
			outputLinesListManager.addObject(outputLine);
		}
	}
	
	// Parse this line and extract the two pieces of information
	// qtyGalactic = "glob prok"
    // commodity = "Silver"
	private void parse() {
		
		Pattern ptn = Pattern.compile(regex);
		
		Matcher mcher = ptn.matcher(line);
		mcher.matches();

		qtyGalactic = mcher.group(1);
		commodity = mcher.group(2).trim();
	}

	private String formatValidAnswer(double totalValue) {
		String outputLine = "";

		if (totalValue != 0) {
			outputLine = qtyGalactic + commodity + " is " + (long) totalValue + " Credits";
		}

		return outputLine;
	}
	
	// Validate line
	private boolean isLineValid(){

		boolean isValid;
		
		// Two conditions have to be met, for this to be true
		// 1. Galactic number should be valid
		// 2. Commodity should be valid
		
		boolean isValidGalacticNum;
		boolean isValidCommodity;
		
		// Check if galactic number is valid
		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
		ListManager logsListManager = Factory.getLogsListManagerObject();

		if (galacticNumerals.isValidGalacticNum(qtyGalactic)) {
			isValidGalacticNum = true;
		} else {
			isValidGalacticNum = false;
			logsListManager.addObject("Invalid Galactic Number in Input Line : " + line);
		}
		
		// Check if Commodity is valid
		CommodityMap commodityMap = Factory.getCommodityMapObject();
		if (commodityMap.isValidCommodity(commodity)) {
			isValidCommodity = true;
		} else {
			isValidCommodity = false;
			logsListManager.addObject("Invalid Commodity in Input Line : " + line);
		}
		
		if (isValidGalacticNum && isValidCommodity){
			isValid = true;
		} else {
			isValid = false;
		}
		
		return isValid;
	}
	
	 private String formatInvalidAnswer() {
		String outputLine = "I have no idea what you are talking about";
		return outputLine;
	}
	 
}
