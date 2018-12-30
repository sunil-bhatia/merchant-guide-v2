package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.CommodityMap;
import com.thoughtworks.merchant.interfaces.GalacticNumerals;
import com.thoughtworks.merchant.interfaces.Line;
import com.thoughtworks.merchant.interfaces.ListManager;

public abstract class GenericLine implements Line {

	protected String line;
	protected String regex;

	protected String commodity;
	protected String qtyGalactic;
	
	protected boolean isCommodityValid;
	protected boolean isGalacticNumValid;

	protected String validOutputLine;
	protected String invalidOutputLine;
	protected String outputLine;

	protected ListManager logsListManager = Factory.getLogsListManagerObject();
	protected GalacticNumerals galacticNumerals = (GalacticNumerals) Factory.getObject("galacticNumerals");
	protected CommodityMap commodityMap = (CommodityMap) Factory.getObject("commodityMap");

	public GenericLine() {
	}

	public String process() {
		
		Matcher mcher = parse();
		extractData(mcher);
		validateData();

		if (isLineValid()) {
			if(isLineAssignmentType()){
				calculateAssignedData();
				addAssignedData();
			} else {
				calculateAnswer();
				formatValidAnswer();
			}
		} else {
			formatInvalidAnswer();
		}
		
		if (validOutputLine != null){
			outputLine = validOutputLine;
		} else if (invalidOutputLine != null){
			outputLine = invalidOutputLine;
		} else {
			outputLine = "";
		}
		
		return outputLine;
	}
	
	protected Matcher parse() {
		
		Matcher mcher;
		
		Pattern ptn = Pattern.compile(regex);
		mcher = ptn.matcher(line);
		mcher.matches();
		
		return mcher;
	}
	
	protected boolean isLineAssignmentType(){
		
		boolean isAssignmentType;
		
		int indexLastChar = line.length()-1;
		char lastChar = line.charAt(indexLastChar);
		
		if (lastChar != '?'){
			isAssignmentType = true;
		} else {
			isAssignmentType = false;
		}
		
		return isAssignmentType;
	}

	protected abstract void extractData(Matcher mcher);
	
	protected void validateData() {
		
		if (qtyGalactic != null){
			validateGalacticNum();
		} else {
			isGalacticNumValid = true;
		}
		
		// Don't check commodity validity in value assignment line 
		if (isLineAssignmentType() == false && commodity != null){
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
	
	public void setLine(String line) {
		this.line = line;
	}
	
	public void setRegex(String regex) {
		this.regex = regex;
	}
}
