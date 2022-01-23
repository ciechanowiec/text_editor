package com.gui;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

    private MenuBar menuBar;
    private ToolsBar toolsBar;
    private TextArea textArea;

    public MainFrame() {
        super("Text Editor");
        this.setSize(606, 730);
        this.setResizable(false);

        initComponents();

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void initComponents() {
        this.menuBar = new MenuBar();
        this.setJMenuBar(this.menuBar);
        this.toolsBar = new ToolsBar();
        this.add(toolsBar, BorderLayout.NORTH);
        this.textArea = new TextArea();
        initScrollPane();
    }

    private void initScrollPane() {
        JScrollPane scrollPane = new JScrollPane(this.textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Border marginBorder = new EmptyBorder(5, 30, 15, 30);
        scrollPane.setBorder(marginBorder);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void setTextForTextArea(String textForTextArea) {
        this.textArea.setText(textForTextArea);
    }

    public String getTextFtomTextArea() {
        return this.textArea.getText();
    }

    public String getSearchedPhrase() {
        return this.toolsBar.getSearchedPhrase();
    }
    
}
