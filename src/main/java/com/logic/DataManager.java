package com.logic;

import java.io.File;

public abstract class DataManager {

    private static File currentFile;
    private static String currentText;    

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
    
}
