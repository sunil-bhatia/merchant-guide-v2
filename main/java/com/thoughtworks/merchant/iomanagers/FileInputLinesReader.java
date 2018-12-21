package com.thoughtworks.merchant.iomanagers;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.merchant.lines.listmanagers.InputLinesListManager;

public class FileInputLinesReader implements InputLinesReader {
	
	@Override
	public List<String> readInputLines(){
		
		List<String> inputLines = new ArrayList<>();
		
		String inputLinesFileName = ConfigPropertiesManager.getInputLinesFileName();
		      
        try {
            Path path = Paths.get(inputLinesFileName);
            inputLines = Files.readAllLines(path, Charset.forName("utf-8"));

        } catch (IOException e) {
            System.err.println("Unable to read input file: " + e.getMessage());
            System.err.println("Please try again. ");
            System.exit(1);
        }
        
        InputLinesListManager.setInputLines(inputLines);
        
        return inputLines;
	}
}
