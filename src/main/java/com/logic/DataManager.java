package com.logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class DataManager {

    private static File currentFile;
    private static String currentTextInMemory;
    private static String currentSearchPhrase;

    public static void tranferTextFromCurrentFileIntoMemory() {
        try {
            String filePath = currentFile.getAbsolutePath();
            String textToTransfer = Files.readString(Path.of(filePath));
            DataManager.setCurrentTextInMemory(textToTransfer);
        } catch (IOException e) {            
            e.printStackTrace();
        }
    }

    public static void tranferTextFromMemoryIntoCurrentFile() {
        try {
            FileWriter fileWriter = new FileWriter(currentFile);
            fileWriter.append(currentTextInMemory);
            fileWriter.close();            
        } catch (IOException e) {            
            e.printStackTrace();
        }
    }

    public static File getCurrentFile() {
        return currentFile;
    }

    public static void setCurrentFile(File currentFileToSet) {
        currentFile = currentFileToSet;
    }

    public static void setCurrentTextInMemory(String currentTextToSet) {
        currentTextInMemory = currentTextToSet;
    }

    public static String getCurrentTextInMemory() {
        return currentTextInMemory;
    }

    public static void setCurrentSearchPhrase(String currentSearchPhraseToSet) {
        currentSearchPhrase = currentSearchPhraseToSet;
    }

    public static String getCurrentSearchPhrase() {
        return currentSearchPhrase;
    }
    
}
