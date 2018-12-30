package com.thoughtworks.merchant.interfaces;

//This interface defines methods to access the mapping between galactic symbols and roman symbols
//This mapping was obtained from the Galactic Assignment Input Lines like: "glob is I"
//Example Galactic Map : {tegj=L, glob=I, prok=V, pish=X}
public interface GalacticMap {

	public void addMapping(String galacticSymbol, Character romanSymbol);

	public boolean isValidGalacticSymbol(String galacticSymbol);

	public Character getRomanSymbol(String galacticSymbol);

}
