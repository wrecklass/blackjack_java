/**
 * BlackJack game programmed by Stephen Martin
 * Copyright (C) 2006-2012 Stephen W. Martin
 *
 */
package com.swm.blackjack;

import java.awt.Color;

/**
 * The main class to the BlackJack game.
 *
 * Contains the main() method from which the game is invoked. Also contains
 * several global constants used by the game display.
 *
 * @author smartin
 *
 */
public class BlackJack {
    public static final Color   darkGreen     = new Color(0, 128, 0);
    public static final Color   lightYellow   = new Color(255, 255, 128);
    public static WinningsPanel winningsPanel = new WinningsPanel();
    public static BoardPanel    boardPanel    = new BoardPanel();

    /**
     * @param args
     *            -- Unused
     */
    public static void main(String[] args) {
        GameFrame frame = new GameFrame("BlackJack", boardPanel, winningsPanel);
        frame.display();
    }
}
