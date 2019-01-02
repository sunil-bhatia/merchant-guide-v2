package com.thoughtworks.merchant.interfaces;

public interface GalacticMap {

	public void addMapping(String galacticSymbol, Character romanSymbol);

	public boolean isGalacticSymbolValid(String galacticSymbol);

	public Character getRomanSymbol(String galacticSymbol);
}
