package com.thoughtworks.merchant.iomanagers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.thoughtworks.merchant.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.ListWriter;

public class FileLogWriter implements ListWriter {

	@Override
	public void write(List<String> list, String title) {
		
		String logFilePathAndName = ConfigPropertiesManager.getPropertyValue("logFilePathAndName");
		
        Path path = Paths.get(logFilePathAndName);
        try {
			Files.write(path, list);
		} catch (IOException e) {
            System.err.println("Unable to write to log file: " + e.getMessage());
            System.exit(1);
        }
	}
}
