package com.thoughtworks.merchant.iomanagers;

import java.util.List;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.lines.listmanagers.ListManager;

public class ConsoleOutputLinesWriter implements ListWriter {

	@Override
	public void write() {

		// Get output lines list from manager
		ListManager outputLinesListManager = Factory.getOutputLinesListManagerObject();
		List<String> outputLines = outputLinesListManager.getList();

		System.out.println();
		System.out.println("Results:");
		System.out.println();
		for (String line : outputLines) {
			System.out.println(line);
		}
	}
}
