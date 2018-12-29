package com.thoughtworks.merchant.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.MerchantsNotesProcessor;
import com.thoughtworks.merchant.interfaces.Line;
import com.thoughtworks.merchant.interfaces.ListManager;
import com.thoughtworks.merchant.interfaces.ListReader;
import com.thoughtworks.merchant.interfaces.ListWriter;

public class Factory {
	
	private static ListManager inputLinesListManagerObject;
	private static ListManager outputLinesListManagerObject;
	private static ListManager logsListManagerObject;
	
	public static MerchantsNotesProcessor createMerchantsNotesProcessor() {

		ListReader inputLinesReader = (ListReader) Factory.getObject("inputLinesReader");
		ListWriter inputLinesWriter = (ListWriter) Factory.getObject("inputLinesWriter");
		ListWriter outputLinesWriter = (ListWriter) Factory.getObject("outputLinesWriter");
		ListWriter logWriter = (ListWriter) Factory.getObject("logWriter");

		MerchantsNotesProcessor merchantsNotesProcessor = new MerchantsNotesProcessor(inputLinesReader,
				inputLinesWriter, outputLinesWriter, logWriter);
		return merchantsNotesProcessor;
	}

	// Based on the format of the line, return an appropriate object of type Line
	public static Line getLineObject(String line) {

		// Get the mapping of class names and regex, for each of the line types
		HashMap<String, String> lineTypesMap = ConfigPropertiesManager.getLineTypesMap();

		// If line does not match with any of the regex, then by default it will be considered of invalid type
		Line lineObject = (Line) constructLineObject("invalidLineType", line, "");

		// For each of the line type, try to match this line with its corresponding regex
		// and if it matches, instantiate an object of the corresponding class
		for (Entry<String, String> entry : lineTypesMap.entrySet()) {
			String objectName = entry.getKey();
			String regex = entry.getValue();

			Pattern ptn = Pattern.compile(regex);
			Matcher mcher = ptn.matcher(line);
			if (mcher.matches()) {
				lineObject = (Line) constructLineObject(objectName, line, regex);
			}
		}

		return lineObject;
	}
	
	public static Object constructLineObject(String objectName, String line, String regex) {

		Object object = null;

		String className = ConfigPropertiesManager.getClassName(objectName);

		Class<?> classObject;

		try {
			classObject = Class.forName(className);
			Constructor<?> constructor = classObject.getConstructor(String.class, String.class);
			object = constructor.newInstance(line, regex);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return object;
	}

	public static Object getObject(String objectName) {

		Object object = null;

		String className = ConfigPropertiesManager.getClassName(objectName);

		Class<?> classObject;

		try {
			classObject = Class.forName(className);
			Constructor<?> constructor = classObject.getConstructor();
			object = constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return object;
	}
	
	// We need to instantiate ony one instance of this object
	public static ListManager getInputLinesListManagerObject() {
		
		if (inputLinesListManagerObject == null){
			inputLinesListManagerObject = (ListManager) Factory.getObject("listManager");
		}
		
		return inputLinesListManagerObject;
	}
	
	// We need to instantiate ony one instance of this object
	public static ListManager getOutputLinesListManagerObject() {
		
		if (outputLinesListManagerObject == null){
			outputLinesListManagerObject = (ListManager) Factory.getObject("listManager");
		}
		
		return outputLinesListManagerObject;
	}
	
	// We need to instantiate ony one instance of this object
	public static ListManager getLogsListManagerObject() {
		
		if (logsListManagerObject == null){
			logsListManagerObject = (ListManager) Factory.getObject("listManager");
		}

		return logsListManagerObject;
	}
}
