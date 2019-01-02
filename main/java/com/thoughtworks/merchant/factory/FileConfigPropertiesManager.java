package com.thoughtworks.merchant.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.thoughtworks.merchant.interfaces.ConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.GenericFactory;

public class FileConfigPropertiesManager implements ConfigPropertiesManager {

	private static ResourceBundle rb = null;
	
	@Override
	public void configureProperties(String[] args) {

		if (args == null || args.length == 0) {
			System.err.println("Please enter the name of the properties file as a program argument, e.g.");
			System.err.println("java -jar MerchantGuide.jar config");
			System.exit(1);
		}

		try {
			rb = ResourceBundle.getBundle(args[0]);
		} catch (MissingResourceException e) {
			System.err.println(e.getMessage());
			System.err.println("Please try again. ");
			System.exit(1);
		}
	}

	@Override
	public String getPropertyValue(String propertyName) {
		String propertyValue = "";
		
		if (rb.containsKey(propertyName)){
			propertyValue = rb.getString(propertyName);
		}
		
		return propertyValue;
	}
	
	@Override
	public GenericFactory getFactoryObject(){
		
		GenericFactory factoryObject = null;
		
		String className = getPropertyValue("GenericFactory");
		
		Class<?> classObject = null;

		try {
			classObject = Class.forName(className);
			Constructor<?> constructor = classObject.getConstructor();
			factoryObject = (GenericFactory) constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		factoryObject.setConfigPropertiesManager(this);
				
		return factoryObject;
	}
}
