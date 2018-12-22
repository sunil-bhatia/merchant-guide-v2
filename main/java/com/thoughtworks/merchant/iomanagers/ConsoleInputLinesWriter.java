package com.thoughtworks.merchant.iomanagers;

import java.util.List;

import com.thoughtworks.merchant.factory.Factory;
import com.thoughtworks.merchant.lines.listmanagers.ListManager;

public class ConsoleInputLinesWriter implements ListWriter {
	
	@Override
    public void write() {
    	
    	//Get input lines list from manager
        ListManager inputLinesListManager = Factory.getInputLinesListManagerObject();
    	List<String> lines = inputLinesListManager.getList();
    			
        System.out.println();
        System.out.println("Input text:");
        System.out.println();
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
