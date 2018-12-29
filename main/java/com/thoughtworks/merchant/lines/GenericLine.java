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
	protected GalacticNumerals galacticNumerals = (GalacticNumerals) Factory.getObject("galacticNumerals");
	protected CommodityMap commodityMap = (CommodityMap) Factory.getObject("commodityMap");
	protected CommodityCalculator commodityCalculator = (CommodityCalculator) Factory.getObject("commodityCalculator");
	protected AliasMap aliasMap = (AliasMap) Factory.getObject("aliasMap");

	public GenericLine(String line, String regex) {
		this.line = line;
		this.regex = regex;
	}

	public void process() {
		
		parse();
		determineIsAssignmentLine();
		extractData();
		validateData();

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
	
	protected void determineIsAssignmentLine(){
		int indexLastChar = line.length()-1;
		char lastChar = line.charAt(indexLastChar);
		
		if (lastChar != '?'){
			isAssignmentLine = true;
		} else {
			isAssignmentLine = false;
		}
	}

	protected abstract void extractData();
	
	protected void validateData() {
		
		if (qtyGalactic != null){
			validateGalacticNum();
		} else {
			isGalacticNumValid = true;
		}
		
		// Don't check commodity validity in value assignment line 
		if (isAssignmentLine == false && commodity != null){
			validateCommodity();
		} else {
			isCommodityValid = true;
		}
	}
	
	protected boolean isLineValid() {
		boolean isValid;
		
		if (isGalacticNumValid && isCommodityValid){
			isValid = true;
		} else {
			isValid = false;
		}
		
		return isValid;
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
