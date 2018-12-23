package com.thoughtworks.merchant.computations;

import java.util.HashMap;

import com.thoughtworks.merchant.interfaces.AliasMap;

//This class maintains the mapping between galactic symbols and roman symbols
//This mapping was obtained from the Alias Assignment Input Lines like: "glob is I"
//Example Alias Map : {tegj=L, glob=I, prok=V, pish=X}
public class AliasMapImpl implements AliasMap {

	private static HashMap<String, Character> aliasMap = new HashMap<String, Character>();

	// Example: galacticSymbol = "glob"
	// 			romanSymbol = 'I'
	public void addMapping(String galacticSymbol, Character romanSymbol) {
		aliasMap.put(galacticSymbol, romanSymbol);
	}

	public boolean isValidGalacticSymbol(String galacticSymbol){
		boolean isValid;
		if (aliasMap.containsKey(galacticSymbol)){
			isValid = true;
		} else {
			isValid = false;
		}
		return isValid;
	}
	
	public Character getRomanSymbol(String galacticSymbol){
		return aliasMap.get(galacticSymbol);
	}
	
	public HashMap<String, Character> getAliasMap() {
		return aliasMap;
	}
}
