package com.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Searcher {    
    
    /* This class implements a singleton design pattern */
    
    private static Searcher instance;

    private ArrayList<Integer[]> startIndecesWithPhraseLengths; // Format: {'start index', 'phrase length'}
    private Integer[] lastReturnedIndexWithPhraseLength = new Integer[2];
    private boolean searchInitialised;
    private boolean toUseRegex;
    private Matcher matcher;
    private ListIterator<Integer[]> iteratorOverStartIndecesWithPhraseLengths;

    private Searcher() {        
        /* A private constructor to disable the possibility to 
           instantiate more than one instance of this class */
        this.startIndecesWithPhraseLengths = new ArrayList<>();        
        this.lastReturnedIndexWithPhraseLength[0] = 0;
        this.lastReturnedIndexWithPhraseLength[1] = 0;
        this.searchInitialised = false;
        this.toUseRegex = false;
    }

    public static Searcher getInstance() {
        if (Searcher.instance == null) {
            Searcher.instance = new Searcher();
        }
        return Searcher.instance;
    }

    public void startNewSearch(boolean useRegex) {        
        resetSearch();
        this.toUseRegex = useRegex;
        initMatcher();
        while (this.matcher.find()) {
            int startIndex = this.matcher.start();
            int phraseLength = this.matcher.group().length();
            startIndecesWithPhraseLengths.add(new Integer[] {startIndex, phraseLength});
        }
        this.iteratorOverStartIndecesWithPhraseLengths = this.startIndecesWithPhraseLengths.listIterator();
        this.searchInitialised = true;
    }

    private void initMatcher() {
        String searchedPhraseFormatted = this.toUseRegex ? DataManager.getCurrentSearchPhrase() 
                                         : Pattern.quote(DataManager.getCurrentSearchPhrase());
        Pattern pattern = Pattern.compile(searchedPhraseFormatted);
        this.matcher = pattern.matcher(DataManager.getCurrentTextInMemory());
    }

    private void resetSearch() {
        this.startIndecesWithPhraseLengths.clear();
        this.lastReturnedIndexWithPhraseLength[0] = 0;
        this.lastReturnedIndexWithPhraseLength[1] = 0;
        this.toUseRegex = false;
        this.matcher = null;
        this.iteratorOverStartIndecesWithPhraseLengths = null;
    }

    /* A function is elaborated to exclude situations when the same value 
       is returned after a search sequence like "next -> previous" */
    public Integer[] getNextStartIndexAndPhraseLength() {
        if (this.iteratorOverStartIndecesWithPhraseLengths.hasNext()) {
            Integer[] candidateData = this.iteratorOverStartIndecesWithPhraseLengths.next();
            if (!Arrays.equals(candidateData, this.lastReturnedIndexWithPhraseLength)) {
                this.lastReturnedIndexWithPhraseLength[0] = candidateData[0];
                this.lastReturnedIndexWithPhraseLength[1] = candidateData[1];
                return candidateData;
            } else {
                return getNextStartIndexAndPhraseLength();
            }
        } else {
            return this.lastReturnedIndexWithPhraseLength;
        }
    }

    /* A function is elaborated to exclude situations when the same value 
       is returned after a search sequence like "next -> previous" */
    public Integer[] getPreviousStartIndexAndPhraseLength() {
        if (this.iteratorOverStartIndecesWithPhraseLengths.hasPrevious()) {
            Integer[] candidateData = this.iteratorOverStartIndecesWithPhraseLengths.previous();
            if (!Arrays.equals(candidateData, this.lastReturnedIndexWithPhraseLength)) {
                this.lastReturnedIndexWithPhraseLength[0] = candidateData[0];
                this.lastReturnedIndexWithPhraseLength[1] = candidateData[1];
                return candidateData;                
            } else {
                return getPreviousStartIndexAndPhraseLength();
            }
        } else {
            return this.lastReturnedIndexWithPhraseLength;
        }
    }

    public boolean isSearchInitilised() {
        return this.searchInitialised;
    }

    public boolean isToUseRegex() {
        return this.toUseRegex;
    }
}
