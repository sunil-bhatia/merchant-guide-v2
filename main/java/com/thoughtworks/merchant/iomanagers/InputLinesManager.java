package com.thoughtworks.merchant.iomanagers;

import java.util.List;

public interface InputLinesManager {
	
	public List<String> getInputLines(String[] args);
    public void printInput();
    
}
