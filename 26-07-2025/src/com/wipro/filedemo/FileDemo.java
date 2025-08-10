package com.wipro.filedemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileDemo {

    public static void main(String[] args) throws FileNotFoundException {
        String inputFileName = "C:\\Users\\delld\\OneDrive\\Desktop\\OIP.jpeg";
        String outputFileName = "C:\\Users\\delld\\OneDrive\\Desktop\\OIPout.jpeg";

        try (
            FileInputStream fi = new FileInputStream(inputFileName);
            FileOutputStream fo = new FileOutputStream(outputFileName)
        ) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            
       while ((bytesRead = fi.read(buffer)) != -1) {
                fo.write(buffer, 0, bytesRead);
            }

            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
