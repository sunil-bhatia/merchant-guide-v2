package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.interfaces.AliasMap;

// Example Alias Assignment Line: "glob is I"
public class AliasAssignmentLine extends GenericLine {

	private String galacticSymbol;
	private Character romanSymbol;
	
	public AliasAssignmentLine(String line, String regex) {
		super(line, regex);
	}
	
	// Parse this line and extract the two pieces of information
	// galacticSymbol = "glob"
	// romanSymbol = 'I'
	protected void parse() {
		
		isAssignmentLine = true;
		
		Pattern ptn = Pattern.compile(regex);

		Matcher mcher = ptn.matcher(line);
		mcher.matches();

		galacticSymbol = mcher.group(1).trim();
		romanSymbol = mcher.group(2).trim().charAt(0);
	}
	
	protected void addAssignedData(){
		// Add Galactic Roman Mapping to the Alias Map
		AliasMap aliasMap = Factory.getAliasMapObject();
		aliasMap.addMapping(galacticSymbol, romanSymbol);
	}
}
