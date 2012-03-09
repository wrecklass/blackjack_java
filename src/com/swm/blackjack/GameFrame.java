package com.swm.blackjack;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 * This is the main window frame for the game. All of the graphics 
 * components of the game appear in this Frame.
 * 
 * @author smartin
 * 
 */
class GameFrame implements ActionListener {
    private static final Integer GAP   = 2, 
                                 WIDTH = 407, 
                                 HEIGHT = 575;
    private JFrame frame;
    private JMenuItem newGameItem = new JMenuItem("New Game"),
                      exitItem    = new JMenuItem("Quit");

    private JMenuItem aboutItem   = new JMenuItem("About Java BlackJack..."),
                      helpItem    = new JMenuItem("How to Play...");

    private JMenuItem dealItem    = new JMenuItem("Deal"),
                      hitItem     = new JMenuItem("Hit"),
                      stayItem    = new JMenuItem("Stay"),
                      doubleItem  = new JMenuItem("Double");

    private BoardPanel boardPanel;
    private WinningsPanel winningsPanel;
    private JCheckBoxMenuItem softItem = new JCheckBoxMenuItem("Dealer Hits on Soft 17");
    private boolean softState = false;

    /**
     * GameFrame constructor, creates main window, menubar and lays out the
     * components
     * 
     * @param name
     *            a <code>String</code> that will be used for the window title
     * @param boardPanel
     *            a <code>JPanel</code> with the playing board
     * @param winningsPanel
     *            a <code>JPanel</code> with the betting and results
     */
    public GameFrame(String name, BoardPanel boardPanel,
                     WinningsPanel winningsPanel) {
        JMenuBar menuBar = new JMenuBar();

        initMenuBar(menuBar);

        this.boardPanel = boardPanel;
        this.winningsPanel = winningsPanel;

        // Establish our main window frame
        frame = new JFrame(name);
        // Set it's size
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        // Center on the current screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        frame.setLocation((screenSize.width - frameSize.width) / 2,
                          (screenSize.height - frameSize.height) / 2);

        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, GAP,
                                                        GAP));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(boardPanel, BorderLayout.NORTH);
        frame.add(winningsPanel, BorderLayout.SOUTH);

        frame.setJMenuBar(menuBar);
    }

    /**
     * Set up the menubar and all of its components
     * 
     * @param menuBar
     *            the Menubar to be configured
     */
    private void initMenuBar(JMenuBar menuBar) {
        
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        newGameItem.addActionListener(this);
        newGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                                                          ActionEvent.CTRL_MASK));
        newGameItem.setMnemonic(KeyEvent.VK_N);

        exitItem.addActionListener(this);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                                                       ActionEvent.CTRL_MASK));
        exitItem.setMnemonic(KeyEvent.VK_Q);

        fileMenu.add(newGameItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu playMenu = new JMenu("Play");
        playMenu.setMnemonic(KeyEvent.VK_P);

        dealItem.addActionListener(this);
        dealItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0));
        dealItem.setMnemonic(KeyEvent.VK_D);

        hitItem.addActionListener(this);
        hitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0));
        hitItem.setMnemonic(KeyEvent.VK_H);

        stayItem.addActionListener(this);
        stayItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0));
        stayItem.setMnemonic(KeyEvent.VK_S);

        doubleItem.addActionListener(this);
        doubleItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, 0));
        doubleItem.setMnemonic(KeyEvent.VK_O);

        playMenu.add(dealItem);
        playMenu.add(hitItem);
        playMenu.add(stayItem);
        playMenu.add(doubleItem);

        JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_O);
        softItem.addActionListener(this);
        softItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_7,
                                                       ActionEvent.CTRL_MASK));
        softItem.setMnemonic(KeyEvent.VK_D);
        softItem.setState(softState);

        optionsMenu.add(softItem);

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        aboutItem.addActionListener(this);
        helpItem.addActionListener(this);
        helpMenu.add(helpItem);
        helpMenu.addSeparator();
        helpMenu.add(aboutItem);
        helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));

        menuBar.add(fileMenu);
        menuBar.add(playMenu);
        menuBar.add(optionsMenu);
        menuBar.add(helpMenu);
    }

    /**
     * Standard actionPerformed method to be called menu items are selected
     * 
     * @param evt
     *            is an <code>ActionEvent</code> with the information about the
     *            users actions
     */
    public void actionPerformed(ActionEvent evt) {
        Object object = evt.getSource();

        if (object == newGameItem) {
            boardPanel.newDeal();
            winningsPanel.init();
            frame.repaint();
        } else if (object == exitItem) {
            frame.setVisible(false);
            frame.dispose();
        } else if (object == softItem) {
            softState = (softState ? false : true);
            softItem.setState(softState);
        } else if (object == aboutItem) {
            displayAboutBox();
        } else if (object == helpItem) {
            displayHelp();
        } else if (object == dealItem) {
            boardPanel.clickDeal();
        } else if (object == hitItem) {
            boardPanel.clickHit();
        } else if (object == stayItem) {
            boardPanel.clickStay();
        } else if (object == doubleItem) {
            boardPanel.clickDouble();
        }
    }

    /**
     * Display out About dialog box
     */
    private void displayAboutBox() {

        JOptionPane.showMessageDialog(frame,
                                      "Java BlackJack \nCopyright © 2006-2008 Stephen Martin, all rights reserved",
                                      "About BlackJack",
                                      JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Display our Help dialog box
     */
    private void displayHelp() {
        String helpMsg = "Use Deal to start a hand.\n"
                         + "Use Hit to take a new Card.\n"
                         + "Use Stay to take no more cards.\n"
                         + "Use Double to take just one card and double your bet.\n\n"
                         + "You can only change your bet before a new hand is dealt.\n"
                         + "Try to get close to 21 without going over.\n"
                         + "Dealer must hit on anything less than a 17.\n"
                         + "Dealer plays after you.\n"
                         + "Beat the dealer to win!\n";

        JOptionPane.showMessageDialog(frame, helpMsg, "How to Play BlackJack",
                                      JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Set the window to visible
     */
    public void display() {
        frame.setVisible(true);
    }
}
