package com.thoughtworks.merchant.lines;

//Example Quantity Question Line: "how much is pish tegj glob glob ?"
public class QuantityQuestionLine extends GenericLine {
	
	public QuantityQuestionLine(String line, String regex) {
		super(line, regex);
	}

	// Example: qtyGalactic = "pish tegj glob glob"
	protected void extractData() {
		qtyGalactic = mcher.group(1);
	}
	
	protected void calculateAnswer() {
		qtyArabic = galacticNumerals.galacticToArabic(qtyGalactic);
	}
	
	protected void formatValidAnswer() {
		validOutputLine = qtyGalactic + "is " + qtyArabic;
	}
}
