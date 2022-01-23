package com.logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class Driver {

    public static void tranferTextFromCurrentFileIntoMemory() {
        try {
            String filePath = DataManager.getCurrentFile().getAbsolutePath();
            String textToTransfer = Files.readString(Path.of(filePath));
            DataManager.setCurrentText(textToTransfer);
        } catch (IOException e) {            
            e.printStackTrace();
        }
    }

    public static void tranferTextFromMemoryIntoCurrentFile() {
        try {
            FileWriter fileWriter = new FileWriter(DataManager.getCurrentFile());
            fileWriter.append(DataManager.getCurrentText());
            fileWriter.close();            
        } catch (IOException e) {            
            e.printStackTrace();
        }
    }


    
}
