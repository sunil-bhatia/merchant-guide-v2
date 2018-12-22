package com.thoughtworks.merchant.lines.listmanagers;

import java.util.ArrayList;
import java.util.List;

//This class maintains Input Lines List
public class InputLinesListManager implements ListManager {

	private static List<String> inputLines = new ArrayList<>();

	@Override
	public void addObject(String object) {
		inputLines.add(object);
	}

	@Override
	public List<String> getList() {
		return inputLines;
	}

	@Override
	public void setList(List<String> list) {
		inputLines = list;
	}
}
