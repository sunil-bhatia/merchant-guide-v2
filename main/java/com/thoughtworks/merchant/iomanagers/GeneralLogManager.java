package com.thoughtworks.merchant.iomanagers;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.merchant.interfaces.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.iomanagers.LogManager;

public abstract class GeneralLogManager implements LogManager {
	
	protected ConfigPropertiesManager configPropertiesManager;

	protected List<String> logs = new ArrayList<>();

	@Override
	public void addLog(String log) {
		logs.add(log);
	}
	
	public void setConfigPropertiesManager(Object configPropertiesManager) {
		this.configPropertiesManager = (ConfigPropertiesManager) configPropertiesManager;
	}

}
