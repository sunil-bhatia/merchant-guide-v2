package com.thoughtworks.merchant.interfaces.factory;

public interface ConfigPropertiesManager {

	public void readConfigProperties(String[] args);
	
	public String getPropertyValue(String propertyName);
	
	public GeneralFactory getGeneralFactoryObject();
}
