package com.thoughtworks.merchant.interfaces;

public interface ConfigPropertiesManager {

	public void configureProperties(String[] args);
	
	public String getPropertyValue(String propertyName);
	
	public Factory getFactoryObject();
}
