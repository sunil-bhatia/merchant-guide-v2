package com.thoughtworks.merchant.iomanagers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.thoughtworks.merchant.lines.listmanagers.LogsListManager;

public class FileLogWriter implements LogWriter {

	@Override
	public void writeLogs() {
		
		// Get log list from manager
		List<String> logs = LogsListManager.getLogs();
		
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
