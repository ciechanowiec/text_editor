/**
 * 
 * # Text Editor
 * 
 * ## Table of Contents
 * 1. Basic Info
 * 2. Functionality
 * 3. Architecture
 * 
 * ## Basic Info
 * 
 * This program is a simple text editor which supports working with files and text search.
 * 
 * | Parameter                         | Data                                        |
 * | :-------------------------------: | :-----------------------------------------: |
 * | Program name:                     | Text Editor                                 |
 * | Date of creation:                 | January 2022                                |
 * | Technologies used:                | Java SE                                     |
 * | Time spent to create the program: | ~10 hours                                   |
 * | Author:                           | Herman Ciechanowiec, herman@ciechanowiec.eu |
 * 
 * ## Functionality
 * 
 * The program allows to:
 * - edit text
 * - open text from a file
 * - save text to a file
 * - perform usual text search
 * - perform regex text search
 * 
 * ## Architecture
 * The program is divided into three parts: front-end (graphical user interface), back-end (logic of the program) and the controller, which handles a relationship between the front-end and the back-end: 
 * 
 * */

package com;

public class ApplicationRunner {

    public static void main(String[] args) throws InterruptedException {
        Controller.initMainFrame();        
    }
}
