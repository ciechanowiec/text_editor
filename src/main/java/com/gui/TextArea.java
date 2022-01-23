package com.gui;

import javax.swing.JTextArea;
import java.awt.Font;

public class TextArea extends JTextArea {

    public TextArea() {
        this.setLineWrap(true);
        Font textAreaFont = new Font("Default", Font.PLAIN, 16);
        this.setFont(textAreaFont);
        this.setName("TextArea");
    }
    
    public void focusText(int startIndex, int searchedPhraseLength) {        
        this.setCaretPosition(startIndex + searchedPhraseLength);
        this.select(startIndex, startIndex + searchedPhraseLength);
        this.grabFocus();
    }
    
}
