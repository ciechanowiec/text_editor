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
        mainFrame.getTextArea().setText(DataManager.getCurrentTextInMemory());
    }
    
    public static void saveIntoFile(File fileToSaveInto) {
        DataManager.setCurrentFile(fileToSaveInto);
        updateCurrentTextInMemory();
        DataManager.tranferTextFromMemoryIntoCurrentFile();
    }

    public static void startNewSearch() {                
        DataManager.setCurrentSearchPhrase(mainFrame.getToolsBar().getSearchedPhrase());
        updateCurrentTextInMemory();
        boolean useRegex = mainFrame.getToolsBar().isUseRegexSelected();
        Searcher.getInstance().startNewSearch(useRegex);
        selectNext();        
    }    

    private static void updateCurrentTextInMemory() {
        DataManager.setCurrentTextInMemory(mainFrame.getTextArea().getText());
    }

    public static void selectNext() {
        if (!needToStartNewSearch()) {
            Integer[] dataForNextSelection = Searcher.getInstance().getNextStartIndexAndPhraseLength();
            int startIndex = dataForNextSelection[0];
            int searchedPhraseLength = dataForNextSelection[1];
            mainFrame.getTextArea().focusText(startIndex, searchedPhraseLength);
        } else {
            startNewSearch();
        }
    }

    public static void selectPrevious() {
        if (!needToStartNewSearch()) {
            Integer[] dataForPreviousSelection = Searcher.getInstance().getPreviousStartIndexAndPhraseLength();
            int startIndex = dataForPreviousSelection[0];
            int searchedPhraseLength = dataForPreviousSelection[1];
            mainFrame.getTextArea().focusText(startIndex, searchedPhraseLength);
        } else {
            startNewSearch();
        }
    }

    public static boolean needToStartNewSearch() {
        /* New search needs to be started if:
           - the search hasn't been started yet,
           - the search has been already started but after that a user has changed:
             - the 'use regex' status,
             - the text itself, 
             - the searched phrase. */
        return (!Searcher.getInstance().isSearchInitilised()
                || Searcher.getInstance().isToUseRegex() != mainFrame.getToolsBar().isUseRegexSelected()
                || !DataManager.getCurrentTextInMemory().equals(mainFrame.getTextArea().getText())
                || !DataManager.getCurrentSearchPhrase().equals(mainFrame.getToolsBar().getSearchedPhrase()));
    }

    public static void changeRegexCheckBoxSelection() {
        /* Inverts the selection of a regex check-box */
        if (mainFrame.getToolsBar().getRegexCheckBox().isSelected()) {
            mainFrame.getToolsBar().getRegexCheckBox().setSelected(false);
        } else {
            mainFrame.getToolsBar().getRegexCheckBox().setSelected(true);
        }
    }
}
