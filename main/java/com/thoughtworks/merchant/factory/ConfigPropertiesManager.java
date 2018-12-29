package com.thoughtworks.merchant.factory;

import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ConfigPropertiesManager {

	private static HashMap<String, String> lineTypesMap = new HashMap<String, String>();
	
	private static String inputLinesFileName = "";
	
	private static String isLogFileNeeded = "";
	private static String logFilePathAndName = "";
	
	private static ResourceBundle rb = null;
	

	public static void configureProperties(String[] args) {

		if (args == null || args.length == 0) {
			System.err.println("Please enter the name of the properties file as a program argument, e.g.");
			System.err.println("java -jar MerchantGuide.jar config");
			System.exit(1);
		}

		try {
			rb = ResourceBundle.getBundle(args[0]);
		} catch (MissingResourceException e) {
			System.err.println(e.getMessage());
			System.err.println("Please try again. ");
			System.exit(1);
		}

		configureLineTypes();
		
		inputLinesFileName = rb.getString("inputLinesFileName");
		
		isLogFileNeeded = rb.getString("isLogFileNeeded");
		if (isLogFileNeeded.equals("true")){
			logFilePathAndName = rb.getString("logFilePathAndName");
		}
		
	}

	private static void configureLineTypes() {

		int numberOfLineTypes = Integer.parseInt(rb.getString("numberOfLineTypes"));

		String objectName = "";
		String regex = "";

		for (int i = 0; i < numberOfLineTypes; i++) {
			objectName = "lineType" + (i + 1);
			regex = rb.getString("lineTypeRegex" + (i + 1));
			lineTypesMap.put(objectName, regex);
		}
	}

	public static HashMap<String, String> getLineTypesMap() {
		return lineTypesMap;
	}

	public static String getInputLinesFileName() {
		return inputLinesFileName;
	}

	public static String getLogFilePathAndName() {
		return logFilePathAndName;
	}
	
	public static String getClassName(String objectName) {
		String className = rb.getString(objectName);
		return className;
	}
}
