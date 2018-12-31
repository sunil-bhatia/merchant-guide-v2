package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;

//Example Invalid Line: "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"
public class InvalidLine extends GenericLine {

	public InvalidLine() {
		super();
	}

	public String process() {
		return processInvalidData();
	}

	@Override
	protected void extractData(Matcher mcher) {
		// No data to be extracted 
	}

	@Override
	protected String processValidData() {
		// No data to be processed 
		return null;
	}

	@Override
	protected boolean isDataValid() {
		// No data to be validated
		return false;
	}
}
