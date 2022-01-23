package com;

import java.io.File;

import com.gui.MainFrame;
import com.logic.DataManager;
import com.logic.Searcher;

/* This class represents a controller that handles relations between GUI and Logic */

public class Controller {

    private static MainFrame mainFrame;

    public static void initMainFrame() {
        mainFrame = MainFrame.getInstance();
    }

    public static File getCurrentFile() {
        return DataManager.getCurrentFile();
    }

    public static void openFromFile(File fileToOpenFrom) {
        DataManager.setCurrentFile(fileToOpenFrom);
        DataManager.tranferTextFromCurrentFileIntoMemory();
        mainFrame.getTextArea().setText(DataManager.getCurrentText());
    }
    
    public static void saveIntoFile(File fileToSaveInto) {
        DataManager.setCurrentFile(fileToSaveInto);
        updateCurrentText();
        DataManager.tranferTextFromMemoryIntoCurrentFile();
    }

    public static void search() {        
        /* Starts a new search */
        DataManager.setCurrentSearchPhrase(mainFrame.getToolsBar().getSearchedPhrase());
        updateCurrentText();
        Searcher.getInstance().initSearch(mainFrame.getToolsBar().isUseRegexSelected());
        selectNext();        
    }    

    private static void updateCurrentText() {
        DataManager.setCurrentText(mainFrame.getTextArea().getText());
    }

    public static void selectNext() {
        if (!needToInitialiseNewSearch()) {
            Integer[] dataForNextSelection = Searcher.getInstance().getNextStartIndexAndPhraseLength();
            int startIndex = dataForNextSelection[0];
            int searchedPhraseLength = dataForNextSelection[1];
            mainFrame.getTextArea().focusText(startIndex, searchedPhraseLength);
        } else {
            search();
        }
    }

    public static void selectPrevious() {
        if (!needToInitialiseNewSearch()) {
            Integer[] dataForPreviousSelection = Searcher.getInstance().getPreviousStartIndexAndPhraseLength();
            int startIndex = dataForPreviousSelection[0];
            int searchedPhraseLength = dataForPreviousSelection[1];
            mainFrame.getTextArea().focusText(startIndex, searchedPhraseLength);
        } else {
            search();
        }
    }

    public static boolean needToInitialiseNewSearch() {
        return (!Searcher.getInstance().isSearchInitilised()
                || Searcher.getInstance().isToUseRegex() != mainFrame.getToolsBar().isUseRegexSelected()
                || !DataManager.getCurrentText().equals(mainFrame.getTextArea().getText())
                || !DataManager.getCurrentSearchPhrase().equals(mainFrame.getToolsBar().getSearchedPhrase()));
    }

    public static void changeRegexCheckBoxSelection() {
        if (mainFrame.getToolsBar().getRegexCheckBox().isSelected()) {
            mainFrame.getToolsBar().getRegexCheckBox().setSelected(false);
        } else {
            mainFrame.getToolsBar().getRegexCheckBox().setSelected(true);
        }
    }
}
