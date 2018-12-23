package com.thoughtworks.merchant.lines;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.GalacticNumerals;
import com.thoughtworks.merchant.interfaces.Line;
import com.thoughtworks.merchant.interfaces.ListManager;

public abstract class GenericAssignmentLine implements Line {

	protected String line;
	protected String regex;

	protected String commodity;
	protected String qtyGalactic;
	
	protected boolean isGalacticNumValid;
	protected boolean isCommodityValid;

	protected String outputLine;

	public GenericAssignmentLine(String line, String regex) {
		this.line = line;
		this.regex = regex;
	}

	@Override
	public void process() {

		parse();

		if (isLineValid()) {
			calculateAssignedData();
			addAssignedData();
		} else {
			formatInvalidAnswer();
			addOutputLine();
		}
	}

	protected abstract void parse();
	
	protected void calculateAssignedData(){
		// Empty method
		// Will be implemented by derived classes, only if required
	}
	
	protected abstract void addAssignedData();
	
	// By default, it is assumed that line is valid
	protected boolean isLineValid() {
		return true;
	}
	
	protected void validateGalacticNum(){

		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
		ListManager logsListManager = Factory.getLogsListManagerObject();

		if (galacticNumerals.isValidGalacticNum(qtyGalactic)) {
			isGalacticNumValid = true;
		} else {
			isGalacticNumValid = false;
			logsListManager.addObject("Invalid Galactic Number in Input Line : " + line);
		}
	}
	
	protected void formatInvalidAnswer() {
		outputLine = "I have no idea what you are talking about";
	}
	
	protected void addOutputLine() {
		ListManager outputLinesListManager = Factory.getOutputLinesListManagerObject();
		outputLinesListManager.addObject(outputLine);
	}
}
