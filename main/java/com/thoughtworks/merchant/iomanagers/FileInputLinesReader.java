package com.thoughtworks.merchant.iomanagers;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.merchant.interfaces.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.iomanagers.ListReader;

public class FileInputLinesReader implements ListReader {
	
	private ConfigPropertiesManager configPropertiesManager;
	
	@Override
	public List<String> read(){
		
		List<String> inputLines = new ArrayList<>();
		
		String inputLinesFileName = configPropertiesManager.getPropertyValue("InputLinesFileName");
		      
        try {
            Path path = Paths.get(inputLinesFileName);
            inputLines = Files.readAllLines(path, Charset.forName("utf-8"));

        } catch (IOException e) {
            System.err.println("Unable to read input file: " + e.getMessage());
            System.err.println("Please try again. ");
            System.exit(1);
        }
              
        return inputLines;
	}
	
	public void setConfigPropertiesManager(Object configPropertiesManager) {
		this.configPropertiesManager = (ConfigPropertiesManager) configPropertiesManager;
	}
}
