package com.thoughtworks.merchant.lines;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.MerchantsNotesProcessor;
import com.thoughtworks.merchant.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.factory.Factory;


public class ValueQuestionLineValidationTest {
	
    @Before
    public void setupConfig() {
    	
    	// Configure properties
    	String[] args = {"config"};
		ConfigPropertiesManager.configureProperties(args);
		
    }
	
	@Test
	public void testIncorrectGalacticSymbolInValueQuestionReturnsNoIdeaLine() {
		List<String> inputLines = new ArrayList<String>();
		inputLines.add("glob is I");
		inputLines.add("prok is V");
		inputLines.add("how many Credits is mnop prok Silver ?");

		//Expected behavior assumption: Output will have this line "I have no idea what you are talking about"
		List<String> expectedOutputLines = new ArrayList<String>();
		expectedOutputLines.add("I have no idea what you are talking about");

		// Process input lines
		MerchantsNotesProcessor merchantsNotesProcessor = Factory.createMerchantsNotesProcessor();
		List<String> generatedOutputLines = merchantsNotesProcessor.processLines(inputLines);

		assertEquals(expectedOutputLines, generatedOutputLines);
	}
	

	@Test
	public void testIncorrectCommodityInValueQuestionReturnsNoIdeaLine() {
		List<String> inputLines = new ArrayList<String>();
		inputLines.add("glob is I");
		inputLines.add("glob glob Silver is 34 Credits");
		inputLines.add("how many Credits is glob glob Copper ?");

		//Expected behavior assumption: Output will have this line "I have no idea what you are talking about"
		List<String> expectedOutputLines = new ArrayList<String>();
		expectedOutputLines.add("I have no idea what you are talking about");

		// Process input lines
		MerchantsNotesProcessor merchantsNotesProcessor = Factory.createMerchantsNotesProcessor();
		List<String> generatedOutputLines = merchantsNotesProcessor.processLines(inputLines);

		assertEquals(expectedOutputLines, generatedOutputLines);
	}
}
