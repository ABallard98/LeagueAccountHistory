package com.aydenballard.demo;

import java.io.File;
import java.util.Scanner;

public class FileReader {

    /**
     * Method to get the API key from text file
     * @return String - API key
     */
    public static String readApiKey() {
        final String FILE_NAME = "src/main/resources/api_key.txt";
        try{
            Scanner in = new Scanner(new File(FILE_NAME));
            return in.nextLine();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
