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

public class GalacticQuestionLineValidationTest {
	
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
	public void testIncorrectGalacticSymbolInGalacticQuestionReturnsNoIdeaLine() {
		List<String> inputLines = new ArrayList<String>();
		inputLines.add("glob is I");
		inputLines.add("prok is V");
		inputLines.add("how much is ijkl ?");
		
		//Expected behavior assumption: Output will have this line "I have no idea what you are talking about"
		List<String> expectedOutputLines = new ArrayList<String>();
		expectedOutputLines.add("I have no idea what you are talking about");

		// Process input lines
		List<String> generatedOutputLines = merchantsNotesProcessor.processInputLines(inputLines);

		assertEquals(expectedOutputLines, generatedOutputLines);
	}
}
