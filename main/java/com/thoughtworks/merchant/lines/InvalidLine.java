package com.thoughtworks.merchant.lines;

import com.thoughtworks.merchant.iomanagers.OutputLinesManager;

//Example Invalid Line: "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"
public class InvalidLine implements Line {
	
	public InvalidLine() {
	}

	@Override
	public void process() {

		// Delegate to formatter for formatting the output line
		String outputLine = formatAnswer();

		// Add the answer to the output lines
		OutputLinesManager.addLine(outputLine);
	}
	
	 String formatAnswer() {
		String outputLine = "I have no idea what you are talking about";
		return outputLine;
	}
}
