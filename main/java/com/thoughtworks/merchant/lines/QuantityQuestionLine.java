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
	
	protected void determineLineType() {
		isAssignmentLine = false;
	}
	
	@Override
	protected boolean isLineValid() {
		validateGalacticNum();
		return isGalacticNumValid;
	}
	
	protected void calculateAnswer() {
		qtyArabic = galacticNumerals.galacticToArabic(qtyGalactic);
	}
	
	protected void formatValidAnswer() {
		if (qtyArabic != 0) {
			validOutputLine = qtyGalactic + "is " + qtyArabic;
		}
	}
}
