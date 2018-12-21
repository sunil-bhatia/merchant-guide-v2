package com.thoughtworks.merchant;
import java.util.List;

import com.thoughtworks.merchant.factories.ConfigPropertiesManager;
import com.thoughtworks.merchant.factories.Factory;
import com.thoughtworks.merchant.iomanagers.OutputLinesManager;
import com.thoughtworks.merchant.iomanagers.InputLinesReader;
import com.thoughtworks.merchant.iomanagers.InputLinesWriter;
import com.thoughtworks.merchant.iomanagers.LogManager;
import com.thoughtworks.merchant.lines.InputLinesListManager;
import com.thoughtworks.merchant.lines.InputLinesProcessor;

public class MerchantsGuideToGalaxyApp {
	
	public static void main(String[] args) {
		
		//Delegate to Config Properties Manager to configure the Dependency Injection classes
		ConfigPropertiesManager.configureProperties(args);

		InputLinesReader inputLinesReader = Factory.getInputLinesReaderObject();
		
		//Delegate to Input Lines Reader to read input lines
		//Delegate to Input Lines Manager to get Input Lines Array
		inputLinesReader.readInputLines(args);
		final List<String> inputLines = InputLinesListManager.getInputLines();
		
		//Delegate to Input Lines Processor to do all the processing
		InputLinesProcessor.processInputLines(inputLines);

		//Print the Input, Output and Logs
		printInputOutputLogs();
	}

	private static void printInputOutputLogs() {
		InputLinesWriter inputLinesWriter = Factory.getInputLinesWriterObject();
		inputLinesWriter.printInput();
		OutputLinesManager.printOutput();
		LogManager.printLogs();
	}
}