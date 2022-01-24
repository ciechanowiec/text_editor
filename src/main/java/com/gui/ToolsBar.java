package com.gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalCheckBoxIcon;

import java.awt.Image;

public class ToolsBar extends JPanel {

    private final Dimension buttonDimension;
    private JButton openButton;
    private JButton saveButton;
    private JTextField searchField;
    private JButton searchButton;
    private JButton leftArrowButton;
    private JButton rightArrowButton;
    private JCheckBox regexCheckBox;
    private JLabel regexLabel;

    public ToolsBar() {
        this.buttonDimension = new Dimension(35, 35);
        initComponents();
        Border marginBorder = new EmptyBorder(8, 0, 0, 0);
        this.setBorder(marginBorder);
    }

    /* =-=-=-=-=-= METHODS TO INIT GUI COMPONENS =-=-=-=-=-= */

    private void initComponents() {        
        initOpenButton();        
        initSaveButton();
        initSearchField();
        initSearchButton();
        initLeftArrowButton();
        initRightArrowButton();
        initRegexCheckBox();
        initRegexLabel();
    }

    private void initOpenButton() {
        String iconURI = "src/main/resources/icons/open.png";        
        Icon icon = produceResizedIcon(iconURI);
        this.openButton = new JButton(icon);        
        this.openButton.setPreferredSize(this.buttonDimension);
        this.openButton.addActionListener(ActionListenersFactory.getInstance().getOpenFromFileAction());
        this.add(this.openButton);
    }

    private void initSaveButton() {
        String iconURI = "src/main/resources/icons/save.png";
        Icon icon = produceResizedIcon(iconURI);
        this.saveButton = new JButton(icon);        
        this.saveButton.setPreferredSize(this.buttonDimension);        
        this.saveButton.addActionListener(ActionListenersFactory.getInstance().getSaveIntoFileAction());
        this.add(this.saveButton);
    }

    private void initSearchField() {
        this.searchField = new JTextField();        
        this.searchField.setPreferredSize(new Dimension(200, 36));
        Font searchFieldFont = new Font("Default", Font.PLAIN, 17);
        this.searchField.setFont(searchFieldFont);
        
        /* Wrap Search Field into a panel to add left indent 
           from Open Button and Save Button */
        JPanel panelWithSearchField = new JPanel();
        panelWithSearchField.add(this.searchField);
        Border marginBorder = new EmptyBorder(0, 10, 0, 0);
        panelWithSearchField.setBorder(marginBorder);
        this.add(panelWithSearchField);        
    }

    private void initSearchButton() {
        String iconURI = "src/main/resources/icons/search.png";
        Icon icon = produceResizedIcon(iconURI);
        this.searchButton = new JButton(icon);        
        this.searchButton.setPreferredSize(this.buttonDimension);        
        this.searchButton.addActionListener(ActionListenersFactory.getInstance().getSearchAction());        
        this.add(this.searchButton);
    }

    private void initLeftArrowButton() {
        String iconURI = "src/main/resources/icons/leftArrow.png";
        Icon icon = produceResizedIcon(iconURI);
        this.leftArrowButton = new JButton(icon);        
        this.leftArrowButton.setPreferredSize(this.buttonDimension);        
        this.leftArrowButton.addActionListener(ActionListenersFactory.getInstance().getSelectPreviousAction());
        this.add(this.leftArrowButton);
    }
    
    private void initRightArrowButton() {
        String iconURI = "src/main/resources/icons/rightArrow.png";
        Icon icon = produceResizedIcon(iconURI);
        this.rightArrowButton = new JButton(icon);        
        this.rightArrowButton.setPreferredSize(this.buttonDimension);        
        this.rightArrowButton.addActionListener(ActionListenersFactory.getInstance().getSelectNextAction());
        this.add(this.rightArrowButton);
    }

    private void initRegexCheckBox() {
        this.regexCheckBox = new JCheckBox();        

        /* Enlarge the checkbox */
        this.regexCheckBox.setIcon(new MetalCheckBoxIcon () {            
            protected int getControlSize() { 
                return 20;
            }                        
        });                
        
        Border marginBorder = new EmptyBorder(0, 8, 0, 0);
        this.regexCheckBox.setBorder(marginBorder);
        this.add(this.regexCheckBox);
    }

    private void initRegexLabel() {
        this.regexLabel = new JLabel();
        Font regexLabelFont = new Font("Default", Font.BOLD, 14);
        this.regexLabel.setFont(regexLabelFont);
        this.regexLabel.setText("use regex");
        this.add(this.regexLabel);
    }

    /* =-=-=-=-=-= TOOLS METHODS =-=-=-=-=-= */    

    private Icon produceResizedIcon(String iconURI) {
        Image originalImage = new ImageIcon(iconURI).getImage();
        Image resizedImage = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        Icon resizedIcon = new ImageIcon(resizedImage);
        return resizedIcon;
    }

    public String getSearchedPhrase() {        
        return this.searchField.getText();
    }

    public boolean isUseRegexSelected() {
        return this.regexCheckBox.isSelected();
    }

    public JCheckBox getRegexCheckBox() {
        return this.regexCheckBox;
    }    
    
}
