package com.thoughtworks.merchant.lines.listmanagers;

import java.util.ArrayList;
import java.util.List;

//This class maintains Log List
public class LogsListManager implements ListManager {

	private static List<String> logs = new ArrayList<>();
	
	@Override
	public void addObject(String object) {
		logs.add(object);
	}

	@Override
	public List<String> getList() {
		return logs;
	}

	@Override
	public void setList(List<String> list) {
		logs = list;
	}
}
