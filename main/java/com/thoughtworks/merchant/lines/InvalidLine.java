package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;

//Example Invalid Line: "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"
public class InvalidLine extends GenericLine {

	public InvalidLine() {
		super();
	}

	public String process() {
		formatInvalidAnswer();
		return invalidOutputLine;
	}

	@Override
	protected void extractData(Matcher mcher) {
		// No data to be extracted 
	}
}
