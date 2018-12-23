package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.CommodityCalculator;
import com.thoughtworks.merchant.interfaces.CommodityMap;
import com.thoughtworks.merchant.interfaces.GalacticNumerals;
import com.thoughtworks.merchant.interfaces.Line;
import com.thoughtworks.merchant.interfaces.ListManager;

//Example Value Assignment Line: "glob glob Silver is 34 Credits"
public class ValueAssignmentLine implements Line {

	private String line;
	private String regex;

	private String commodity;
	private String qtyGalactic;
	private int value;
	
	public ValueAssignmentLine(String line, String regex) {
		this.line = line;
		this.regex = regex;
	}

	@Override
	public void process() {

		// Parse line and extract key fields
		parse();
		
		// Validate line
		if (isLineValid()){

			// Calculate value per unit quantity for the commodity
			CommodityCalculator commodityCalculator = Factory.getCommodityCalculatorObject();
			double valuePerUnit = commodityCalculator.calculateValuePerUnit(value, qtyGalactic);

			// Add commodity and value per unit to the commodity map
			CommodityMap commodityMap = Factory.getCommodityMapObject();
			commodityMap.addValuePerUnit(commodity, valuePerUnit);
		
		} else {
			
			String outputLine = formatInvalidAnswer();

			// Add the answer to the output lines
			ListManager outputLinesListManager = Factory.getOutputLinesListManagerObject();
			outputLinesListManager.addObject(outputLine);
		}
	}

	// Parse this line and extract the three pieces of information
	// qtyGalactic = "glob glob"
	// commodity = "Silver"
	// value = 34
	private void parse() {

		Pattern ptn = Pattern.compile(regex);

		Matcher mcher = ptn.matcher(line);
		mcher.matches();

		qtyGalactic = mcher.group(1);
		commodity = mcher.group(2);
		value = Integer.parseInt(mcher.group(3).trim());
	}
	
	// Check if quantity galactic is a valid galactic number
	private boolean isLineValid(){

		boolean isValid;
		
		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
		ListManager logsListManager = Factory.getLogsListManagerObject();

		if (galacticNumerals.isValidGalacticNum(qtyGalactic)) {
			isValid = true;
		} else {
			isValid = false;
			logsListManager.addObject("Invalid Galactic Number in Input Line : " + line);
		}
		
		return isValid;
	}
	
	 private String formatInvalidAnswer() {
		String outputLine = "I have no idea what you are talking about";
		return outputLine;
	}
}
