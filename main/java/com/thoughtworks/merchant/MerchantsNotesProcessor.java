package com.thoughtworks.merchant;
import java.util.List;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.iomanagers.InputLinesReader;
import com.thoughtworks.merchant.iomanagers.ListWriter;
import com.thoughtworks.merchant.lines.Line;

public class MerchantsNotesProcessor {
	
	private InputLinesReader inputLinesReader;
	private ListWriter inputLinesWriter;
	private ListWriter outputLinesWriter;
	private ListWriter logWriter; 
	
	public MerchantsNotesProcessor(InputLinesReader inputLinesReader, ListWriter inputLinesWriter,
			ListWriter outputLinesWriter, ListWriter logWriter) {
		super();
		this.inputLinesReader = inputLinesReader;
		this.inputLinesWriter = inputLinesWriter;
		this.outputLinesWriter = outputLinesWriter;
		this.logWriter = logWriter;
	}

	public void processMerchantNotes() {
		
		//Delegate to Input Lines Reader to read input lines
		final List<String> inputLines = inputLinesReader.readInputLines();
		
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
		logWriter.write();
	}
}