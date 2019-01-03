package com.thoughtworks.merchant.interfaces.factory;

public interface GeneralFactory {

	public Object getObject(String objectName);
	
	public void setConfigPropertiesManager(Object configPropertiesManager);
}
