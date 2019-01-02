package com.thoughtworks.merchant.lines;

public abstract class QuestionLine extends GenericLine {

	public QuestionLine() {
		super();
	}

	@Override
	protected String processValidData() {

		String outputLine = "";

		calculateAnswer();
		outputLine = formatValidAnswer();

		return outputLine;
	}

	protected abstract void calculateAnswer();

	protected abstract String formatValidAnswer();
}
