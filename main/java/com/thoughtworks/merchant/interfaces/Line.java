package com.thoughtworks.merchant.interfaces;

public interface Line {

	public void process();
	
	public void setLine(String line);
	public void setRegex(String regex);
}
