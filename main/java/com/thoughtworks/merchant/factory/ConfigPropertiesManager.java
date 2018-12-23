package com.thoughtworks.merchant.factory;

import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ConfigPropertiesManager {

	private static HashMap<String, String> lineTypesMap = new HashMap<String, String>();
	
	private static String invalidLineTypeClassName = "";
	
	private static String inputLinesReaderClassName = "";
	private static String inputLinesFileName = "";
	
	private static String inputLinesWriterClassName = "";
	private static String outputLinesWriterClassName = "";
	
	private static String logWriterClassName = "";
	private static String logFilePathAndName = "";
	
	private static String aliasMapClassName = "";
	private static String commodityMapClassName = "";
	private static String commodityCalculatorClassName = "";
	private static String romanNumeralsClassName = "";
	private static String galacticNumeralsClassName = "";
	
	private static String listManagerClassName = "";
	

	public static void configureProperties(String[] args) {

		if (args == null || args.length == 0) {
			System.err.println("Please enter the name of the properties file as a program argument, e.g.");
			System.err.println("java -jar MerchantGuide.jar config");
			System.exit(1);
		}

		ResourceBundle rb = null;
		try {
			rb = ResourceBundle.getBundle(args[0]);
		} catch (MissingResourceException e) {
			System.err.println(e.getMessage());
			System.err.println("Please try again. ");
			System.exit(1);
		}

		configureLineTypes(rb);
		
		invalidLineTypeClassName = rb.getString("invalidLineTypeClassName");

		inputLinesReaderClassName = rb.getString("inputLinesReaderClassName");
		inputLinesFileName = rb.getString("inputLinesFileName");
		
		inputLinesWriterClassName = rb.getString("inputLinesWriterClassName");
		outputLinesWriterClassName = rb.getString("outputLinesWriterClassName");
		
		logWriterClassName = rb.getString("logWriterClassName");
		logFilePathAndName = rb.getString("logFilePathAndName");
		
		aliasMapClassName = rb.getString("aliasMapClassName");
		commodityMapClassName = rb.getString("commodityMapClassName");
		commodityCalculatorClassName = rb.getString("commodityCalculatorClassName");
		romanNumeralsClassName = rb.getString("romanNumeralsClassName");
		galacticNumeralsClassName = rb.getString("galacticNumeralsClassName");
		
		listManagerClassName = rb.getString("listManagerClassName");
	}

	private static void configureLineTypes(ResourceBundle rb) {

		int numberOfLineTypes = Integer.parseInt(rb.getString("numberOfLineTypes"));

		String className = "";
		String regex = "";

		for (int i = 0; i < numberOfLineTypes; i++) {
			className = rb.getString("lineTypeClassName" + (i + 1));
			regex = rb.getString("lineTypeRegex" + (i + 1));
			lineTypesMap.put(className, regex);
		}
	}

	public static HashMap<String, String> getLineTypesMap() {
		return lineTypesMap;
	}

	public static String getInputLinesReaderClassName() {
		return inputLinesReaderClassName;
	}
	
	public static String getInputLinesFileName() {
		return inputLinesFileName;
	}

	public static String getInputLinesWriterClassName() {
		return inputLinesWriterClassName;
	}
	
	public static String getOutputLinesWriterClassName() {
		return outputLinesWriterClassName;
	}
	
	public static String getLogWriterClassName() {
		return logWriterClassName;
	}
	
	public static String getLogFilePathAndName() {
		return logFilePathAndName;
	}
	
	public static String getInvalidLineTypeClassName() {
		return invalidLineTypeClassName;
	}
	
	public static String getAliasMapClassName() {
		return aliasMapClassName;
	}

	public static String getCommodityMapClassName() {
		return commodityMapClassName;
	}

	public static String getCommodityCalculatorClassName() {
		return commodityCalculatorClassName;
	}

	public static String getRomanNumeralsClassName() {
		return romanNumeralsClassName;
	}

	public static String getGalacticNumeralsClassName() {
		return galacticNumeralsClassName;
	}
	
	public static String getListManagerClassName() {
		return listManagerClassName;
	}
}
