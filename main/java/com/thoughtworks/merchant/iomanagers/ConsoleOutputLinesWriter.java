package com.thoughtworks.merchant.iomanagers;

import java.util.List;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.lines.listmanagers.OutputLinesListManager;

public class ConsoleOutputLinesWriter implements OutputLinesWriter {

	@Override
	public void writeOutput() {

		// Get output lines list from manager
		OutputLinesListManager outputLinesListManager = Factory.getOutputLinesListManagerObject();
		List<String> outputLines = outputLinesListManager.getList();

		System.out.println();
		System.out.println("Results:");
		System.out.println();
		for (String line : outputLines) {
			System.out.println(line);
		}
	}
}
