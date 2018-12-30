package com.thoughtworks.merchant.lines;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.MerchantsNotesProcessor;
import com.thoughtworks.merchant.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.factory.Factory;


public class ValueAssignmentLineValidationTest {
	
    @Before
    public void setupConfig() {
    	
    	// Configure properties
    	String[] args = {"config"};
		ConfigPropertiesManager.configureProperties(args);
		
    }
	
	@Test
	public void testIncorrectGalacticSymbolInValueAssignmentReturnsNoIdeaLine() {
		List<String> inputLines = new ArrayList<String>();
		inputLines.add("glob is I");
		inputLines.add("prok is V");
		inputLines.add("cdef Silver is 34 Credits");

		//Expected behavior assumption: Output will have this line "I have no idea what you are talking about"
		List<String> expectedOutputLines = new ArrayList<String>();
		expectedOutputLines.add("I have no idea what you are talking about");

		// Process input lines
		MerchantsNotesProcessor merchantsNotesProcessor = Factory.createMerchantsNotesProcessor();
		List<String> generatedOutputLines = merchantsNotesProcessor.processLines(inputLines);

		assertEquals(expectedOutputLines, generatedOutputLines);
	}
	
	@Test
	public void testIncorrectRomanNumInValueAssignmentReturnsNoIdeaLine() {
		List<String> inputLines = new ArrayList<String>();
		inputLines.add("glob is I");
		inputLines.add("glob glob glob glob Silver is 34 Credits");

		//Expected behavior assumption: Output will have this line "I have no idea what you are talking about"
		List<String> expectedOutputLines = new ArrayList<String>();
		expectedOutputLines.add("I have no idea what you are talking about");

		// Process input lines
		MerchantsNotesProcessor merchantsNotesProcessor = Factory.createMerchantsNotesProcessor();
		List<String> generatedOutputLines = merchantsNotesProcessor.processLines(inputLines);

		assertEquals(expectedOutputLines, generatedOutputLines);
	}
	
	@Test
	public void testDuplicateCommodityInValueAssignmentOverwrites() {
		List<String> inputLines = new ArrayList<String>();
		inputLines.add("glob is I");
		inputLines.add("glob Silver is 10 Credits");
		inputLines.add("glob glob Silver is 2 Credits");
		inputLines.add("how many Credits is glob glob glob Silver ?");

		//Expected Behaviour - If the map previously contained a mapping for the key, the old value is replaced.
		List<String> expectedOutputLines = new ArrayList<String>();
		expectedOutputLines.add("glob glob glob Silver is 3 Credits");

		// Process input lines
		MerchantsNotesProcessor merchantsNotesProcessor = Factory.createMerchantsNotesProcessor();
		List<String> generatedOutputLines = merchantsNotesProcessor.processLines(inputLines);

		assertEquals(expectedOutputLines, generatedOutputLines);
	}
}
