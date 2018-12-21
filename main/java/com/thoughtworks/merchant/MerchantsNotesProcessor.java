package com.thoughtworks.merchant;
import java.util.List;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.iomanagers.InputLinesReader;
import com.thoughtworks.merchant.iomanagers.InputLinesWriter;
import com.thoughtworks.merchant.iomanagers.LogWriter;
import com.thoughtworks.merchant.iomanagers.OutputLinesWriter;
import com.thoughtworks.merchant.lines.Line;

public class MerchantsNotesProcessor {
	
	private InputLinesReader inputLinesReader;
	private InputLinesWriter inputLinesWriter;
	private OutputLinesWriter outputLinesWriter;
	private LogWriter logWriter; 
	
	public MerchantsNotesProcessor(InputLinesReader inputLinesReader, InputLinesWriter inputLinesWriter,
			OutputLinesWriter outputLinesWriter, LogWriter logWriter) {
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
		
		inputLinesWriter.writeInput();
		outputLinesWriter.writeOutput();
		logWriter.writeLogs();
	}
}