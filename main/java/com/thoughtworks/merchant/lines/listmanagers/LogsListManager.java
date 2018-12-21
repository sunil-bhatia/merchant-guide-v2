package com.thoughtworks.merchant.lines.listmanagers;

import java.util.ArrayList;
import java.util.List;

//This class maintains Log List
public class LogsListManager {

	private static List<String> logs = new ArrayList<>();
	
	public static void add(String message){
		logs.add(message);
	}

	public static List<String> getLogs() {
		return logs;
	}
}
