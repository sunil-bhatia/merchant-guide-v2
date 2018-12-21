package com.thoughtworks.merchant.iomanagers;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// This application accepts an input file. The input file
// path is provided as the first command line argument.
public class FileInputLinesManager implements InputLinesManager {
	
	private static List<String> lines = new ArrayList<>();
	
	public FileInputLinesManager() {
	}
	
	public List<String> getInputLines(String[] args){
		
        if (args == null || args.length == 0) {
            System.err.println("Please enter the path to the input file as a program argument, e.g.");
            System.err.println("java -jar MerchantGuide.jar classes\\input.txt");
            System.exit(1);
        }
        
        try {
            Path path = Paths.get(args[0]);
            lines = Files.readAllLines(path, Charset.forName("utf-8"));

        } catch (IOException e) {
            System.err.println("Unable to read input file: " + e.getMessage());
            System.err.println("Please try again. ");
            System.exit(1);
        }
		
		return lines;
	}
	
    public void printInput() {
        System.out.println();
        System.out.println("Input text:");
        System.out.println();
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
