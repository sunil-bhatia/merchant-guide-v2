package com.thoughtworks.merchant.iomanagers;

import java.util.List;

import com.thoughtworks.merchant.lines.listmanagers.OutputLinesListManager;

public class ConsoleOutputLinesWriter implements OutputLinesWriter {

	@Override
	public void writeOutput() {

		// Get output lines list from manager
		List<String> outputLines = OutputLinesListManager.getOutputLines();

		System.out.println();
		System.out.println("Results:");
		System.out.println();
		for (String line : outputLines) {
			System.out.println(line);
		}
	}
}
