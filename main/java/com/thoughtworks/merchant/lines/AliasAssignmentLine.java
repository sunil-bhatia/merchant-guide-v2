package com.thoughtworks.merchant.lines;

// Example Alias Assignment Line: "glob is I"
public class AliasAssignmentLine extends GenericLine {

	private String galacticSymbol;
	private Character romanSymbol;
	
	public AliasAssignmentLine(String line, String regex) {
		super(line, regex);
	}
	
	// Example: galacticSymbol = "glob"
	// 			romanSymbol = 'I'
	protected void extractData() {
		galacticSymbol = mcher.group(1).trim();
		romanSymbol = mcher.group(2).trim().charAt(0);
	}
	
	protected void determineLineType() {
		isAssignmentLine = true;
	}
	
	protected void addAssignedData(){
		aliasMap.addMapping(galacticSymbol, romanSymbol);
	}
}
