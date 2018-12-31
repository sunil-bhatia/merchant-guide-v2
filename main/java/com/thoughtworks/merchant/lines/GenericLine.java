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

		if (galacticCalculator.isValidGalacticNum(qtyGalactic)) {
			isGalacticNumValid = true;
		} else {
			isGalacticNumValid = false;
			logManager.addLog("Invalid Galactic Number in Input Line : " + line);
		}

		return isGalacticNumValid;
	}
	
	protected boolean isCommodityValid() {

		boolean isCommodityValid;

		if (commodityMap.isValidCommodity(commodity)) {
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

	public void setLine(String line) {
		this.line = line;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}
}
