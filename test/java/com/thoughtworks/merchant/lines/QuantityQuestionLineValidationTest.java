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


public class QuantityQuestionLineValidationTest {
	
    @Before
    public void setupConfig() {
    	
    	// Configure properties
    	String[] args = {"config"};
		ConfigPropertiesManager.configureProperties(args);
		
    }

	@Test
	public void testIncorrectGalacticSymbolInQtyQuestionReturnsNoIdeaLine() {
		List<String> inputLines = new ArrayList<String>();
		inputLines.add("glob is I");
		inputLines.add("prok is V");
		inputLines.add("how much is ijkl ?");
		
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
