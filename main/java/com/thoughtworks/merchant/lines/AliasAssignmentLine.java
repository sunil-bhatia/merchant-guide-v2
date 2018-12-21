package com.thoughtworks.merchant.lines;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.merchant.computations.AliasMap;
import com.thoughtworks.merchant.factory.Factory;

// Example Alias Assignment Line: "glob is I"
public class AliasAssignmentLine implements Line {

	private String line;	
	private String regex;
	
	String galacticSymbol;
	Character romanSymbol;
	
	public AliasAssignmentLine(String line, String regex) {
		this.line = line;
		this.regex = regex;
	}

	@Override
	public void process() {

		// Delegate to parse method for parsing the line
		parse();

		// Add Galactic Roman Mapping to the Alias Map
		AliasMap aliasMap = Factory.getAliasMapObject();
		aliasMap.addMapping(galacticSymbol, romanSymbol);
	}
	
	// Parse this line and extract the two pieces of information
	// galacticSymbol = "glob"
	// romanSymbol = 'I'
	private void parse() {
		
		Pattern ptn = Pattern.compile(regex);

		Matcher mcher = ptn.matcher(line);
		mcher.matches();

		galacticSymbol = mcher.group(1).trim();
		romanSymbol = mcher.group(2).trim().charAt(0);
	}
}
