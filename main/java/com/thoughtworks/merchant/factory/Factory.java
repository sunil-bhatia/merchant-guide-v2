package com.thoughtworks.merchant.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.MerchantsNotesProcessor;
import com.thoughtworks.merchant.interfaces.AliasMap;
import com.thoughtworks.merchant.interfaces.CommodityCalculator;
import com.thoughtworks.merchant.interfaces.CommodityMap;
import com.thoughtworks.merchant.interfaces.GalacticNumerals;
import com.thoughtworks.merchant.interfaces.Line;
import com.thoughtworks.merchant.interfaces.ListManager;
import com.thoughtworks.merchant.interfaces.ListReader;
import com.thoughtworks.merchant.interfaces.ListWriter;
import com.thoughtworks.merchant.interfaces.RomanNumerals;

public class Factory {
	
	private static ListManager inputLinesListManagerObject;
	private static ListManager outputLinesListManagerObject;
	private static ListManager logsListManagerObject;
	private static Line invalidLineTypeObject;
	private static AliasMap aliasMapObject;
	private static CommodityMap commodityMapObject;
	private static CommodityCalculator commodityCalculatorObject;
	private static RomanNumerals romanNumeralsObject;
	private static GalacticNumerals galacticNumeralsObject;

	public static MerchantsNotesProcessor createMerchantsNotesProcessor() {

		ListReader inputLinesReader = Factory.getInputLinesReaderObject();
		ListWriter inputLinesWriter = Factory.getInputLinesWriterObject();
		ListWriter outputLinesWriter = Factory.getOutputLinesWriterObject();
		ListWriter logWriter = Factory.getLogWriterObject();

		MerchantsNotesProcessor merchantsNotesProcessor = new MerchantsNotesProcessor(inputLinesReader,
				inputLinesWriter, outputLinesWriter, logWriter);
		return merchantsNotesProcessor;
	}

