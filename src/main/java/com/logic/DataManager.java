package com.logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class DataManager {

    private static File currentFile;
    private static String currentText;
    private static String currentSearchPhrase;

    public static void tranferTextFromCurrentFileIntoMemory() {
        try {
            String filePath = currentFile.getAbsolutePath();
            String textToTransfer = Files.readString(Path.of(filePath));
            DataManager.setCurrentText(textToTransfer);
        } catch (IOException e) {            
            e.printStackTrace();
        }
    }

    public static void tranferTextFromMemoryIntoCurrentFile() {
        try {
            FileWriter fileWriter = new FileWriter(currentFile);
            fileWriter.append(currentText);
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

    public static void setCurrentText(String currentTextToSet) {
        currentText = currentTextToSet;
    }

    public static String getCurrentText() {
        return currentText;
    }

    public static void setCurrentSearchPhrase(String currentSearchPhraseToSet) {
        currentSearchPhrase = currentSearchPhraseToSet;
    }

    public static String getCurrentSearchPhrase() {
        return currentSearchPhrase;
    }
    
}
