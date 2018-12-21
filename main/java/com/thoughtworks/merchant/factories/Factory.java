package com.thoughtworks.merchant.factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.iomanagers.InputLinesReader;
import com.thoughtworks.merchant.iomanagers.InputLinesWriter;
import com.thoughtworks.merchant.lines.InvalidLine;
import com.thoughtworks.merchant.lines.Line;

public class Factory {

	public static Line getLineObject(String line) {

		HashMap<String, String> lineTypesMap = ConfigPropertiesManager.getLineTypesMap();

		Line lineObject = new InvalidLine();

		Class<?> classObject;

		for (Entry<String, String> entry : lineTypesMap.entrySet()) {
			String className = entry.getKey();
			String regex = entry.getValue();

			Pattern ptn = Pattern.compile(regex);
			Matcher mcher = ptn.matcher(line);
			if (mcher.matches()) {
				try {
					classObject = Class.forName(className);
					Constructor<?> constructor = classObject.getConstructor(String.class, String.class);
					lineObject = (Line) constructor.newInstance(line, regex);
				} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
						| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}

		return lineObject;
	}

	public static InputLinesReader getInputLinesReaderObject() {

		InputLinesReader inputLinesReaderObject = null;
		
		String inputLinesReaderClassName = ConfigPropertiesManager.getInputLinesReaderClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(inputLinesReaderClassName);
			Constructor<?> constructor = classObject.getConstructor();
			inputLinesReaderObject = (InputLinesReader) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return inputLinesReaderObject;
	}
	
	public static InputLinesWriter getInputLinesWriterObject() {

		InputLinesWriter inputLinesWriterObject = null;
		
		String inputLinesWriterClassName = ConfigPropertiesManager.getInputLinesWriterClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(inputLinesWriterClassName);
			Constructor<?> constructor = classObject.getConstructor();
			inputLinesWriterObject = (InputLinesWriter) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return inputLinesWriterObject;
	}

}
