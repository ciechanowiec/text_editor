package com;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {

    private JMenu fileMenu;
    private JMenu searchMenu;

    public MenuBar() {
        initComponents();
    }

    private void initComponents() {
        initFileMenu();
        initSearchMenu();
    }

    private void initFileMenu() {
        /* Create a File Menu */
        this.fileMenu = new JMenu("File");
        this.fileMenu.setMnemonic(KeyEvent.VK_F);
        
        /* Create items for the File Menu */
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        /* Add items to the File Menu */
        this.fileMenu.add(openItem);
        this.fileMenu.add(saveItem);
        this.fileMenu.addSeparator();
        this.fileMenu.add(exitItem);

        /* Add the File Menu to the Menu Bar */
        this.add(this.fileMenu);

    }

    private void initSearchMenu() {
        /* Create a Search Menu */
        this.searchMenu = new JMenu("Search");
        this.searchMenu.setMnemonic(KeyEvent.VK_S);

        /* Create items for the Search Menu */
        JMenuItem startSearchItem = new JMenuItem("Start search");
        JMenuItem prevSearchItem = new JMenuItem("Previous search");
        JMenuItem nextMatchItem = new JMenuItem("Next match");
        JMenuItem useRegexItem = new JMenuItem("Use regular expressions");        
        
        /* Add items to the Search Menu */
        this.searchMenu.add(startSearchItem);
        this.searchMenu.add(prevSearchItem);
        this.searchMenu.add(nextMatchItem);
        this.searchMenu.add(useRegexItem);        

        /* Add the Search Menu to the Menu Bar */
        this.add(this.searchMenu);

    }
    
}
