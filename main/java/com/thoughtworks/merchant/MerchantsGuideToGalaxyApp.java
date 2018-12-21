package com.thoughtworks.merchant;
import java.util.List;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.iomanagers.ConfigPropertiesManager;
import com.thoughtworks.merchant.iomanagers.InputLinesReader;
import com.thoughtworks.merchant.iomanagers.InputLinesWriter;
import com.thoughtworks.merchant.iomanagers.LogWriter;
import com.thoughtworks.merchant.iomanagers.OutputLinesWriter;
import com.thoughtworks.merchant.lines.InputLinesProcessor;
import com.thoughtworks.merchant.lines.listmanagers.InputLinesListManager;

public class MerchantsGuideToGalaxyApp {
	
	public static void main(String[] args) {
		
		//Delegate to Config Properties Manager to configure the properties like Dependency Injection classes etc
		ConfigPropertiesManager.configureProperties(args);

		InputLinesReader inputLinesReader = Factory.getInputLinesReaderObject();
		
		//Delegate to Input Lines Reader to read input lines
		//Delegate to Input Lines Manager to get Input Lines Array
		inputLinesReader.readInputLines();
		final List<String> inputLines = InputLinesListManager.getInputLines();
		
		//Delegate to Input Lines Processor to do all the processing
		InputLinesProcessor.processInputLines(inputLines);

		//Print the Input, Output and Logs
		printInputOutputLogs();
	}

	private static void printInputOutputLogs() {
		
		InputLinesWriter inputLinesWriter = Factory.getInputLinesWriterObject();
		inputLinesWriter.writeInput();
		
		OutputLinesWriter outputLinesWriter = Factory.getOutputLinesWriterObject();
		outputLinesWriter.writeOutput();
		
		LogWriter logWriter = Factory.getLogWriterObject();
		logWriter.writeLogs();
	}
}