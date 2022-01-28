package com.gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {
    
    /* This class implements a singleton design pattern */

    private static MainFrame instance;

    private MenuBar menuBar;
    private ToolsBar toolsBar;
    private TextArea textArea;

    private MainFrame() {
        /* A private constructor to disable the possibility to 
           instantiate more than one instance of this class */
        super("Text Editor");
        this.setSize(606, 730);
        this.setResizable(false);
        setMainFrameIcon();
        
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
    
    private void setMainFrameIcon() {
        String iconURI = "src/main/resources/icon.png";
        ImageIcon icon = new ImageIcon(iconURI);
        this.setIconImage(icon.getImage());
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


    public TextArea getTextArea() {
        return this.textArea;
    }        

    public ToolsBar getToolsBar() {
        return this.toolsBar;
    }    
    
}
