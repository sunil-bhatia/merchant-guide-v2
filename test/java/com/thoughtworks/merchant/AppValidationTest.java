package com.thoughtworks.merchant;

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


public class AppValidationTest {
	
    @Before
    public void setupConfig() {
    	
    	// Configure properties
    	String[] args = {"config"};
		ConfigPropertiesManager.configureProperties(args);
		
    }

	@Test
	// Acceptance Test
	public void testStatedInputReturnsStatedOutput() {
		List<String> inputLines = new ArrayList<String>();
		inputLines.add("glob is I");
		inputLines.add("prok is V");
		inputLines.add("pish is X");
		inputLines.add("tegj is L");
		inputLines.add("glob glob Silver is 34 Credits");
		inputLines.add("glob prok Gold is 57800 Credits");
		inputLines.add("pish pish Iron is 3910 Credits");
		inputLines.add("how much is pish tegj glob glob ?");
		inputLines.add("how many Credits is glob prok Silver ?");
		inputLines.add("how many Credits is glob prok Gold ?");
		inputLines.add("how many Credits is glob prok Iron ?");
		inputLines.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");

		List<String> expectedOutputLines = new ArrayList<String>();
		expectedOutputLines.add("pish tegj glob glob is 42");
		expectedOutputLines.add("glob prok Silver is 68 Credits");
		expectedOutputLines.add("glob prok Gold is 57800 Credits");
		expectedOutputLines.add("glob prok Iron is 782 Credits");
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
	public void testNewScopeInputReturnsExpectedOutput() {
		List<String> inputLines = new ArrayList<String>();
		inputLines.add("glob is I");
		inputLines.add("prok is V");
		inputLines.add("pish is X");
		inputLines.add("tegj is L");
		inputLines.add("glob glob Silver is 34 Credits");
		inputLines.add("glob prok Gold is 57800 Credits");
		inputLines.add("pish pish Iron is 3910 Credits");
		inputLines.add("how much is pish tegj glob glob ?");
		inputLines.add("how many Credits is glob prok Silver ?");
		inputLines.add("how many Credits is glob prok Gold ?");
		inputLines.add("how many Credits is glob prok Iron ?");
		inputLines.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");

		List<String> expectedOutputLines = new ArrayList<String>();
		expectedOutputLines.add("pish tegj glob glob is 42");
		expectedOutputLines.add("glob prok Silver is 68 Credits");
		expectedOutputLines.add("glob prok Gold is 57800 Credits");
		expectedOutputLines.add("glob prok Iron is 782 Credits");
		expectedOutputLines.add("I have no idea what you are talking about");
		
		// Process input lines
		MerchantsNotesProcessor merchantsNotesProcessor = Factory.createMerchantsNotesProcessor();
		merchantsNotesProcessor.processLines(inputLines);

		// Get output lines list from manager
		ListManager outputLinesListManager = Factory.getOutputLinesListManagerObject();
		List<String> generatedOutputLines = outputLinesListManager.getList();
		
		assertEquals(expectedOutputLines, generatedOutputLines);
	}
	
//	@Test
//	public void testDuplicateGalacticSymbolInAliasAssignmentOverwrites() {
//		List<String> inputLines = new ArrayList<String>();
//		inputLines.add("glob is C");
//		inputLines.add("glob is I");
//		inputLines.add("how much is glob ?");
//
//		//Expected Behaviour - If the map previously contained a mapping for the key, the old value is replaced.
//		List<String> expectedOutputLines = new ArrayList<String>();
//		expectedOutputLines.add("glob is 1");
//
//		List<String> generatedOutputLines = InputLinesProcessor.processInputLines(inputLines);
//		assertEquals(expectedOutputLines, generatedOutputLines);
//	}
//	
//	@Test
//	public void testIncorrectRomanSymbolInAliasAssignmentReturnsNoIdeaLine() {
//		List<String> inputLines = new ArrayList<String>();
//		inputLines.add("glob is T");
//
//		//Expected behavior assumption: Output will have this line "I have no idea what you are talking about"
//		List<String> expectedOutputLines = new ArrayList<String>();
//		expectedOutputLines.add("I have no idea what you are talking about");
//
//		List<String> generatedOutputLines = InputLinesProcessor.processInputLines(inputLines);
//		assertEquals(expectedOutputLines, generatedOutputLines);
//	}
//	
//	@Test
//	public void testIncorrectGalacticSymbolInValueAssignmentReturnsNoIdeaLine() {
//		List<String> inputLines = new ArrayList<String>();
//		inputLines.add("glob is I");
//		inputLines.add("prok is V");
//		inputLines.add("cdef Silver is 34 Credits");
//
//		//Expected behavior assumption: Output will have this line "I have no idea what you are talking about"
//		List<String> expectedOutputLines = new ArrayList<String>();
//		expectedOutputLines.add("I have no idea what you are talking about");
//
//		List<String> generatedOutputLines = InputLinesProcessor.processInputLines(inputLines);
//		assertEquals(expectedOutputLines, generatedOutputLines);
//	}
//	
//	@Test
//	public void testIncorrectRomanNumInValueAssignmentReturnsNoIdeaLine() {
//		List<String> inputLines = new ArrayList<String>();
//		inputLines.add("glob is I");
//		inputLines.add("glob glob glob glob Silver is 34 Credits");
//
//		//Expected behavior assumption: Output will have this line "I have no idea what you are talking about"
//		List<String> expectedOutputLines = new ArrayList<String>();
//		expectedOutputLines.add("I have no idea what you are talking about");
//
//		List<String> generatedOutputLines = InputLinesProcessor.processInputLines(inputLines);
//		assertEquals(expectedOutputLines, generatedOutputLines);
//	}
//	
//	@Test
//	public void testDuplicateCommodityInValueAssignmentOverwrites() {
//		List<String> inputLines = new ArrayList<String>();
//		inputLines.add("glob is I");
//		inputLines.add("glob Silver is 10 Credits");
//		inputLines.add("glob glob Silver is 2 Credits");
//		inputLines.add("how many Credits is glob glob glob Silver ?");
//
//		//Expected Behaviour - If the map previously contained a mapping for the key, the old value is replaced.
//		List<String> expectedOutputLines = new ArrayList<String>();
//		expectedOutputLines.add("glob glob glob Silver is 3 Credits");
//
//		List<String> generatedOutputLines = InputLinesProcessor.processInputLines(inputLines);
//		assertEquals(expectedOutputLines, generatedOutputLines);
//	}
//	
//	@Test
//	public void testIncorrectGalacticSymbolInQtyQuestionReturnsNoIdeaLine() {
//		List<String> inputLines = new ArrayList<String>();
//		inputLines.add("glob is I");
//		inputLines.add("prok is V");
//		inputLines.add("how much is ijkl ?");
//		
//		//Expected behavior assumption: Output will have this line "I have no idea what you are talking about"
//		List<String> expectedOutputLines = new ArrayList<String>();
//		expectedOutputLines.add("I have no idea what you are talking about");
//
//		List<String> generatedOutputLines = InputLinesProcessor.processInputLines(inputLines);
//		assertEquals(expectedOutputLines, generatedOutputLines);
//	}
//	
//	@Test
//	public void testIncorrectGalacticSymbolInValueQuestionReturnsNoIdeaLine() {
//		List<String> inputLines = new ArrayList<String>();
//		inputLines.add("glob is I");
//		inputLines.add("prok is V");
//		inputLines.add("how many Credits is mnop prok Silver ?");
//
//		//Expected behavior assumption: Output will have this line "I have no idea what you are talking about"
//		List<String> expectedOutputLines = new ArrayList<String>();
//		expectedOutputLines.add("I have no idea what you are talking about");
//
//		List<String> generatedOutputLines = InputLinesProcessor.processInputLines(inputLines);
//		assertEquals(expectedOutputLines, generatedOutputLines);
//	}
//	
//
//	@Test
//	public void testIncorrectCommodityInValueQuestionReturnsNoIdeaLine() {
//		List<String> inputLines = new ArrayList<String>();
//		inputLines.add("glob is I");
//		inputLines.add("glob glob Silver is 34 Credits");
//		inputLines.add("how many Credits is glob glob Copper ?");
//
//		//Expected behavior assumption: Output will have this line "I have no idea what you are talking about"
//		List<String> expectedOutputLines = new ArrayList<String>();
//		expectedOutputLines.add("I have no idea what you are talking about");
//
//		List<String> generatedOutputLines = InputLinesProcessor.processInputLines(inputLines);
//		assertEquals(expectedOutputLines, generatedOutputLines);
//	}
//	
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
