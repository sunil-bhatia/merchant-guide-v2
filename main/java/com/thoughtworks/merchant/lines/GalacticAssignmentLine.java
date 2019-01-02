package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;

import com.thoughtworks.merchant.factory.FactoryImpl;
import com.thoughtworks.merchant.interfaces.Factory;
import com.thoughtworks.merchant.interfaces.GalacticMap;

// Example Galactic Assignment Line: "glob is I"
public class GalacticAssignmentLine extends AssignmentLine {

	private String galacticSymbol;
	private Character romanSymbol;
	
	Factory factory = new FactoryImpl();
	private GalacticMap galacticMap = (GalacticMap) factory.getObject("GalacticMap");
	
	public GalacticAssignmentLine() {
		super();
	}
	
	@Override
	protected void extractData(Matcher mcher) {
		galacticSymbol = mcher.group(1).trim();
		romanSymbol = mcher.group(2).trim().charAt(0);
	}
	
	@Override
	protected boolean isDataValid() {
		return true;
	}
	
	@Override
	protected void addAssignedData(){
		galacticMap.addMapping(galacticSymbol, romanSymbol);
	}

	@Override
	protected void calculateAssignedData() {
		// Empty method, as nothing to be calculated
	}
}
