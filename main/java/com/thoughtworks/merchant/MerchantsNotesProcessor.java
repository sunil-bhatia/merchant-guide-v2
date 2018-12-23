package com.thoughtworks.merchant;
import java.util.List;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.Line;
import com.thoughtworks.merchant.interfaces.ListReader;
import com.thoughtworks.merchant.interfaces.ListWriter;

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
		
		//Read input lines
		final List<String> inputLines = inputLinesReader.read();
		
		//Process input lines
		processLines(inputLines);

		//Print the Input, Output and Logs
		printInputOutputLogs();
	}
	
	public void processLines(List<String> inputLines){
		
		// Process each line
		for (String line : inputLines) {
			Line lineObject = Factory.getLineObject(line);
			lineObject.process();
		}
	}

	private void printInputOutputLogs() {
		
		inputLinesWriter.write();
		outputLinesWriter.write();
		logWriter.write();
	}
}