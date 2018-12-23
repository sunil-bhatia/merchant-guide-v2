package com.thoughtworks.merchant.lines;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.merchant.MerchantsNotesProcessor;
import com.thoughtworks.merchant.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.AliasMap;
import com.thoughtworks.merchant.interfaces.CommodityMap;
import com.thoughtworks.merchant.interfaces.ListManager;


public class AliasAssignmentLineValidationTest {
	
    @Before
    public void setupConfig() {
    	
    	// Configure properties
    	String[] args = {"config"};
		ConfigPropertiesManager.configureProperties(args);
		
    }
	
	@Test
	public void testDuplicateGalacticSymbolInAliasAssignmentOverwrites() {
		List<String> inputLines = new ArrayList<String>();
		inputLines.add("glob is C");
		inputLines.add("glob is I");
		inputLines.add("how much is glob ?");

		//Expected Behaviour - If the map previously contained a mapping for the key, the old value is replaced.
		List<String> expectedOutputLines = new ArrayList<String>();
		expectedOutputLines.add("glob is 1");
		
		// Process input lines
		MerchantsNotesProcessor merchantsNotesProcessor = Factory.createMerchantsNotesProcessor();
		merchantsNotesProcessor.processLines(inputLines);

		// Get output lines list from manager
		ListManager outputLinesListManager = Factory.getOutputLinesListManagerObject();
		List<String> generatedOutputLines = outputLinesListManager.getList();

		assertEquals(expectedOutputLines, generatedOutputLines);
	}
	
	@Test
	public void testIncorrectRomanSymbolInAliasAssignmentReturnsNoIdeaLine() {
		List<String> inputLines = new ArrayList<String>();
		inputLines.add("glob is T");

		//Expected behavior assumption: Output will have this line "I have no idea what you are talking about"
		List<String> expectedOutputLines = new ArrayList<String>();
		expectedOutputLines.add("I have no idea what you are talking about");

		// Process input lines
		MerchantsNotesProcessor merchantsNotesProcessor = Factory.createMerchantsNotesProcessor();
		merchantsNotesProcessor.processLines(inputLines);

		// Get output lines list from manager
		ListManager outputLinesListManager = Factory.getOutputLinesListManagerObject();
		List<String> generatedOutputLines = outputLinesListManager.getList();
		
		assertEquals(expectedOutputLines, generatedOutputLines);
	}
	
	@Test
	public void testTwoCharRomanSymbolInAliasAssignmentReturnsNoIdeaLine() {
		List<String> inputLines = new ArrayList<String>();
		inputLines.add("glob is II");

		//Expected behavior assumption: Output will have this line "I have no idea what you are talking about"
		List<String> expectedOutputLines = new ArrayList<String>();
		expectedOutputLines.add("I have no idea what you are talking about");

		// Process input lines
		MerchantsNotesProcessor merchantsNotesProcessor = Factory.createMerchantsNotesProcessor();
		merchantsNotesProcessor.processLines(inputLines);

		// Get output lines list from manager
		ListManager outputLinesListManager = Factory.getOutputLinesListManagerObject();
		List<String> generatedOutputLines = outputLinesListManager.getList();
		
		assertEquals(expectedOutputLines, generatedOutputLines);
	}
	
	@Test
	public void testIsMissingInAliasAssignmentReturnsNoIdeaLine() {
		List<String> inputLines = new ArrayList<String>();
		inputLines.add("glob in I");

		//Expected behavior assumption: Output will have this line "I have no idea what you are talking about"
		List<String> expectedOutputLines = new ArrayList<String>();
		expectedOutputLines.add("I have no idea what you are talking about");

		// Process input lines
		MerchantsNotesProcessor merchantsNotesProcessor = Factory.createMerchantsNotesProcessor();
		merchantsNotesProcessor.processLines(inputLines);

		// Get output lines list from manager
		ListManager outputLinesListManager = Factory.getOutputLinesListManagerObject();
		List<String> generatedOutputLines = outputLinesListManager.getList();
		
		assertEquals(expectedOutputLines, generatedOutputLines);
	}
	
	@Test
	public void testFourWordsInAliasAssignmentReturnsNoIdeaLine() {
		List<String> inputLines = new ArrayList<String>();
		inputLines.add("glob is I A");

		//Expected behavior assumption: Output will have this line "I have no idea what you are talking about"
		List<String> expectedOutputLines = new ArrayList<String>();
		expectedOutputLines.add("I have no idea what you are talking about");

		// Process input lines
		MerchantsNotesProcessor merchantsNotesProcessor = Factory.createMerchantsNotesProcessor();
		merchantsNotesProcessor.processLines(inputLines);

		// Get output lines list from manager
		ListManager outputLinesListManager = Factory.getOutputLinesListManagerObject();
		List<String> generatedOutputLines = outputLinesListManager.getList();
		
		assertEquals(expectedOutputLines, generatedOutputLines);
	}
	
    @After
    public void teardownAllMaps() {
		AliasMap aliasMap = Factory.getAliasMapObject();
		aliasMap.getAliasMap().clear();
		
		CommodityMap commodityMap = Factory.getCommodityMapObject();
		commodityMap.getCommodityMap().clear();
		
		ListManager outputLinesListManager = Factory.getOutputLinesListManagerObject();
		outputLinesListManager.getList().clear();
		
		// clear other maps as well
    }
}
