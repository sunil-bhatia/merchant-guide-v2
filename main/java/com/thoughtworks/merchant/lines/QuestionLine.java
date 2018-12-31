package com.thoughtworks.merchant.lines;

public abstract class QuestionLine extends GenericLine {

	public QuestionLine() {
		super();
	}

	protected String processValidData() {

		String outputLine = "";

		calculateAnswer();
		outputLine = formatValidAnswer();

		return outputLine;
	}
	
	protected boolean isDataValid() {
		
		boolean isDataValid;
		
		if (isGalacticNumValid() && isCommodityValid()){
			isDataValid = true;
		} else {
			isDataValid = false;
		}
		
		return isDataValid;
	}
	
	protected boolean isCommodityValid() {
		
		boolean isCommodityValid;
		
		if (commodity != null){
			if (commodityMap.isValidCommodity(commodity)) {
				isCommodityValid = true;
			} else {
				isCommodityValid = false;
				logManager.addLog("Invalid Commodity in Input Line : " + line);
			}
		} else {
			isCommodityValid = true;
		}
		
		return isCommodityValid;
	}
	
	protected abstract void calculateAnswer();
	
	protected abstract String formatValidAnswer();
}
