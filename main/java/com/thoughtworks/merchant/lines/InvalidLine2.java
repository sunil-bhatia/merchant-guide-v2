package com.thoughtworks.merchant.lines;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.Line;
import com.thoughtworks.merchant.interfaces.ListManager;

//Example Invalid Line: "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"
public class InvalidLine2 implements Line {
	
	public InvalidLine2() {
	}

	@Override
	public void process() {

		String outputLine = formatAnswer();

		// Add the answer to the output lines
		ListManager outputLinesListManager = Factory.getOutputLinesListManagerObject();
		outputLinesListManager.addObject(outputLine);
	}
	
	 private String formatAnswer() {
		String outputLine = "I have no idea what you are talking about";
		return outputLine;
	}
}
