package com.thoughtworks.merchant.lines;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.lines.listmanagers.ListManager;

//Example Invalid Line: "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"
public class InvalidLine implements Line {
	
	public InvalidLine() {
	}

	@Override
	public void process() {

		// Delegate to formatter for formatting the output line
		String outputLine = formatAnswer();

		// Add the answer to the output lines
		ListManager outputLinesListManager = Factory.getOutputLinesListManagerObject();
		outputLinesListManager.addObject(outputLine);
	}
	
	 String formatAnswer() {
		String outputLine = "I have no idea what you are talking about";
		return outputLine;
	}
}
