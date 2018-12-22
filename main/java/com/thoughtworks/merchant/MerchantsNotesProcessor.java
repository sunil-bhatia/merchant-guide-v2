package com.thoughtworks.merchant;
import java.util.List;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.iomanagers.ListReader;
import com.thoughtworks.merchant.iomanagers.ListWriter;
import com.thoughtworks.merchant.lines.Line;
import com.thoughtworks.merchant.lines.listmanagers.ListManager;

public class MerchantsNotesProcessor {
	
	private ListReader inputLinesReader;
	private ListWriter inputLinesWriter;
	private ListWriter outputLinesWriter;
	private ListWriter logWriter; 
	
	public MerchantsNotesProcessor(ListReader inputLinesReader, ListWriter inputLinesWriter,
			ListWriter outputLinesWriter, ListWriter logWriter) {
		super();
		this.inputLinesReader = inputLinesReader;
		this.inputLinesWriter = inputLinesWriter;
		this.outputLinesWriter = outputLinesWriter;
		this.logWriter = logWriter;
	}

	public void processMerchantNotes() {
		
		//Delegate to Input Lines Reader to read input lines
		final List<String> inputLines = inputLinesReader.read();
		
		// Process each line
		for (String line : inputLines) {
			Line lineObject = Factory.getLineObject(line);
			lineObject.process();
		}

		//Print the Input, Output and Logs
		printInputOutputLogs();
	}

	private void printInputOutputLogs() {
		
		inputLinesWriter.write();
		outputLinesWriter.write();
		ListManager logsListManager = Factory.getLogsListManagerObject();
		logsListManager.addObject("test where this is printed");
		logWriter.write();
	}
}