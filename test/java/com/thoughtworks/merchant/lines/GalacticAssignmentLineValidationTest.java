package com.thoughtworks.merchant.lines;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.factory.FileConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.MerchantsNotesProcessor;
import com.thoughtworks.merchant.interfaces.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.factory.GeneralFactory;

public class GalacticAssignmentLineValidationTest {
	
	private GeneralFactory factory;
	private MerchantsNotesProcessor merchantsNotesProcessor;
	
    @Before
    public void setupConfig() {
    	
    	// Configure properties
    	String[] args = {"config"};
		ConfigPropertiesManager configPropertiesManager = new FileConfigPropertiesManager();
		configPropertiesManager.readConfigProperties(args);
		factory = configPropertiesManager.getGeneralFactoryObject();
		merchantsNotesProcessor = (MerchantsNotesProcessor) factory.getObject("MerchantsNotesProcessor");
		
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
		List<String> generatedOutputLines = merchantsNotesProcessor.processInputLines(inputLines);

		assertEquals(expectedOutputLines, generatedOutputLines);
	}
}
