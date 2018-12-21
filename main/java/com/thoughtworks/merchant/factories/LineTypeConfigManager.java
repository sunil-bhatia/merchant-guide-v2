package com.thoughtworks.merchant.factories;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LineTypeConfigManager {
	
	private static HashMap<String, String> lineTypesMap = new HashMap<String, String>();

	public static HashMap<String, String> configureLineTypesMap(String[] args){
		
		 List<String> lineRegexConfigs = new ArrayList<>();
		 List<String> lineClassNameConfigs = new ArrayList<>();
		        
        try {
            Path path = Paths.get(args[1]);
            lineRegexConfigs = Files.readAllLines(path, Charset.forName("utf-8"));
            
            path = Paths.get(args[2]);
            lineClassNameConfigs = Files.readAllLines(path, Charset.forName("utf-8"));

        } catch (IOException e) {
            System.err.println("Unable to read Line Type Configuration Files: " + e.getMessage());
            System.exit(1);
        }
        
        String regex = "";
        String className = "";
        
		for (int i = 0; i < lineRegexConfigs.size(); i++) {
			regex = lineRegexConfigs.get(i);
			className = lineClassNameConfigs.get(i);
			lineTypesMap.put(regex, className);
		}
		
		return lineTypesMap;
	}
	
	
	public static HashMap<String, String> getLineTypesMap() {
		return lineTypesMap;
	}
	
}
