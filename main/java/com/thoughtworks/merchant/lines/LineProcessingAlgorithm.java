package com.thoughtworks.merchant.lines;

public interface LineProcessingAlgorithm {

	public void parse();
	
	public void calculate();
	
	public void save();
  
}
