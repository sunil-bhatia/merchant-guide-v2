package com.thoughtworks.merchant;
import java.util.List;

import com.thoughtworks.merchant.factories.LineTypeConfigManager;
import com.thoughtworks.merchant.iomanagers.FileInputLinesManager;
import com.thoughtworks.merchant.iomanagers.InputLinesManager;
import com.thoughtworks.merchant.iomanagers.LogManager;
import com.thoughtworks.merchant.iomanagers.OutputLinesManager;
import com.thoughtworks.merchant.lines.InputLinesProcessor;

public class MerchantsGuideToGalaxyApp {
	
	private static InputLinesManager inputLinesManager = new FileInputLinesManager();

	public static void main(String[] args) {
		
		//Delegate to Line Type Config Manager to configure the line types
		LineTypeConfigManager.configureLineTypesMap(args);

		//Delegate to Input Lines Manager to get Input Lines Array
		final List<String> inputLines = inputLinesManager.getInputLines(args);

		//Delegate to Input Lines Processor to do all the processing
		InputLinesProcessor.processInputLines(inputLines);

		//Print the Input, Output and Logs
		printInputOutputLogs();
	}

	private static void printInputOutputLogs() {
		inputLinesManager.printInput();
		OutputLinesManager.printOutput();
		LogManager.printLogs();
	}
}