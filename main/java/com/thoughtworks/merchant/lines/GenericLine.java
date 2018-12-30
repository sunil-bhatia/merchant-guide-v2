package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.CommodityMap;
import com.thoughtworks.merchant.interfaces.GalacticCalculator;
import com.thoughtworks.merchant.interfaces.Line;
import com.thoughtworks.merchant.interfaces.LogManager;

public abstract class GenericLine implements Line {

	protected String line;
	protected String regex;

	protected String commodity;
	protected String qtyGalactic;

	protected LogManager logManager = Factory.getLogManager();
	protected GalacticCalculator galacticCalculator = (GalacticCalculator) Factory.getObject("galacticCalculator");
	protected CommodityMap commodityMap = (CommodityMap) Factory.getObject("commodityMap");

	public GenericLine() {
	}

	public String process() {
		
		String outputLine = "";
		
		Matcher mcher = parse();
		extractData(mcher);

		if (isDataValid()) {
			if(isLineAssignmentType()){
				calculateAssignedData();
				addAssignedData();
			} else {
				calculateAnswer();
				outputLine = formatValidAnswer();
			}
		} else {
			outputLine = formatInvalidAnswer();
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
	
	protected abstract void extractData(Matcher mcher);
	
	protected boolean isDataValid() {
		
		boolean isGalacticNumValid;
		boolean isCommodityValid;
		
		if (qtyGalactic != null){
			isGalacticNumValid = validateGalacticNum();
		} else {
			isGalacticNumValid = true;
		}
		
		// Don't check commodity validity in value assignment line 
		if (isLineAssignmentType() == false && commodity != null){
			isCommodityValid = validateCommodity();
		} else {
			isCommodityValid = true;
		}
		
		boolean isValid;
		
		if (isGalacticNumValid && isCommodityValid){
			isValid = true;
		} else {
			isValid = false;
		}
		
		return isValid;
	}
	
	protected boolean validateGalacticNum() {
		boolean isGalacticNumValid;
		
		if (galacticCalculator.isValidGalacticNum(qtyGalactic)) {
			isGalacticNumValid = true;
		} else {
			isGalacticNumValid = false;
			logManager.addLog("Invalid Galactic Number in Input Line : " + line);
		}
		
		return isGalacticNumValid;
	}

	protected boolean validateCommodity() {
		boolean isCommodityValid;
		
		if (commodityMap.isValidCommodity(commodity)) {
			isCommodityValid = true;
		} else {
			isCommodityValid = false;
			logManager.addLog("Invalid Commodity in Input Line : " + line);
		}
		
		return isCommodityValid;
	}
	
	protected boolean isLineAssignmentType(){
		
		boolean isAssignmentType;
		
		line = line.trim();
		
		int indexLastChar = line.length()-1;
		char lastChar = line.charAt(indexLastChar);
		
		if (lastChar != '?'){
			isAssignmentType = true;
		} else {
			isAssignmentType = false;
		}
		
		return isAssignmentType;
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
	
	protected String formatInvalidAnswer() {
		return "I have no idea what you are talking about";
	}
	
	protected String formatValidAnswer() {
		// Dummy method - Will be implemented by derived classes, only if required
		return "";
	}
	
	public void setLine(String line) {
		this.line = line;
	}
	
	public void setRegex(String regex) {
		this.regex = regex;
	}
}
