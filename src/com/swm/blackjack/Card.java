package com.swm.blackjack;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * Card - a JPanel class that specifies an individual card and how to
 * draw it in the playing area.
 * 
 * A card has a Rank (Ace - King), Suit (Club, Diamond, Heart, Spade) 
 * and an Orientation (Face Up, Face Down, or Empty)
 * 
 * @author smartin
 */
class Card extends JPanel implements Comparable<Card>
{
	private static final long serialVersionUID = 2L;
	public static final Integer WIDTH = 60, HEIGHT = 80;
	private Rank rank;
	private Suit suit;
	private CardOrientation orientation;

	/**
	 * Initialize an Empty card to reserve space on the board
	 */
	public Card()
	{
		this.suit = Suit.CLUB;
		this.rank = Rank.TWO;
		this.orientation = CardOrientation.EMPTY;
	}
	
	/**
	 * Constructor to set a card
	 * @param suit the Suit of this card
	 * @param rank the Rank of this card
	 * @param orientation the CardOrientation of this card (Face up, down, etc.)
	 */
	public Card(Suit suit, Rank rank, CardOrientation orientation)
	{
		this.suit = suit;
		this.rank = rank;
		this.orientation  = orientation;
	}

	/**
	 * Constructor to set a card with a Default CardOrientation of FACE_DOWN
	 * @param suit the Suit of this card
	 * @param rank the Rank of this card
	 */
	public Card(Suit suit, Rank rank) 
	{
		this.suit = suit;
		this.rank = rank;
		this.orientation = CardOrientation.FACE_DOWN;
	}
	
	/**
	 * Standard comparator to allow us to decide which card is of higher value.
	 * For sorting and shuffling algorithms
	 * @param o the <code>Card</code> to compare to
	 * @return -1 if less than, 0 if equal, and 1 otherwise
	 */
	public int compareTo(Card o)
	{
		return rank.compareTo( o.rank );
	}

	/**
	 * Standard method used by swing to find out the preferred size of a JPanel.
	 * @return the Dimension based on our WIDTH and HEIGHT specifications
	 */
	public Dimension getPreferredSize()
	{
		return new Dimension( WIDTH, HEIGHT );
	}

	/**
	 * Standard method used by swing to find out the minimum size of a JPanel,
	 * we simply return the preferred size as we need cards fully displayed.
	 * @return the Dimension from getPreferredSize()
	 */
	public Dimension getMinimumSize()
	{
		return getPreferredSize();
	}

	/**
	 * Standard method used by swing to paint the component in the Graphics
	 * container. Calls the draw() method of the cards orientation member.
	 * 
	 * To get font smoothing (anti-aliasing) we render with the 
	 * java.awt.Graphics2D engine.
	 * @param a Graphics swing drawing container
	 */
	public void paintComponent(Graphics graphics)
	{
        Graphics2D g2 = (Graphics2D) graphics;
        
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
          RenderingHints.VALUE_RENDER_QUALITY);
        
        g2.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 200);
        
        super.paintComponent(g2);
//		super.paintComponent( graphics );
		orientation.draw( g2, suit, rank );
	}

	/**
	 * Get a value of the card based on the rank: ACE = 1, TWO = 2, THREE = 3...
	 * Face Cards all return 10 (for BlackJack)
	 * @return
	 */
	public int getValue()
	{
		int val = 1 + rank.ordinal();
		if ( val > 10 ) val = 10;
		return (val);
	}

	/**
	 * Turn the CardOrientation FACE UP, and repaint to display the Card.
	 */
	public void turnFaceUp()
	{
		orientation = CardOrientation.FACE_UP;
		repaint();
	}

	/**
	 * Turn a CardOrientation FACE DOWN and repaint to display the Card. Not
	 * used in BlackJack, but handy for other types of games.
	 */
	public void turnFaceDown()
	{
		orientation = CardOrientation.FACE_DOWN;
		repaint();
	}

	/**
	 * Retrieve the current Card Orientation
	 * @return CardOrientation (FACE_UP,FACE_DOWN,EMPTY)
	 */
	public CardOrientation getOrientation()
	{
		return orientation;
	}

	/**
	 * Standard String utility used mostly for debugging, but could also be used
	 * in a console based game
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		if ( orientation.compareTo( CardOrientation.EMPTY ) != 0 ) {
			sb.append( rank.toString() + " of " + suit.toString() );
		}
		return sb.toString();
	}
}
