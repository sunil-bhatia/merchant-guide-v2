package com.thoughtworks.merchant.interfaces;

public interface GenericFactory {

	public Object getObject(String objectName);
	
	public void setConfigPropertiesManager(Object configPropertiesManager);
}
