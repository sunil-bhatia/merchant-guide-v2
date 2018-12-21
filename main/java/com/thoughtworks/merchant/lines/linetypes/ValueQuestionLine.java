package com.thoughtworks.merchant.lines.linetypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.computations.CommodityCalculator;
import com.thoughtworks.merchant.lines.listmanagers.OutputLinesListManager;

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

		// Delegate to parse method for parsing the line
        parse();
        
        // Delegate to Commodity Calculator for calculating the total value for this commodity for the given quantity
        double totalValue = CommodityCalculator.calculateTotalValue(commodity, qtyGalactic);
        	
		//Delegate to formatter for formatting the output line
		String outputLine = formatAnswer(totalValue);
		
    	//Add the answer to the output lines
		OutputLinesListManager.addLine(outputLine);
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

	private String formatAnswer(double totalValue) {
		String outputLine = "";

		if (totalValue != 0) {
			outputLine = qtyGalactic + commodity + " is " + (long) totalValue + " Credits";
		}

		return outputLine;
	}
}
