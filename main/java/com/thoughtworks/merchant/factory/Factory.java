package com.thoughtworks.merchant.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.MerchantsNotesProcessor;
import com.thoughtworks.merchant.interfaces.Line;
import com.thoughtworks.merchant.interfaces.ListManager;
import com.thoughtworks.merchant.interfaces.ListReader;
import com.thoughtworks.merchant.interfaces.ListWriter;

public class Factory {
	
	private static ListManager logsListManagerObject;
	
	public static MerchantsNotesProcessor createMerchantsNotesProcessor() {

		ListReader inputLinesReader = (ListReader) Factory.getObject("inputLinesReader");
		ListWriter listWriter = (ListWriter) Factory.getObject("listWriter");
		ListWriter logWriter = (ListWriter) Factory.getObject("logWriter");

		MerchantsNotesProcessor merchantsNotesProcessor = new MerchantsNotesProcessor(inputLinesReader,
				listWriter, logWriter);
		return merchantsNotesProcessor;
	}

	// Based on the format of the line, return an appropriate object of type Line
	public static Line getLineObject(String line) {

		// If line does not match with any of the regex, then by default it will be considered of invalid type
		Line lineObject = (Line) getObject("invalidLineType");
		lineObject.setLine(line);
		lineObject.setRegex("");

		int numberOfLineTypes = Integer.parseInt(ConfigPropertiesManager.getPropertyValue("numberOfLineTypes"));

		String objectName = "";
		String regex = "";
		
		// For each of the line type, try to match this line with its corresponding regex
		// and if it matches, instantiate an object of the corresponding class
		for (int i = 0; i < numberOfLineTypes; i++) {
			objectName = "lineType" + (i + 1);
			regex = ConfigPropertiesManager.getPropertyValue("lineTypeRegex" + (i + 1));
			
			Pattern ptn = Pattern.compile(regex);
			Matcher mcher = ptn.matcher(line);
			if (mcher.matches()) {
				lineObject = (Line) getObject(objectName);
				lineObject.setLine(line);
				lineObject.setRegex(regex);
			}
		}

		return lineObject;
	}
	
	public static Object getObject(String objectName) {

		Object object = null;

		String className = ConfigPropertiesManager.getPropertyValue(objectName);

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
	
	// We need to instantiate only one instance of this object
	public static ListManager getLogsListManagerObject() {
		
		if (logsListManagerObject == null){
			logsListManagerObject = (ListManager) Factory.getObject("listManager");
		}

		return logsListManagerObject;
	}
}
