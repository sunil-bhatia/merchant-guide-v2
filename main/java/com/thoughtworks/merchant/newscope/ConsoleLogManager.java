package com.thoughtworks.merchant.newscope;

import com.thoughtworks.merchant.iomanagers.GenericLogManager;

public class ConsoleLogManager extends GenericLogManager {

	@Override
	public void printLog() {
		
        System.out.println();
        System.out.println("Logs:");
        System.out.println();
        for (String log : logs) {
            System.out.println(log);
        }
	}
}
