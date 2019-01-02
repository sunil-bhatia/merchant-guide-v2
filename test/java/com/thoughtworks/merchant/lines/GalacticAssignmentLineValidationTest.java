package com.thoughtworks.merchant.lines;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.MerchantsNotesProcessorImpl;
import com.thoughtworks.merchant.factory.FileConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.ConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.GenericFactory;

public class GalacticAssignmentLineValidationTest {
	
	private GenericFactory factory;
	
    @Before
    public void setupConfig() {
    	
    	// Configure properties
    	String[] args = {"config"};
		ConfigPropertiesManager configPropertiesManager = new FileConfigPropertiesManager();
		configPropertiesManager.configureProperties(args);
		factory = configPropertiesManager.getFactoryObject();
		
    }
	
	@Test
	public void testDuplicateGalacticSymbolInGalacticAssignmentOverwrites() {
		List<String> inputLines = new ArrayList<String>();
		inputLines.add("glob is C");
		inputLines.add("glob is I");
		inputLines.add("how much is glob ?");

		//Expected Behaviour - If the map previously contained a mapping for the key, the old value is replaced.
		List<String> expectedOutputLines = new ArrayList<String>();
		expectedOutputLines.add("glob is 1");
		
		// Process input lines
		MerchantsNotesProcessorImpl merchantsNotesProcessor = (MerchantsNotesProcessorImpl) factory.getObject("MerchantsNotesProcessor");
		List<String> generatedOutputLines = merchantsNotesProcessor.processInputLines(inputLines);

		assertEquals(expectedOutputLines, generatedOutputLines);
	}
	
	@Test
	public void testIncorrectRomanSymbolInGalacticAssignmentReturnsNoIdeaLine() {
		List<String> inputLines = new ArrayList<String>();
		inputLines.add("glob is T");

		//Expected behavior assumption: Output will have this line "I have no idea what you are talking about"
		List<String> expectedOutputLines = new ArrayList<String>();
		expectedOutputLines.add("I have no idea what you are talking about");

		// Process input lines
		MerchantsNotesProcessorImpl merchantsNotesProcessor = (MerchantsNotesProcessorImpl) factory.getObject("MerchantsNotesProcessor");
		List<String> generatedOutputLines = merchantsNotesProcessor.processInputLines(inputLines);

		assertEquals(expectedOutputLines, generatedOutputLines);
	}
	
	@Test
	public void testTwoCharRomanSymbolInGalacticAssignmentReturnsNoIdeaLine() {
		List<String> inputLines = new ArrayList<String>();
		inputLines.add("glob is II");

		//Expected behavior assumption: Output will have this line "I have no idea what you are talking about"
		List<String> expectedOutputLines = new ArrayList<String>();
		expectedOutputLines.add("I have no idea what you are talking about");

		// Process input lines
		MerchantsNotesProcessorImpl merchantsNotesProcessor = (MerchantsNotesProcessorImpl) factory.getObject("MerchantsNotesProcessor");
		List<String> generatedOutputLines = merchantsNotesProcessor.processInputLines(inputLines);
	
		assertEquals(expectedOutputLines, generatedOutputLines);
	}
	
	@Test
	public void testIsMissingInGalacticAssignmentReturnsNoIdeaLine() {
		List<String> inputLines = new ArrayList<String>();
		inputLines.add("glob in I");

		//Expected behavior assumption: Output will have this line "I have no idea what you are talking about"
		List<String> expectedOutputLines = new ArrayList<String>();
		expectedOutputLines.add("I have no idea what you are talking about");

		// Process input lines
		MerchantsNotesProcessorImpl merchantsNotesProcessor = (MerchantsNotesProcessorImpl) factory.getObject("MerchantsNotesProcessor");
		List<String> generatedOutputLines = merchantsNotesProcessor.processInputLines(inputLines);

		assertEquals(expectedOutputLines, generatedOutputLines);
	}
	
	@Test
	public void testFourWordsInGalacticAssignmentReturnsNoIdeaLine() {
		List<String> inputLines = new ArrayList<String>();
		inputLines.add("glob is I A");

		//Expected behavior assumption: Output will have this line "I have no idea what you are talking about"
		List<String> expectedOutputLines = new ArrayList<String>();
		expectedOutputLines.add("I have no idea what you are talking about");

		// Process input lines
		MerchantsNotesProcessorImpl merchantsNotesProcessor = (MerchantsNotesProcessorImpl) factory.getObject("MerchantsNotesProcessor");
		List<String> generatedOutputLines = merchantsNotesProcessor.processInputLines(inputLines);

		assertEquals(expectedOutputLines, generatedOutputLines);
	}
}
