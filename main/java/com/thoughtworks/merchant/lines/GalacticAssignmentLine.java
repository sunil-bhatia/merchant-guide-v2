package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.GalacticMap;

// Example Galactic Assignment Line: "glob is I"
public class GalacticAssignmentLine extends AssignmentLine {

	private String galacticSymbol;
	private Character romanSymbol;
	
	private GalacticMap galacticMap = (GalacticMap) Factory.getObject("galacticMap");
	
	public GalacticAssignmentLine() {
		super();
	}
	
	// Example: galacticSymbol = "glob"
	// 			romanSymbol = 'I'
	protected void extractData(Matcher mcher) {
		galacticSymbol = mcher.group(1).trim();
		romanSymbol = mcher.group(2).trim().charAt(0);
	}
	
	protected void addAssignedData(){
		galacticMap.addMapping(galacticSymbol, romanSymbol);
	}
}
