package com.thoughtworks.merchant;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.Line;
import com.thoughtworks.merchant.interfaces.ListManager;
import com.thoughtworks.merchant.interfaces.ListReader;
import com.thoughtworks.merchant.interfaces.ListWriter;

public class MerchantsNotesProcessor {
	
	private ListReader inputLinesReader;
	private ListWriter listWriter;
	private ListWriter logWriter; 
	
	public MerchantsNotesProcessor(ListReader inputLinesReader, ListWriter listWriter,
			ListWriter logWriter) {
		super();
		this.inputLinesReader = inputLinesReader;
		this.listWriter = listWriter;
		this.logWriter = logWriter;
	}

	public void processMerchantNotes() {
		
		//Read input lines
		final List<String> inputLines = inputLinesReader.read();
		
		//Process input lines
		List<String> outputLines = processLines(inputLines);

		//Print the Input, Output and Logs
		printInputLines(inputLines);
		printOutputLines(outputLines);
		printLogs();
	}
	
	public List<String> processLines(List<String> inputLines){
		
		List<String> outputLines = new ArrayList<String>();
		String outputLine;
		
		// Process each line
		for (String line : inputLines) {
			Line lineObject = Factory.getLineObject(line);
			outputLine = lineObject.process();
			
			if (!outputLine.isEmpty()){
				outputLines.add(outputLine);
			}
		}
		
		return outputLines;
		
	}
	
	private void printInputLines(List<String> inputLines) {   	
		listWriter.write(inputLines, "Input text:");
	}
	
	private void printOutputLines(List<String> outputLines) {   	
		listWriter.write(outputLines, "Results:");
	}
	
	private void printLogs() {
		
		// Get log list from manager
		ListManager logsListManager = Factory.getLogsListManagerObject();
		// For testing
		logsListManager.addObject("test where this is printed 4");
		List<String> logs = logsListManager.getList();
		
		logWriter.write(logs, "Logs:");
	}
}