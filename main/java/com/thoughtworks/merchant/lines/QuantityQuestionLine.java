package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.GalacticNumerals;
import com.thoughtworks.merchant.interfaces.Line;
import com.thoughtworks.merchant.interfaces.ListManager;

//Example Quantity Question Line: "how much is pish tegj glob glob ?"
public class QuantityQuestionLine implements Line {

	private String line;	
	private String regex;
	
	String qtyGalactic;
	
	public QuantityQuestionLine(String line, String regex) {
		this.line = line;
		this.regex = regex;
	}

	@Override
	public void process() {

		// Parse line and extract key field
        parse();
        
		// Validate line
		if (isLineValid()){

			// Calculate quantity arabic
			GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
			int qtyArabic = galacticNumerals.galacticToArabic(qtyGalactic);
        
			//Format valid answer
			String outputLine = formatValidAnswer(qtyArabic);
		
			//Add the answer to the output lines
			ListManager outputLinesListManager = Factory.getOutputLinesListManagerObject();
			outputLinesListManager.addObject(outputLine);
		
		} else {
			
			String outputLine = formatInvalidAnswer();

			// Add the answer to the output lines
			ListManager outputLinesListManager = Factory.getOutputLinesListManagerObject();
			outputLinesListManager.addObject(outputLine);
		}
	}
	
	// Parse this line and extract this piece of information
	// qtyGalactic = "pish tegj glob glob"
	private void parse() {
		
		Pattern ptn = Pattern.compile(regex);

		Matcher mcher = ptn.matcher(line);
		mcher.matches();

		qtyGalactic = mcher.group(1);
	}
	
	private String formatValidAnswer(int qtyArabic) {
		String outputLine = "";
		if (qtyArabic != 0) {
			outputLine = qtyGalactic + "is " + qtyArabic;
		}
		return outputLine;
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
