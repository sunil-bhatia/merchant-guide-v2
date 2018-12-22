package com.thoughtworks.merchant.iomanagers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.lines.listmanagers.ListManager;

public class FileLogWriter implements ListWriter {

	@Override
	public void write() {
		
		// Get log list from manager
		ListManager logsListManager = Factory.getLogsListManagerObject();
		List<String> logs = logsListManager.getList();
		
		String logFilePathAndName = ConfigPropertiesManager.getLogFilePathAndName();
		
        Path path = Paths.get(logFilePathAndName);
        try {
			Files.write(path, logs);
		} catch (IOException e) {
            System.err.println("Unable to write to log file: " + e.getMessage());
            System.exit(1);
        }
	}
}
