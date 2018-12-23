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


public class AppAcceptanceTest {
	
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
