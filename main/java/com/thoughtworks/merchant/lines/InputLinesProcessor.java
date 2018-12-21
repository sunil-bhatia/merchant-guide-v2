package com.thoughtworks.merchant.lines;

import java.util.List;

import com.thoughtworks.merchant.factories.LineFactory;
import com.thoughtworks.merchant.iomanagers.OutputLinesManager;

public class InputLinesProcessor {

	public static List<String> processInputLines(List<String> lines) {
		
		// Process each line
		for (String line : lines) {
			Line lineObject = LineFactory.getLineObject(line);
			lineObject.process();
		}

		return OutputLinesManager.getOutputLines();
	}
}
