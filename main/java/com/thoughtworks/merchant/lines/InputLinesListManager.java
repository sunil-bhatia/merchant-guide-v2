package com.thoughtworks.merchant.lines;

import java.util.ArrayList;
import java.util.List;

//This class maintains Input Lines List
public class InputLinesListManager {

	private static List<String> inputLines = new ArrayList<>();

	public static List<String> getInputLines() {
		return inputLines;
	}

	public static void setInputLines(List<String> inputLines) {
		InputLinesListManager.inputLines = inputLines;
	}
}
