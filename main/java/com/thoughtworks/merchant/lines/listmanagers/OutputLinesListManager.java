package com.thoughtworks.merchant.lines.listmanagers;

import java.util.ArrayList;
import java.util.List;

//This class maintains Output Lines List
public class OutputLinesListManager {

	private static List<String> outputLines = new ArrayList<>();
	
	public static void addLine(String line){
		outputLines.add(line);
	}

	public static List<String> getOutputLines() {
		return outputLines;
	}
}
