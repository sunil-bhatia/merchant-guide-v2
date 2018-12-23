package com.thoughtworks.merchant.lines;

//Example Invalid Line: "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"
public class InvalidLine extends GenericLine {

	public InvalidLine(String line, String regex) {
		super(line, regex);
	}

	public void process() {
		formatInvalidAnswer();
		addInvalidOutputLine();
	}

	@Override
	protected void extractData() {
		// No data to be extracted 
	}
}
