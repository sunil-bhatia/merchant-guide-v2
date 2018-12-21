package com.thoughtworks.merchant.iomanagers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//This class maintains Log List
public class LogManager {

	private static List<String> logs = new ArrayList<>();
	
	public static void add(String message){
		logs.add(message);
	}
	
	public static void printLogs() {
        Path path = Paths.get("C:/Temp/log.txt");
        try {
			Files.write(path, logs);
		} catch (IOException e) {
            System.err.println("Unable to write to log file: " + e.getMessage());
            System.exit(1);
        }
	}

	public static List<String> getLogs() {
		return logs;
	}
}
