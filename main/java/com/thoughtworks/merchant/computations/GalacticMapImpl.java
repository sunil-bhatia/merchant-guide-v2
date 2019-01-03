package com.thoughtworks.merchant.computations;

import java.util.HashMap;

import com.thoughtworks.merchant.interfaces.computations.GalacticMap;

//This class maintains the mapping between galactic symbols and roman symbols
public class GalacticMapImpl implements GalacticMap {

	private static HashMap<String, Character> galacticMap = new HashMap<String, Character>();

	@Override
	public void addMapping(String galacticSymbol, Character romanSymbol) {
		galacticMap.put(galacticSymbol, romanSymbol);
	}

	@Override
	public boolean isGalacticSymbolValid(String galacticSymbol){
		return galacticMap.containsKey(galacticSymbol);
	}
	
	@Override
	public Character getRomanSymbol(String galacticSymbol){
		return galacticMap.get(galacticSymbol);
	}
}
