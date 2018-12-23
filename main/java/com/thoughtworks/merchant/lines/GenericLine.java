package com.thoughtworks.merchant.lines;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.CommodityMap;
import com.thoughtworks.merchant.interfaces.GalacticNumerals;
import com.thoughtworks.merchant.interfaces.Line;
import com.thoughtworks.merchant.interfaces.ListManager;

public abstract class GenericLine implements Line {

	private String line;
	private String regex;

	String commodity;
	String qtyGalactic;

	String outputLine;

	public GenericLine(String line, String regex) {
		this.line = line;
		this.regex = regex;
	}

	@Override
	public void process() {

		parse();

		if (isLineValid()) {
			calculateAnswer();
			outputLine = formatValidAnswer();
		} else {
			outputLine = formatInvalidAnswer();
		}

		addOutputLine();
	}

	public abstract void parse();

	// Validate line
	public boolean isLineValid() {

		boolean isValid;

		// Two conditions have to be met, for this to be true
		// 1. Galactic number should be valid
		// 2. Commodity should be valid

		boolean isValidGalacticNum;
		boolean isValidCommodity;

		// Check if galactic number is valid
		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
		ListManager logsListManager = Factory.getLogsListManagerObject();

		if (qtyGalactic == null || galacticNumerals.isValidGalacticNum(qtyGalactic)) {
			isValidGalacticNum = true;
		} else {
			isValidGalacticNum = false;
			logsListManager.addObject("Invalid Galactic Number in Input Line : " + line);
		}

		// Check if Commodity is valid
		CommodityMap commodityMap = Factory.getCommodityMapObject();
		if (commodity == null || commodityMap.isValidCommodity(commodity)) {
			isValidCommodity = true;
		} else {
			isValidCommodity = false;
			logsListManager.addObject("Invalid Commodity in Input Line : " + line);
		}

		if (isValidGalacticNum && isValidCommodity) {
			isValid = true;
		} else {
			isValid = false;
		}

		return isValid;
	}
	
	public abstract void calculateAnswer();

	public abstract String formatValidAnswer();

	public String formatInvalidAnswer() {
		String outputLine = "I have no idea what you are talking about";
		return outputLine;
	}
	
	// Add the answer to the output lines
	public void addOutputLine() {
		ListManager outputLinesListManager = Factory.getOutputLinesListManagerObject();
		outputLinesListManager.addObject(outputLine);
	}
}