	// Based on the format of the line, return an appropriate object of type Line
	public static Line getLineObject(String line) {

		// Get the mapping of class names and regex, for each of the line types
		HashMap<String, String> lineTypesMap = ConfigPropertiesManager.getLineTypesMap();

		// If line does not match with any of the regex, then by default it will be considered of invalid type
		Line lineObject = getInvalidLineTypeObject();

		Class<?> classObject;

		// For each of the line type, try to match this line with its corresponding regex
		// and if it matches, instantiate an object of the corresponding class
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

	// We need to instantiate ony one instance of this object
	public static Line getInvalidLineTypeObject() {
		
		if (invalidLineTypeObject != null){
			return invalidLineTypeObject;
		}

		String invalidLineTypeClassName = ConfigPropertiesManager.getInvalidLineTypeClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(invalidLineTypeClassName);
			Constructor<?> constructor = classObject.getConstructor(String.class, String.class);
			invalidLineTypeObject = (Line) constructor.newInstance("", "");
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return invalidLineTypeObject;
	}

	public static ListReader getInputLinesReaderObject() {

		ListReader inputLinesReaderObject = null;

		String inputLinesReaderClassName = ConfigPropertiesManager.getInputLinesReaderClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(inputLinesReaderClassName);
			Constructor<?> constructor = classObject.getConstructor();
			inputLinesReaderObject = (ListReader) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return inputLinesReaderObject;
	}

	public static ListWriter getInputLinesWriterObject() {

		ListWriter inputLinesWriterObject = null;

		String inputLinesWriterClassName = ConfigPropertiesManager.getInputLinesWriterClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(inputLinesWriterClassName);
			Constructor<?> constructor = classObject.getConstructor();
			inputLinesWriterObject = (ListWriter) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return inputLinesWriterObject;
	}

	public static ListWriter getOutputLinesWriterObject() {

		ListWriter outputLinesWriterObject = null;

		String outputLinesWriterClassName = ConfigPropertiesManager.getOutputLinesWriterClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(outputLinesWriterClassName);
			Constructor<?> constructor = classObject.getConstructor();
			outputLinesWriterObject = (ListWriter) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return outputLinesWriterObject;
	}

	public static ListWriter getLogWriterObject() {

		ListWriter logWriterObject = null;

		String logWriterClassName = ConfigPropertiesManager.getLogWriterClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(logWriterClassName);
			Constructor<?> constructor = classObject.getConstructor();
			logWriterObject = (ListWriter) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return logWriterObject;
	}

	// We need to instantiate ony one instance of this object
	public static AliasMap getAliasMapObject() {
		
		if (aliasMapObject != null){
			return aliasMapObject;
		}

		String aliasMapClassName = ConfigPropertiesManager.getAliasMapClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(aliasMapClassName);
			Constructor<?> constructor = classObject.getConstructor();
			aliasMapObject = (AliasMap) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return aliasMapObject;
	}

	// We need to instantiate ony one instance of this object
	public static CommodityMap getCommodityMapObject() {
		
		if (commodityMapObject != null){
			return commodityMapObject;
		}
		
		String commodityMapClassName = ConfigPropertiesManager.getCommodityMapClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(commodityMapClassName);
			Constructor<?> constructor = classObject.getConstructor();
			commodityMapObject = (CommodityMap) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return commodityMapObject;
	}

	// We need to instantiate ony one instance of this object
	public static CommodityCalculator getCommodityCalculatorObject() {
		
		if (commodityCalculatorObject != null){
			return commodityCalculatorObject;
		}

		String commodityCalculatorClassName = ConfigPropertiesManager.getCommodityCalculatorClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(commodityCalculatorClassName);
			Constructor<?> constructor = classObject.getConstructor();
			commodityCalculatorObject = (CommodityCalculator) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return commodityCalculatorObject;
	}

	// We need to instantiate ony one instance of this object
	public static RomanNumerals getRomanNumeralsObject() {
		
		if (romanNumeralsObject != null){
			return romanNumeralsObject;
		}
		
		String romanNumeralsClassName = ConfigPropertiesManager.getRomanNumeralsClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(romanNumeralsClassName);
			Constructor<?> constructor = classObject.getConstructor();
			romanNumeralsObject = (RomanNumerals) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return romanNumeralsObject;
	}

	// We need to instantiate ony one instance of this object
	public static GalacticNumerals getGalacticNumeralsObject() {
		
		if (galacticNumeralsObject != null){
			return galacticNumeralsObject;
		}

		String galacticNumeralsClassName = ConfigPropertiesManager.getGalacticNumeralsClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(galacticNumeralsClassName);
			Constructor<?> constructor = classObject.getConstructor();
			galacticNumeralsObject = (GalacticNumerals) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return galacticNumeralsObject;
	}

	// We need to instantiate ony one instance of this object
	public static ListManager getInputLinesListManagerObject() {
		
		if (inputLinesListManagerObject != null){
			return inputLinesListManagerObject;
		}

		String listManagerClassName = ConfigPropertiesManager.getListManagerClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(listManagerClassName);
			Constructor<?> constructor = classObject.getConstructor();
			inputLinesListManagerObject = (ListManager) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return inputLinesListManagerObject;
	}

	// We need to instantiate ony one instance of this object
	public static ListManager getOutputLinesListManagerObject() {
		
		if (outputLinesListManagerObject != null){
			return outputLinesListManagerObject;
		}

		String listManagerClassName = ConfigPropertiesManager.getListManagerClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(listManagerClassName);
			Constructor<?> constructor = classObject.getConstructor();
			outputLinesListManagerObject = (ListManager) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return outputLinesListManagerObject;
	}

	// We need to instantiate ony one instance of this object
	public static ListManager getLogsListManagerObject() {
		
		if (logsListManagerObject != null){
			return logsListManagerObject;
		}

		String listManagerClassName = ConfigPropertiesManager.getListManagerClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(listManagerClassName);
			Constructor<?> constructor = classObject.getConstructor();
			logsListManagerObject = (ListManager) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return logsListManagerObject;
	}

}
