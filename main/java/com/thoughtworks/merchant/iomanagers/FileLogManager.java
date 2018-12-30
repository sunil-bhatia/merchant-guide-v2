package com.thoughtworks.merchant.iomanagers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.thoughtworks.merchant.factory.ConfigPropertiesManager;

public class FileLogManager extends GenericLogManager {

	@Override
	public void printLog() {
		
		String logFilePathAndName = ConfigPropertiesManager.getPropertyValue("logFilePathAndName");
		
        Path path = Paths.get(logFilePathAndName);
        try {
			Files.write(path, logs);
		} catch (IOException e) {
            System.err.println("Unable to write to log file: " + e.getMessage());
            System.exit(1);
        }
	}
}
