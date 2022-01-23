package com.gui;

/* This class implements a singleton design pattern */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.FileSystem;

import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import com.Controller;
import com.logic.DataManager;

public class ActionListenersFactory {
    
    private static ActionListenersFactory instance;
        

    public static ActionListenersFactory getInstance() {
        if (instance == null) {
            instance = new ActionListenersFactory();
        } 
        return instance;        
    }
    
    private ActionListenersFactory() {
        
    }    

    public ActionListener getOpenFromFileAction() {
        return new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent actionEvent) {                
                /* Prompts a user to choose a file to open 
                   and restricts this choice to .txt files */
                File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
                JFileChooser fileChooser = new JFileChooser(homeDirectory);
                FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("TXT File", "txt");
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setFileFilter(txtFilter);
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
                /* Prompts a user to choose a file to save into 
                   and restricts this choice to .txt files */
                File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
                JFileChooser fileChooser = new JFileChooser(homeDirectory);
                FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("TXT File", "txt");
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setFileFilter(txtFilter);
                int choiceStatus = fileChooser.showSaveDialog(null);
                if (choiceStatus == JFileChooser.APPROVE_OPTION) {                    
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println(selectedFile.getAbsolutePath());                    
                }
            }
        };
    }

}
