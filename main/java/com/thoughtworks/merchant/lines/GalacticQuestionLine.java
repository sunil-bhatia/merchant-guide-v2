package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;

//Example Galactic Question Line: "how much is pish tegj glob glob ?"
public class GalacticQuestionLine extends QuestionLine {
	
	private int arabicNumber;
	
	public GalacticQuestionLine() {
		super();
	}

	@Override
	protected void extractData(Matcher mcher) {
		galacticNumber = mcher.group(1);
	}
	
	@Override
	protected boolean isDataValid() {		
		return isGalacticNumValid();
	}
	
	@Override
	protected void calculateAnswer() {
		arabicNumber = galacticCalculator.galacticToArabic(galacticNumber);
	}
	
	@Override
	protected String formatValidAnswer() {
		return (galacticNumber + "is " + arabicNumber);
	}
}
