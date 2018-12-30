package com.thoughtworks.merchant.iomanagers;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.merchant.interfaces.LogManager;

public abstract class GenericLogManager implements LogManager {

	protected List<String> logs = new ArrayList<>();

	@Override
	public void addLog(String log) {
		logs.add(log);
	}

}
