package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.AliasMap;

// Example Alias Assignment Line: "glob is I"
public class AliasAssignmentLine extends GenericLine {

	private String galacticSymbol;
	private Character romanSymbol;
	
	private AliasMap aliasMap = (AliasMap) Factory.getObject("aliasMap");
	
	public AliasAssignmentLine() {
		super();
	}
	
	// Example: galacticSymbol = "glob"
	// 			romanSymbol = 'I'
	protected void extractData(Matcher mcher) {
		galacticSymbol = mcher.group(1).trim();
		romanSymbol = mcher.group(2).trim().charAt(0);
	}
	
	protected void addAssignedData(){
		aliasMap.addMapping(galacticSymbol, romanSymbol);
	}
}
