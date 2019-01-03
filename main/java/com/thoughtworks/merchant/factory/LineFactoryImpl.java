package com.thoughtworks.merchant.factory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.interfaces.Line;
import com.thoughtworks.merchant.interfaces.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.factory.GeneralFactory;
import com.thoughtworks.merchant.interfaces.factory.LineFactory;

public class LineFactoryImpl implements LineFactory {

	private GeneralFactory generalFactory; 
	private ConfigPropertiesManager configPropertiesManager;

	// Based on the format of the line, return an appropriate object of type
	// Line
	@Override
	public Line getLineObject(String line) {

		// If line does not match with any of the regex, then by default it will
		// be considered of invalid type
		Line lineObject = (Line) generalFactory.getObject("InvalidLineType");

		int numberOfLineTypes = Integer.parseInt(configPropertiesManager.getPropertyValue("NumberOfLineTypes"));

		String objectName = "";
		String regex = "";

		// For each of the line type, try to match this line with its
		// corresponding regex
		// and if it matches, instantiate an object of the corresponding class
		for (int i = 0; i < numberOfLineTypes; i++) {
			objectName = "LineType" + (i + 1);
			regex = configPropertiesManager.getPropertyValue("LineTypeRegex" + (i + 1));

			Pattern ptn = Pattern.compile(regex);
			Matcher mcher = ptn.matcher(line);
			if (mcher.matches()) {
				lineObject = (Line) generalFactory.getObject(objectName);
				lineObject.setLine(line);
				lineObject.setRegex(regex);
			}
		}
		
		//For testing
		//System.out.println(lineObject);

		return lineObject;
	}

	public void setConfigPropertiesManager(Object configPropertiesManager) {
		this.configPropertiesManager = (ConfigPropertiesManager) configPropertiesManager;
	}
	
	public void setGeneralFactory(Object generalFactory) {
		this.generalFactory = (GeneralFactory) generalFactory;
	}
}
