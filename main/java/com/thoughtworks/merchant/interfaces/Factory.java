package com.thoughtworks.merchant.interfaces;

import com.thoughtworks.merchant.interfaces.Line;

public interface Factory {

	public Line getLineObject(String line);

	public Object getObject(String objectName);
	
	public void setConfigPropertiesManager(Object configPropertiesManager);
}
