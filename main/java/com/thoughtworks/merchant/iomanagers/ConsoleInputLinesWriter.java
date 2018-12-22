package com.thoughtworks.merchant.iomanagers;

import java.util.List;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.lines.listmanagers.InputLinesListManager;

public class ConsoleInputLinesWriter implements InputLinesWriter {
	
	@Override
    public void writeInput() {
    	
    	//Get input lines list from manager
        InputLinesListManager inputLinesListManager = Factory.getInputLinesListManagerObject();
    	List<String> lines = inputLinesListManager.getList();
    			
        System.out.println();
        System.out.println("Input text:");
        System.out.println();
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
