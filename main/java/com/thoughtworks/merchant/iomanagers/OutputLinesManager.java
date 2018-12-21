package com.thoughtworks.merchant.iomanagers;

import java.util.ArrayList;
import java.util.List;

//This class maintains Output Lines List
public class OutputLinesManager {

	private static List<String> outputLines = new ArrayList<>();
	
	public static void addLine(String line){
		outputLines.add(line);
	}
	
	public static void printOutput() {
		System.out.println();
		System.out.println("Results:");
		System.out.println();
		for (String line : outputLines) {
			System.out.println(line);
		}
	}

	public static List<String> getOutputLines() {
		return outputLines;
	}
}
