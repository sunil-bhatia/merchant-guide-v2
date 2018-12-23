package com.thoughtworks.merchant.lines;

//Example Value Question Line: "how many Credits is glob prok Silver ?"
public class ValueQuestionLine extends GenericLine {
	
	protected double totalValue;
	
	public ValueQuestionLine(String line, String regex) {
		super(line, regex);
	}

	// Example: qtyGalactic = "glob prok"
    // 			commodity = "Silver"
	protected void extractData() {
		qtyGalactic = mcher.group(1);
		commodity = mcher.group(2).trim();
	}
	
	protected void determineLineType() {
		isAssignmentLine = false;
	}
	
	protected boolean isLineValid() {
		boolean isValid;
		
		validateGalacticNum();
		validateCommodity();
		
		if (isGalacticNumValid && isCommodityValid){
			isValid = true;
		} else {
			isValid = false;
		}
		
		return isValid;
	}
	
	protected void calculateAnswer() {
		totalValue = commodityCalculator.calculateTotalValue(commodity, qtyGalactic);
	}

	protected void formatValidAnswer() {
		validOutputLine = qtyGalactic + commodity + " is " + (long) totalValue + " Credits";
	}	 
}
