package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.interfaces.CommodityCalculator;
import com.thoughtworks.merchant.interfaces.CommodityMap;
import com.thoughtworks.merchant.interfaces.GalacticCalculator;
import com.thoughtworks.merchant.interfaces.Line;
import com.thoughtworks.merchant.interfaces.LogManager;

public abstract class GenericLine implements Line {

	protected String line;
	protected String regex;

	protected String commodity;
	protected String galacticNumber;
	
	protected LogManager logManager;
	protected GalacticCalculator galacticCalculator;
	protected CommodityMap commodityMap;
	protected CommodityCalculator commodityCalculator;

	public GenericLine() {
	}

	@Override
	public String process() {

		String outputLine = "";

		Matcher mcher = parse();
		extractData(mcher);

		if (isDataValid()) {
			outputLine = processValidData();
		} else {
			outputLine = processInvalidData();
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

	protected abstract boolean isDataValid();

	protected boolean isGalacticNumValid() {
		boolean isGalacticNumValid;

		if (galacticCalculator.isGalacticNumValid(galacticNumber)) {
			isGalacticNumValid = true;
		} else {
			isGalacticNumValid = false;
			logManager.addLog("Invalid Galactic Number in Input Line : " + line);
		}

		return isGalacticNumValid;
	}
	
	protected boolean isCommodityValid() {

		boolean isCommodityValid;

		if (commodityMap.isCommodityValid(commodity)) {
			isCommodityValid = true;
		} else {
			isCommodityValid = false;
			logManager.addLog("Invalid Commodity in Input Line : " + line);
		}

		return isCommodityValid;
	}

	protected abstract String processValidData();

	protected String processInvalidData() {
		return "I have no idea what you are talking about";
	}

	@Override
	public void setLine(String line) {
		this.line = line;
	}

	@Override
	public void setRegex(String regex) {
		this.regex = regex;
	}

	@Override
	public String toString() {
		return "GenericLine [line=" + line + ", regex=" + regex + ", commodity=" + commodity + ", galacticNumber="
				+ galacticNumber + "]";
	}
	
	public void setLogManager(Object logManager) {
		this.logManager = (LogManager) logManager;
	}

	public void setGalacticCalculator(Object galacticCalculator) {
		this.galacticCalculator = (GalacticCalculator) galacticCalculator;
	}

	public void setCommodityMap(Object commodityMap) {
		this.commodityMap = (CommodityMap) commodityMap;
	}
	
	public void setCommodityCalculator(Object commodityCalculator) {
		this.commodityCalculator = (CommodityCalculator) commodityCalculator;
	}
}
