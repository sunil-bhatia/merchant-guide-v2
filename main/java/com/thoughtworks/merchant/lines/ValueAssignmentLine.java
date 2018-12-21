package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.computations.CommodityCalculator;
import com.thoughtworks.merchant.computations.CommodityMapManager;

//Example Value Assignment Line: "glob glob Silver is 34 Credits"
public class ValueAssignmentLine implements Line {

	private String line;	
	private String regex;
	
	String commodity;
	String qtyGalactic;
	int value;
	
	public ValueAssignmentLine(String line, String regex) {
		this.line = line;
		this.regex = regex;
	}

	@Override
	public void process() {

		// Delegate to parse method for parsing the line
        parse();
        
        // Delegate to Commodity Calculator for calculating the value per unit quantity for the commodity
		double valuePerUnit = CommodityCalculator.calculateValuePerUnit(value, qtyGalactic);
        
    	// Add commodity and valuePerUnit to the Commodity Value Map 
        CommodityMapManager.addValuePerUnit(commodity, valuePerUnit);
	}
	
	// Parse this line and extract the three pieces of information
	// qtyGalactic = "glob glob"
	// commodity = "Silver"
	// value = 34
	private void parse() {
		
		Pattern ptn = Pattern.compile(regex);
		
		Matcher mcher = ptn.matcher(line);
		mcher.matches();

		qtyGalactic = mcher.group(1);
		commodity = mcher.group(2);
		value = Integer.parseInt(mcher.group(3).trim());
	}
}
