package com.thoughtworks.merchant.factory;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.thoughtworks.merchant.interfaces.ConfigPropertiesManager;

public class FileConfigPropertiesManager implements ConfigPropertiesManager {

	private static ResourceBundle rb = null;
	
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

	public String getPropertyValue(String propertyName) {
		return rb.getString(propertyName);
	}
}
