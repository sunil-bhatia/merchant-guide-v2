package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.AliasMap;
import com.thoughtworks.merchant.interfaces.CommodityCalculator;
import com.thoughtworks.merchant.interfaces.CommodityMap;
import com.thoughtworks.merchant.interfaces.GalacticNumerals;
import com.thoughtworks.merchant.interfaces.Line;
import com.thoughtworks.merchant.interfaces.ListManager;

public abstract class GenericLine implements Line {

	protected String line;
	protected String regex;
	protected Pattern ptn;
	protected Matcher mcher;

	protected String commodity;
	protected String qtyGalactic;
	protected int qtyArabic;
	protected int value;
	protected double valuePerUnit;
	
	protected boolean isAssignmentLine;

	protected boolean isGalacticNumValid;
	protected boolean isCommodityValid;

	protected String validOutputLine;
	protected String invalidOutputLine;

	protected ListManager logsListManager = Factory.getLogsListManagerObject();
	protected ListManager outputLinesListManager = Factory.getOutputLinesListManagerObject();
	protected GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
	protected CommodityMap commodityMap = Factory.getCommodityMapObject();
	protected CommodityCalculator commodityCalculator = Factory.getCommodityCalculatorObject();
	protected AliasMap aliasMap = Factory.getAliasMapObject();

	public GenericLine(String line, String regex) {
		this.line = line;
		this.regex = regex;
	}

	public void process() {
		
		parse();
		extractData();
		determineLineType();

		if (isLineValid()) {
			if(isAssignmentLine){
				calculateAssignedData();
				addAssignedData();
			} else {
				calculateAnswer();
				formatValidAnswer();
				addValidOutputLine();
			}
		} else {
			formatInvalidAnswer();
			addInvalidOutputLine();
		}
	}
	
	protected void parse() {
		ptn = Pattern.compile(regex);
		mcher = ptn.matcher(line);
		mcher.matches();
	}

	protected abstract void extractData();
	
	protected abstract void determineLineType();

	// By default, it is assumed that line is valid
	protected boolean isLineValid() {
		return true;
	}

	protected void validateGalacticNum() {
		if (galacticNumerals.isValidGalacticNum(qtyGalactic)) {
			isGalacticNumValid = true;
		} else {
			isGalacticNumValid = false;
			logsListManager.addObject("Invalid Galactic Number in Input Line : " + line);
		}
	}

	protected void validateCommodity() {
		if (commodityMap.isValidCommodity(commodity)) {
			isCommodityValid = true;
		} else {
			isCommodityValid = false;
			logsListManager.addObject("Invalid Commodity in Input Line : " + line);
		}
	}
	
	protected void formatInvalidAnswer() {
		invalidOutputLine = "I have no idea what you are talking about";
	}

	protected void addValidOutputLine() {
		outputLinesListManager.addObject(validOutputLine);
	}

	protected void addInvalidOutputLine() {
		outputLinesListManager.addObject(invalidOutputLine);
	}

	protected void calculateAssignedData() {
		// Empty method - Will be implemented by derived classes, only if required
	}

	protected void addAssignedData() {
		// Empty method - Will be implemented by derived classes, only if required
	}

	protected void calculateAnswer() {
		// Empty method - Will be implemented by derived classes, only if required
	}

	protected void formatValidAnswer() {
		// Empty method - Will be implemented by derived classes, only if required
	}
}
