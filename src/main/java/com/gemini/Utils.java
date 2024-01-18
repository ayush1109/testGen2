package com.gemini;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Utils {
    public static String readProperties(String property) throws IOException { // Function to read Data from Properties File
        FileReader read = new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
        Properties credential = new Properties();
        credential.load(read);
        return credential.getProperty(property);
    }
}
