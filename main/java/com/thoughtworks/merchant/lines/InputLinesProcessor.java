package com.thoughtworks.merchant.lines;

import java.util.List;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.lines.linetypes.Line;
import com.thoughtworks.merchant.lines.listmanagers.OutputLinesListManager;

public class InputLinesProcessor {

	public static List<String> processInputLines(List<String> lines) {
		
		// Process each line
		for (String line : lines) {
			Line lineObject = Factory.getLineObject(line);
			lineObject.process();
		}

		return OutputLinesListManager.getOutputLines();
	}
}
