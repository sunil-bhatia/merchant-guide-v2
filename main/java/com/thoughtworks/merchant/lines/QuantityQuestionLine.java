package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.GalacticNumerals;

//Example Quantity Question Line: "how much is pish tegj glob glob ?"
public class QuantityQuestionLine extends GenericLine {
	
	protected int qtyArabic;
	
	public QuantityQuestionLine(String line, String regex) {
		super(line, regex);
	}

	// Parse this line and extract this piece of information
	// qtyGalactic = "pish tegj glob glob"
	protected void parse() {
		
		isAssignmentLine = false;
		
		Pattern ptn = Pattern.compile(regex);

		Matcher mcher = ptn.matcher(line);
		mcher.matches();

		qtyGalactic = mcher.group(1);
	}
	
	@Override
	protected boolean isLineValid() {
		validateGalacticNum();
		return isGalacticNumValid;
	}
	
	protected void calculateAnswer() {
		// Calculate quantity arabic
		GalacticNumerals galacticNumerals = Factory.getGalacticNumeralsObject();
		qtyArabic = galacticNumerals.galacticToArabic(qtyGalactic);
	}
	
	protected void formatValidAnswer() {
		if (qtyArabic != 0) {
			validOutputLine = qtyGalactic + "is " + qtyArabic;
		}
	}
}
