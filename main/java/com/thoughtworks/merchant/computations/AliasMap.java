package com.thoughtworks.merchant.computations;

import java.util.HashMap;

//This interface defines methods to access the mapping between galactic symbols and roman symbols
//This mapping was obtained from the Alias Assignment Input Lines like: "glob is I"
//Example Alias Map : {tegj=L, glob=I, prok=V, pish=X}
public interface AliasMap {

	public void addMapping(String galacticSymbol, Character romanSymbol);

	public boolean isValidGalacticSymbol(String galacticSymbol);

	public Character getRomanSymbol(String galacticSymbol);

	public HashMap<String, Character> getAliasMap();

}
