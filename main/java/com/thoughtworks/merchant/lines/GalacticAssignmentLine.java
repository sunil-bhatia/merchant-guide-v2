package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;

import com.thoughtworks.merchant.interfaces.computations.GalacticMap;

// Example Galactic Assignment Line: "glob is I"
public class GalacticAssignmentLine extends AssignmentLine {

	private String galacticSymbol;
	private Character romanSymbol;
	
	private GalacticMap galacticMap;
	
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

	public void setGalacticMap(Object galacticMap) {
		this.galacticMap = (GalacticMap) galacticMap;
	}
}
