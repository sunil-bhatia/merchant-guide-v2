package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;

//Example Quantity Question Line: "how much is pish tegj glob glob ?"
public class QuantityQuestionLine extends GenericLine {
	
	private int qtyArabic;
	
	public QuantityQuestionLine() {
		super();
	}

	// Example: qtyGalactic = "pish tegj glob glob"
	protected void extractData(Matcher mcher) {
		qtyGalactic = mcher.group(1);
	}
	
	protected void calculateAnswer() {
		qtyArabic = galacticNumerals.galacticToArabic(qtyGalactic);
	}
	
	protected void formatValidAnswer() {
		validOutputLine = qtyGalactic + "is " + qtyArabic;
	}
}
