package com.thoughtworks.merchant.newscope;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.CommodityMap;
import com.thoughtworks.merchant.interfaces.Line;
import com.thoughtworks.merchant.interfaces.ListManager;

//Example Per Unit Question Line: "how many Credits is Silver per unit quantity ?"
public class PerUnitQuestionLine implements Line {

	private String line;	
	private String regex;
	
	String commodity;
	
	public PerUnitQuestionLine(String line, String regex) {
		this.line = line;
		this.regex = regex;
	}

	@Override
	public void process() {

		// Parse line and extract key field
        parse();
        
		// Validate line
		if (isLineValid()){
        
			// Get per unit value for this commodity
			CommodityMap commodityMap = Factory.getCommodityMapObject();
			double perUnitValue = commodityMap.getValuePerUnit(commodity);
        	
			//Format valid answer
			String outputLine = formatValidAnswer(perUnitValue);
		
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
	
	// Parse this line and extract one piece of information
    // commodity = "Silver"
	private void parse() {
		
		Pattern ptn = Pattern.compile(regex);
		
		Matcher mcher = ptn.matcher(line);
		mcher.matches();

		commodity = mcher.group(1).trim();
	}

	private String formatValidAnswer(double perUnitValue) {
		String outputLine = "";

		if (perUnitValue != 0) {
			outputLine = "Per unit quantity " + commodity + " is " + (long) perUnitValue + " Credits";
		}

		return outputLine;
	}
	
	// Validate line
	private boolean isLineValid(){

		boolean isValid;
		ListManager logsListManager = Factory.getLogsListManagerObject();
		
		// Check if Commodity is valid
		CommodityMap commodityMap = Factory.getCommodityMapObject();
		if (commodityMap.isValidCommodity(commodity)) {
			isValid = true;
		} else {
			isValid = false;
			logsListManager.addObject("Invalid Commodity in Input Line : " + line);
		}
		
		return isValid;
	}
	
	 private String formatInvalidAnswer() {
		String outputLine = "I have no idea what you are talking about";
		return outputLine;
	}
}
