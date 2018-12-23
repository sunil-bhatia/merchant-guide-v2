package com.thoughtworks.merchant.lines;

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

	protected boolean isGalacticNumValid;
	protected boolean isCommodityValid;

	protected String validOutputLine;
	protected String invalidOutputLine;
	protected ListManager outputLinesListManager = Factory.getOutputLinesListManagerObject();

	protected ListManager logsListManager = Factory.getLogsListManagerObject();
	
	protected boolean isAssignmentLine;

	public GenericLine(String line, String regex) {
		this.line = line;
		this.regex = regex;
	}

	@Override
	public void process() {

		parse();

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

	protected abstract void parse();

	// By default, it is assumed that line is valid
	protected boolean isLineValid() {
		return true;
	}

	protected void validateGalacticNum() {

		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();

		if (galacticNumerals.isValidGalacticNum(qtyGalactic)) {
			isGalacticNumValid = true;
		} else {
			isGalacticNumValid = false;
			logsListManager.addObject("Invalid Galactic Number in Input Line : " + line);
		}
	}

	protected void validateCommodity() {

		CommodityMap commodityMap = Factory.getCommodityMapObject();

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
		// Empty method
		// Will be implemented by derived classes, only if required
	}

	protected void addAssignedData() {
		// Empty method
		// Will be implemented by derived classes, only if required
	}

	protected void calculateAnswer() {
		// Empty method
		// Will be implemented by derived classes, only if required
	}

	protected void formatValidAnswer() {
		// Empty method
		// Will be implemented by derived classes, only if required
	}
}
