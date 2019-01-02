package com.thoughtworks.merchant;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.merchant.factory.FactoryImpl;
import com.thoughtworks.merchant.interfaces.Factory;
import com.thoughtworks.merchant.interfaces.Line;
import com.thoughtworks.merchant.interfaces.LogManager;
import com.thoughtworks.merchant.interfaces.MerchantsNotesProcessor;
import com.thoughtworks.merchant.interfaces.ListReader;
import com.thoughtworks.merchant.interfaces.ListWriter;

public class MerchantsNotesProcessorImpl implements MerchantsNotesProcessor {
	
	private ListReader inputLinesReader;
	private ListWriter listWriter;
	private LogManager logManager; 
	
	public MerchantsNotesProcessorImpl() {
	}
	
	@Override
	public void processMerchantNotes() {
		
		final List<String> inputLines = inputLinesReader.read();
		
		List<String> outputLines = processInputLines(inputLines);

		printInputLines(inputLines);
		printOutputLines(outputLines);
		printLogs();
	}
	
	@Override
	public List<String> processInputLines(List<String> inputLines){
		
		List<String> outputLines = new ArrayList<String>();
		String outputLine;
		
		// Process each line
		for (String line : inputLines) {
			Factory factory = new FactoryImpl();
			Line lineObject = factory.getLineObject(line);
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
		// For testing
		logManager.addLog("test where this is printed 4");
		logManager.printLog();
	}
	
	public void setInputLinesReader(Object inputLinesReader) {
		this.inputLinesReader = (ListReader) inputLinesReader;
	}

	public void setListWriter(Object listWriter) {
		this.listWriter = (ListWriter) listWriter;
	}

	public void setLogManager(Object logManager) {
		this.logManager = (LogManager) logManager;
	}
}