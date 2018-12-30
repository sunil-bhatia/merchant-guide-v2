package com.thoughtworks.merchant.iomanagers;

import java.util.List;

import com.thoughtworks.merchant.interfaces.ListWriter;

public class ConsoleListWriter implements ListWriter {
	
	@Override
	public void write(List<String> list, String title) {
        System.out.println();
        System.out.println(title);
        System.out.println();
        for (String line : list) {
            System.out.println(line);
        }
	}
}
