package com.gui;

/* This class implements a singleton design pattern */

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class MainFrame extends JFrame {

    private static MainFrame instance;

    private JFileChooser fileChooser;
    private MenuBar menuBar;
    private ToolsBar toolsBar;
    private TextArea textArea;

    private MainFrame() {
        super("Text Editor");
        this.setSize(606, 730);
        this.setResizable(false);
        
        initComponents();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static MainFrame getInstance() {
        if (MainFrame.instance == null) {
            MainFrame.instance = new MainFrame();
        }
        return MainFrame.instance;
    }
    
    private void initFileChooser() {
        File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
        this.fileChooser = new JFileChooser(homeDirectory);
        this.fileChooser.setName("FileChooser");
        FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("TXT File", "txt");
        this.fileChooser.setAcceptAllFileFilterUsed(false);
        this.fileChooser.setFileFilter(txtFilter);
        this.add(fileChooser);
    }

    private void initComponents() {
        initFileChooser();
        this.menuBar = new MenuBar();
        this.setJMenuBar(this.menuBar);
        this.toolsBar = new ToolsBar();
        this.add(toolsBar, BorderLayout.NORTH);
        this.textArea = new TextArea();
        initScrollPane();
        
    }

    private void initScrollPane() {
        JScrollPane scrollPane = new JScrollPane(this.textArea);
        scrollPane.setName("ScrollPane");
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Border marginBorder = new EmptyBorder(5, 30, 15, 30);
        scrollPane.setBorder(marginBorder);
        this.add(scrollPane, BorderLayout.CENTER);
    }


    public TextArea getTextArea() {
        return this.textArea;
    }        

    public ToolsBar getToolsBar() {
        return this.toolsBar;
    }

    public JFileChooser getFileChooser() {
        return this.fileChooser;
    }
    
}
