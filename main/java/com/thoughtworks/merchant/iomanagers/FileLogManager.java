package com.thoughtworks.merchant.iomanagers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.thoughtworks.merchant.factory.FileConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.ConfigPropertiesManager;

public class FileLogManager extends GenericLogManager {

	@Override
	public void printLog() {
		
		ConfigPropertiesManager configPropertiesManager = new FileConfigPropertiesManager();
		String logFilePathAndName = configPropertiesManager.getPropertyValue("LogFilePathAndName");
		
        Path path = Paths.get(logFilePathAndName);
        try {
			Files.write(path, logs);
		} catch (IOException e) {
            System.err.println("Unable to write to log file: " + e.getMessage());
            System.exit(1);
        }
	}
}
