package com.thoughtworks.merchant.lines.listmanagers;

import java.util.ArrayList;
import java.util.List;

//This class maintains Output Lines List
public class OutputLinesListManager implements ListManager{

	private static List<String> outputLines = new ArrayList<>();
	
	@Override
	public void addObject(String object) {
		outputLines.add(object);
	}

	@Override
	public List<String> getList() {
		return outputLines;
	}

	@Override
	public void setList(List<String> list) {
		outputLines = list;
	}
}
