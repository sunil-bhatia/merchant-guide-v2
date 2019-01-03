package com.thoughtworks.merchant.interfaces.computations;

public interface GalacticMap {

	public void addMapping(String galacticSymbol, Character romanSymbol);

	public Character getRomanSymbol(String galacticSymbol);
	
	public boolean isGalacticSymbolValid(String galacticSymbol);
}
