package com.thoughtworks.merchant.factories;

import java.util.HashMap;
import java.util.ResourceBundle;

public class ConfigPropertiesManager {
	
	private static HashMap<String, String> lineTypesMap = new HashMap<String, String>();
	private static String inputLinesManagerClassName = "";

	public static void configureProperties(String[] args){
		
		ResourceBundle rb = ResourceBundle.getBundle(args[1]);
		
		configureLineTypes(rb);
		
		inputLinesManagerClassName = rb.getString("inputLinesManagerClassName");
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
	
	public static String getInputLinesManagerClassName() {
		return inputLinesManagerClassName;
	}
}
