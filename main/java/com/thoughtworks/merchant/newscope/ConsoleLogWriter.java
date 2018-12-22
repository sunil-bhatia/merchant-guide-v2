package com.thoughtworks.merchant.newscope;

import java.util.List;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.iomanagers.ListWriter;
import com.thoughtworks.merchant.lines.listmanagers.ListManager;

public class ConsoleLogWriter implements ListWriter {

	@Override
	public void write() {
		
		// Get log list from manager
		ListManager logsListManager = Factory.getLogsListManagerObject();
		List<String> logs = logsListManager.getList();
		
		System.out.println();
		System.out.println("Logs:");
		System.out.println();
		for (String log : logs) {
			System.out.println(log);
		}
	}
}
