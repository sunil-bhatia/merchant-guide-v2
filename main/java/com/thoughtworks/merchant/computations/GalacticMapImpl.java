package com.thoughtworks.merchant.computations;

import java.util.HashMap;

import com.thoughtworks.merchant.interfaces.GalacticMap;

//This class maintains the mapping between galactic symbols and roman symbols
//This mapping was obtained from the Galactic Assignment Input Lines like: "glob is I"
//Example Galactic Map : {tegj=L, glob=I, prok=V, pish=X}
public class GalacticMapImpl implements GalacticMap {

	private static HashMap<String, Character> galacticMap = new HashMap<String, Character>();

	// Example: galacticSymbol = "glob"
	// 			romanSymbol = 'I'
	public void addMapping(String galacticSymbol, Character romanSymbol) {
		galacticMap.put(galacticSymbol, romanSymbol);
	}

	public boolean isValidGalacticSymbol(String galacticSymbol){
		boolean isValid;
		if (galacticMap.containsKey(galacticSymbol)){
			isValid = true;
		} else {
			isValid = false;
		}
		return isValid;
	}
	
	public Character getRomanSymbol(String galacticSymbol){
		return galacticMap.get(galacticSymbol);
	}
}
