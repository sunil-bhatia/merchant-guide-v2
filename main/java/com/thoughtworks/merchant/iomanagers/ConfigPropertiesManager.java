package com.thoughtworks.merchant.iomanagers;

import java.util.HashMap;
import java.util.ResourceBundle;

public class ConfigPropertiesManager {
	
	private static HashMap<String, String> lineTypesMap = new HashMap<String, String>();
	private static String inputLinesReaderClassName = "";
	private static String inputLinesWriterClassName = "";
	private static String inputLinesFileName = "";

	public static void configureProperties(String[] args){
		
		ResourceBundle rb = ResourceBundle.getBundle(args[1]);
		
		configureLineTypes(rb);
		
		inputLinesReaderClassName = rb.getString("inputLinesReaderClassName");
		inputLinesWriterClassName = rb.getString("inputLinesWriterClassName");
		inputLinesFileName = rb.getString("inputLinesFileName");
	}

	private static void configureLineTypes(ResourceBundle rb){
		
		int numberOfLineTypes = Integer.parseInt(rb.getString("numberOfLineTypes"));
		
        String className = "";
        String regex = "";

		for (int i = 0; i < numberOfLineTypes; i++) {
			className = rb.getString("lineTypeClassName" + (i+1));
			regex = rb.getString("lineTypeRegex" + (i+1));
			lineTypesMap.put(className, regex);
		}
	}
	
	public static HashMap<String, String> getLineTypesMap() {
		return lineTypesMap;
	}
	
	public static String getInputLinesReaderClassName() {
		return inputLinesReaderClassName;
	}
	
	public static String getInputLinesWriterClassName() {
		return inputLinesWriterClassName;
	}
	
	public static String getInputLinesFileName() {
		return inputLinesFileName;
	}
}
