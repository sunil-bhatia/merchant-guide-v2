package com.thoughtworks.merchant.lines.newlinetype;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.computations.CommodityMapManager;
import com.thoughtworks.merchant.lines.Line;
import com.thoughtworks.merchant.lines.listmanagers.OutputLinesListManager;

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

		// Delegate to parse method for parsing the line
        parse();
        
        // Delegate to Commodity Map Manager for getting the per unit value for this commodity
        double perUnitValue = CommodityMapManager.getValuePerUnit(commodity);
        	
		//Delegate to formatter for formatting the output line
		String outputLine = formatAnswer(perUnitValue);
		
    	//Add the answer to the output lines
		OutputLinesListManager.addLine(outputLine);
	}
	
	// Parse this line and extract one piece of information
    // commodity = "Silver"
	private void parse() {
		
		Pattern ptn = Pattern.compile(regex);
		
		Matcher mcher = ptn.matcher(line);
		mcher.matches();

		commodity = mcher.group(1).trim();
	}

	private String formatAnswer(double perUnitValue) {
		String outputLine = "";

		if (perUnitValue != 0) {
			outputLine = "Per unit quantity " + commodity + " is " + (long) perUnitValue + " Credits";
		}

		return outputLine;
	}
}
