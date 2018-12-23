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
		// TODO Auto-generated method stub

	}

	@Override
	protected void determineLineType() {
		// TODO Auto-generated method stub

	}

}
