package com.thoughtworks.merchant.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.interfaces.ConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.Factory;
import com.thoughtworks.merchant.interfaces.Line;

public class FactoryImpl implements Factory {

	private static HashMap<String, Object> objectMap = new HashMap<String, Object>();
	
	private static ConfigPropertiesManager configPropertiesManager = new FileConfigPropertiesManager();

	// Based on the format of the line, return an appropriate object of type
	// Line
	@Override
	public Line getLineObject(String line) {

		// If line does not match with any of the regex, then by default it will
		// be considered of invalid type
		Line lineObject = (Line) getObject("InvalidLineType");

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
				lineObject = (Line) getObject(objectName);
				lineObject.setLine(line);
				lineObject.setRegex(regex);
			}
		}
		
		//For testing
		//System.out.println(lineObject);

		return lineObject;
	}

	@Override
	public Object getObject(String objectName) {

		Object object = null;
		
		String objectScope = configPropertiesManager.getPropertyValue(objectName + "Scope");
		
		if (!objectScope.equals("non-singleton") && objectMap.containsKey(objectName)) {
			object = objectMap.get(objectName);
		} else {

			String className = configPropertiesManager.getPropertyValue(objectName);

			Class<?> classObject = null;

			try {
				classObject = Class.forName(className);
				Constructor<?> constructor = classObject.getConstructor();
				object = constructor.newInstance();
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
			//Check for dependencies
			int numberOfDependencies = 0;
			String numberOfDependenciesStr = configPropertiesManager.getPropertyValue(objectName + "NumOfDependencies");
			if (!numberOfDependenciesStr.isEmpty()){
				numberOfDependencies = Integer.parseInt(numberOfDependenciesStr);
			}
			
			//For each dependency, set the dependency
			Object depObject = null;
			for (int i = 0; i < numberOfDependencies; i++) {
				String depObjectName = configPropertiesManager.getPropertyValue(objectName + "Dependency" + (i + 1));

				depObject = getObject(depObjectName);
				//For testing
				//System.out.println(depObject);
				try {
					classObject.getMethod("set" + depObjectName, Object.class).invoke(object, depObject);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}

			if (!objectScope.equals("non-singleton")) {
				objectMap.put(objectName, object);
			}
		}
		
		//For testing
		//System.out.println(objectMap);

		return object;
	}
}
