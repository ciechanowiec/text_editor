package com.gui;

/* This class implements a singleton design pattern */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import com.Controller;

public class ActionListenersFactory {
    
    private static ActionListenersFactory instance;
        
    private ActionListenersFactory() {    
        /* A private constructor to disable the possibility to 
           instantiate more than one instance of this class */
    }    
    
        public static ActionListenersFactory getInstance() {
            if (ActionListenersFactory.instance == null) {
                ActionListenersFactory.instance = new ActionListenersFactory();
            } 
            return ActionListenersFactory.instance;        
        }

    public ActionListener getOpenFromFileAction() {
        return new ActionListener() {     
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = MainFrame.getInstance().getFileChooser();
                int choiceStatus = fileChooser.showOpenDialog(null);
                if (choiceStatus == JFileChooser.APPROVE_OPTION) {                    
                    File selectedFile = fileChooser.getSelectedFile();
                    Controller.openFromFile(selectedFile);                    
                }
            }
        };
    }

    public ActionListener getSaveIntoFileAction() {
        return new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent actionEvent) {                                
                JFileChooser fileChooser = MainFrame.getInstance().getFileChooser();
                int choiceStatus = fileChooser.showSaveDialog(null);
                if (choiceStatus == JFileChooser.APPROVE_OPTION) {                    
                    File selectedFile = fileChooser.getSelectedFile();
                    Controller.saveIntoFile(selectedFile);                    
                }
            }
        };
    }

    public ActionListener getExitAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        };
    }

    public ActionListener getSearchAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Controller.search();                
            }           

        };
    }

    public ActionListener getSelectNextAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Controller.selectNext();
            }
        };
    }

    public ActionListener getSelectPreviousAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Controller.selectPrevious();
            }
        };
    }

    public ActionListener getChangeRegexCheckBoxSelectionAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Controller.changeRegexCheckBoxSelection();
            }
        };
    }

}
