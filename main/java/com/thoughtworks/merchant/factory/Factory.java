package com.thoughtworks.merchant.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.MerchantsNotesProcessor;
import com.thoughtworks.merchant.computations.AliasMap;
import com.thoughtworks.merchant.computations.CommodityCalculator;
import com.thoughtworks.merchant.computations.CommodityMap;
import com.thoughtworks.merchant.computations.GalacticNumerals;
import com.thoughtworks.merchant.computations.RomanNumerals;
import com.thoughtworks.merchant.iomanagers.ConfigPropertiesManager;
import com.thoughtworks.merchant.iomanagers.InputLinesReader;
import com.thoughtworks.merchant.iomanagers.InputLinesWriter;
import com.thoughtworks.merchant.iomanagers.LogWriter;
import com.thoughtworks.merchant.iomanagers.OutputLinesWriter;
import com.thoughtworks.merchant.lines.Line;
import com.thoughtworks.merchant.lines.listmanagers.InputLinesListManager;
import com.thoughtworks.merchant.lines.listmanagers.LogsListManager;
import com.thoughtworks.merchant.lines.listmanagers.OutputLinesListManager;

public class Factory {

	public static MerchantsNotesProcessor createMerchantsNotesProcessor() {

		InputLinesReader inputLinesReader = Factory.getInputLinesReaderObject();
		InputLinesWriter inputLinesWriter = Factory.getInputLinesWriterObject();
		OutputLinesWriter outputLinesWriter = Factory.getOutputLinesWriterObject();
		LogWriter logWriter = Factory.getLogWriterObject();

		MerchantsNotesProcessor merchantsNotesProcessor = new MerchantsNotesProcessor(inputLinesReader,
				inputLinesWriter, outputLinesWriter, logWriter);
		return merchantsNotesProcessor;
	}

	public static Line getLineObject(String line) {

		HashMap<String, String> lineTypesMap = ConfigPropertiesManager.getLineTypesMap();

		Line lineObject = getInvalidLineTypeObject();

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

	public static Line getInvalidLineTypeObject() {

		Line invalidLineTypeObject = null;

		String invalidLineTypeClassName = ConfigPropertiesManager.getInvalidLineTypeClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(invalidLineTypeClassName);
			Constructor<?> constructor = classObject.getConstructor();
			invalidLineTypeObject = (Line) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return invalidLineTypeObject;
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

	public static OutputLinesWriter getOutputLinesWriterObject() {

		OutputLinesWriter outputLinesWriterObject = null;

		String outputLinesWriterClassName = ConfigPropertiesManager.getOutputLinesWriterClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(outputLinesWriterClassName);
			Constructor<?> constructor = classObject.getConstructor();
			outputLinesWriterObject = (OutputLinesWriter) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return outputLinesWriterObject;
	}

	public static LogWriter getLogWriterObject() {

		LogWriter logWriterObject = null;

		String logWriterClassName = ConfigPropertiesManager.getLogWriterClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(logWriterClassName);
			Constructor<?> constructor = classObject.getConstructor();
			logWriterObject = (LogWriter) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return logWriterObject;
	}

	public static AliasMap getAliasMapObject() {
		
		AliasMap aliasMapObject = null;

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

	public static CommodityMap getCommodityMapObject() {
		
		CommodityMap commodityMapObject = null;

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

	public static CommodityCalculator getCommodityCalculatorObject() {
		
		CommodityCalculator commodityCalculatorObject = null;

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

	public static RomanNumerals getRomanNumeralsObject() {
		
		RomanNumerals romanNumeralsObject = null;

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

	public static GalacticNumerals getGalacticNumeralsObject() {
		
		GalacticNumerals galacticNumeralsObject = null;

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

	public static InputLinesListManager getInputLinesListManagerObject() {
		
		InputLinesListManager inputLinesListManagerObject = null;

		String inputLinesListManagerClassName = ConfigPropertiesManager.getInputLinesListManagerClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(inputLinesListManagerClassName);
			Constructor<?> constructor = classObject.getConstructor();
			inputLinesListManagerObject = (InputLinesListManager) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return inputLinesListManagerObject;
	}

	public static OutputLinesListManager getOutputLinesListManagerObject() {
		
		OutputLinesListManager outputLinesListManagerObject = null;

		String outputLinesListManagerClassName = ConfigPropertiesManager.getOutputLinesListManagerClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(outputLinesListManagerClassName);
			Constructor<?> constructor = classObject.getConstructor();
			outputLinesListManagerObject = (OutputLinesListManager) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return outputLinesListManagerObject;
	}

	public static LogsListManager getLogsListManagerObject() {
		
		LogsListManager logsListManagerObject = null;

		String logsListManagerClassName = ConfigPropertiesManager.getLogsListManagerClassName();

		Class<?> classObject;

		try {
			classObject = Class.forName(logsListManagerClassName);
			Constructor<?> constructor = classObject.getConstructor();
			logsListManagerObject = (LogsListManager) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return logsListManagerObject;
	}

}
