package com;

import java.io.File;

import com.gui.MainFrame;
import com.logic.DataManager;
import com.logic.Driver;

/* This class represents a controller that handles relations between GUI and Logic */

public class Controller {

    private static MainFrame mainFrame;

    public static void initMainFrame() {
        mainFrame = new MainFrame();
    }

    public static File getCurrentFile() {
        return DataManager.getCurrentFile();
    }

    public static void openFromFile(File fileToOpenFrom) {
        DataManager.setCurrentFile(fileToOpenFrom);
        Driver.tranferTextFromCurrentFileIntoMemory();
        mainFrame.setTextForTextArea(DataManager.getCurrentText());
    }
    
    public static void saveIntoFile(File fileToSaveInto) {
        DataManager.setCurrentFile(fileToSaveInto);
        updateCurrentText();
        Driver.tranferTextFromMemoryIntoCurrentFile();
    }

    public static void search() {
        mainFrame.getSearchedPhrase();
    }

    private static void updateCurrentText() {
        DataManager.setCurrentText(mainFrame.getTextFtomTextArea());
    }
}
