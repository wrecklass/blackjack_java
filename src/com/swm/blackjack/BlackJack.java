/**
 * BlackJack game programmed by Stephen Martin
 * Copyright (C) 2006 Stephen W. Martin
 * 
 */
package com.swm.blackjack;

import java.awt.Color;

/**
 * @author smartin
 * 
 */
public class BlackJack
{
	public static final Color darkGreen = new Color( 0, 128, 0 );
	public static final Color lightYellow = new Color( 255, 255, 128 );
	public static WinningsPanel winningsPanel = new WinningsPanel();
	public static BoardPanel boardPanel = new BoardPanel();

	/**
	 * @param args -- Unused
	 */
	public static void main(String[] args)
	{
		GameFrame frame = new GameFrame( "BlackJack", boardPanel, winningsPanel );
		frame.display();
	}
}
