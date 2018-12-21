package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.computations.GalacticNumerals;
import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.lines.listmanagers.OutputLinesListManager;

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

		// Delegate to parse method for parsing the line
		// Example: qtyGalactic = "pish tegj glob glob"
        parse();

    	// Delegate to Galactic Numerals for calculating the answer
		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
        int qtyArabic = galacticNumerals.galacticToArabic(qtyGalactic);
        
		//Delegate to formatter for formatting the output line
		String outputLine = formatAnswer(qtyArabic);
		
    	//Add the answer to the output lines
		OutputLinesListManager.addLine(outputLine);
	}
	
	// Parse this line and extract this piece of information
	// qtyGalactic = "pish tegj glob glob"
	private void parse() {
		
		Pattern ptn = Pattern.compile(regex);

		Matcher mcher = ptn.matcher(line);
		mcher.matches();

		qtyGalactic = mcher.group(1);
	}
	
	private String formatAnswer(int qtyArabic) {
		String outputLine = "";
		if (qtyArabic != 0) {
			outputLine = qtyGalactic + "is " + qtyArabic;
		}
		return outputLine;
	}
}
