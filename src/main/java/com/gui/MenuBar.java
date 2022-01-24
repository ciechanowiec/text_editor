package com.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {

    private JMenu fileMenu;
    private JMenuItem openItem;
    private JMenuItem saveItem;
    private JMenuItem exitItem;

    private JMenu searchMenu;
    private JMenuItem startSearchItem;
    private JMenuItem prevSearchItem;
    private JMenuItem nextMatchItem;
    private JMenuItem useRegexItem;

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
        
        /* Init File Menu items */
        initFileMenuItems();

        /* Add items to the File Menu */
        this.fileMenu.add(this.openItem);
        this.fileMenu.add(this.saveItem);
        this.fileMenu.addSeparator();
        this.fileMenu.add(this.exitItem);

        /* Add the File Menu to the Menu Bar */
        this.add(this.fileMenu);        
    }

    private void initFileMenuItems() {        
        this.openItem = new JMenuItem("Open");        
        this.openItem.addActionListener(ActionListenersFactory.getInstance().getOpenFromFileAction());

        this.saveItem = new JMenuItem("Save");        
        this.saveItem.addActionListener(ActionListenersFactory.getInstance().getSaveIntoFileAction());

        this.exitItem = new JMenuItem("Exit");        
        this.exitItem.addActionListener(ActionListenersFactory.getInstance().getExitAction());
    }

    private void initSearchMenu() {
        /* Create a Search Menu */
        this.searchMenu = new JMenu("Search");        
        this.searchMenu.setMnemonic(KeyEvent.VK_S);

        /* Create Search Menu items */
        initSearchMenuItems();

        /* Add items to the Search Menu */
        this.searchMenu.add(this.startSearchItem);
        this.searchMenu.add(this.prevSearchItem);
        this.searchMenu.add(this.nextMatchItem);
        this.searchMenu.add(this.useRegexItem);        

        /* Add the Search Menu to the Menu Bar */
        this.add(this.searchMenu);
    }

    private void initSearchMenuItems() {
        this.startSearchItem = new JMenuItem("Start search");        
        this.startSearchItem.addActionListener(ActionListenersFactory.getInstance().getSearchAction());

        this.prevSearchItem = new JMenuItem("Previous search");        
        this.prevSearchItem.addActionListener(ActionListenersFactory.getInstance().getSelectPreviousAction());
        
        this.nextMatchItem = new JMenuItem("Next match");        
        this.nextMatchItem.addActionListener(ActionListenersFactory.getInstance().getSelectNextAction());

        this.useRegexItem = new JMenuItem("Use regular expressions");        
        this.useRegexItem.addActionListener(ActionListenersFactory.getInstance().getChangeRegexCheckBoxSelectionAction());
    }
    
}
