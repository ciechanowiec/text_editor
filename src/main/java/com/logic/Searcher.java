package com.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* This class implements a singleton design pattern */

public class Searcher {    

    private static Searcher instance;

    private ArrayList<Integer[]> startIndecesWithPhraseLengths; // Format: {'start index', 'phrase length'}
    private ListIterator<Integer[]> iteratorOverStartIndecesWithPhraseLengths;
    private Integer[] lastReturnedIndexWithPhraseLength = new Integer[2];
    private boolean searchInitialised;
    private Matcher matcher;
    private boolean toUseRegex;

    private Searcher() {        
        /* A private constructor to disable the possibility to 
           instantiate more than one instance of this class */
        this.startIndecesWithPhraseLengths = new ArrayList<>();        
        this.searchInitialised = false;
        this.lastReturnedIndexWithPhraseLength[0] = 0;
        this.lastReturnedIndexWithPhraseLength[1] = 0;
        this.toUseRegex = false;
    }

    public static Searcher getInstance() {
        if (Searcher.instance == null) {
            Searcher.instance = new Searcher();
        }
        return Searcher.instance;
    }

    public void initSearch(boolean useRegex) {
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
        this.matcher = pattern.matcher(DataManager.getCurrentText());
    }

    private void resetSearch() {
        this.startIndecesWithPhraseLengths.clear();
        this.iteratorOverStartIndecesWithPhraseLengths = null;
        this.lastReturnedIndexWithPhraseLength[0] = 0;
        this.lastReturnedIndexWithPhraseLength[1] = 0;
        this.matcher = null;
        this.toUseRegex = false;
    }

    /* Function is elaborated to exclude situations when the same value is returned 
       after search sequence like "next -> previous" */
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

    /* Function is elaborated to exclude situations when the same value is returned 
       after search sequence like "next -> previous" */
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
