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

public class NewScopeAcceptanceTest {
	
    @Before
    public void setupConfig() {
    	
    	// Configure properties
    	String[] args = {"confignewscope"};
		ConfigPropertiesManager.configureProperties(args);
		
    }

	@Test
	// Acceptance Test
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
		inputLines.add("how many Credits is Silver per unit quantity ?");

		List<String> expectedOutputLines = new ArrayList<String>();
		expectedOutputLines.add("pish tegj glob glob is 42");
		expectedOutputLines.add("glob prok Silver is 68 Credits");
		expectedOutputLines.add("glob prok Gold is 57800 Credits");
		expectedOutputLines.add("glob prok Iron is 782 Credits");
		expectedOutputLines.add("I have no idea what you are talking about");
		expectedOutputLines.add("Per unit quantity Silver is 17 Credits");
		
		// Process input lines
		MerchantsNotesProcessor merchantsNotesProcessor = Factory.createMerchantsNotesProcessor();
		List<String> generatedOutputLines = merchantsNotesProcessor.processLines(inputLines);

		assertEquals(expectedOutputLines, generatedOutputLines);
	}
	
    @After
    public void teardownAllMaps() {
		AliasMap aliasMap = (AliasMap) Factory.getObject("aliasMap");
		aliasMap.getAliasMap().clear();
		
		CommodityMap commodityMap = (CommodityMap) Factory.getObject("commodityMap");
		commodityMap.getCommodityMap().clear();
		
		// clear other maps as well
    }
}
