package com.thoughtworks.merchant.interfaces;

public interface Line {

	public String process();
	
	public void setLine(String line);
	public void setRegex(String regex);
}
