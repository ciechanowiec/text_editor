package com;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.Image;

public class ToolsBar extends JPanel {

    private final Dimension buttonDimension;
    private JButton openButton;
    private JButton saveButton;
    private JTextField searchField;
    private JButton searchButton;
    private JButton leftArrowButton;
    private JButton rightArrowButton;

    public ToolsBar() {
        this.buttonDimension = new Dimension(35, 35);
        initComponents();
        Border marginBorder = new EmptyBorder(8, 0, 0, 0);
        this.setBorder(marginBorder);
    }

    private void initComponents() {        
        initOpenButton();
        initSaveButton();
        initSearchField();
        initSearchButton();
        initLeftArrowButton();
        initRightArrowButton();
    }

    private void initOpenButton() {
        String iconURI = "src/main/resources/icons/open.png";
        buildButton(iconURI, this.openButton);
    }

    private void initSaveButton() {
        String iconURI = "src/main/resources/icons/save.png";
        buildButton(iconURI, this.saveButton);
    }

    private void initSearchField() {
        this.searchField = new JTextField();
        this.searchField.setPreferredSize(new Dimension(200, 36));
        Font searchFieldFont = new Font("Default", Font.PLAIN, 17);
        this.searchField.setFont(searchFieldFont);
        this.add(this.searchField);
    }

    private void initSearchButton() {
        String iconURI = "src/main/resources/icons/search.png";
        buildButton(iconURI, this.searchButton);
    }

    private void initLeftArrowButton() {
        String iconURI = "src/main/resources/icons/leftArrow.png";
        buildButton(iconURI, this.leftArrowButton);
    }
    
    private void initRightArrowButton() {
        String iconURI = "src/main/resources/icons/rightArrow.png";
        buildButton(iconURI, this.rightArrowButton);        
    }

    private void buildButton(String iconURI, JButton button) {
        Icon icon = produceResizedIcon(iconURI);
        button = new JButton(icon);
        button.setPreferredSize(this.buttonDimension);
        this.add(button);
    }

    private Icon produceResizedIcon(String iconURI) {
        Image originalImage = new ImageIcon(iconURI).getImage();
        Image resizedImage = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        Icon resizedIcon = new ImageIcon(resizedImage);
        return resizedIcon;
    }
    
}
