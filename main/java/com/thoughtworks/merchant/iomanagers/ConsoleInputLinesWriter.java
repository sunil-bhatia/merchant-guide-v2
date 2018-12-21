package com.thoughtworks.merchant.iomanagers;

import java.util.List;

import com.thoughtworks.merchant.lines.InputLinesListManager;

public class ConsoleInputLinesWriter implements InputLinesWriter {
	
    public void writeInput() {
    	
    	//Get input lines list from manager
    	List<String> lines = InputLinesListManager.getInputLines();
    			
        System.out.println();
        System.out.println("Input text:");
        System.out.println();
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
