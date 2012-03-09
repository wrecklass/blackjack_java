package com.swm.blackjack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import static com.swm.blackjack.BlackJack.darkGreen;

/**
 * CardOrientation - Enumerates whether the card is FACE_UP, FACE_DOWN
 * or EMPTY. Empty is used to specify a card position that contains no
 * card at this time for display purposes.
 * 
 * Each Card Orientation contains the method to draw the card on the 
 * playing surface.
 * 
 * @author smartin
 */
enum CardOrientation
{

    /**
     * Face Up
     * -- Display the front of the Card
     */
	FACE_UP {
		void draw(Graphics graphics, Suit suit, Rank rank)
		{
			graphics.setColor( Color.WHITE );
			graphics.fillRect( 0, 0, Card.WIDTH, Card.HEIGHT );
			drawBorder( graphics );
			graphics.setFont( rankFont );
			if ( suit.equals(Suit.DIAMOND) 
			     || suit.equals(Suit.HEART)   ) {
			    graphics.setColor( Color.RED );
			} else {
			    graphics.setColor( Color.BLACK );
			}
			// Need to offset the characters a bit for the '10'
			// as the only card with a double character rank
			if ( rank.ordinal() == 9 ) {
				suit.drawSymbol( graphics, 24, 20 );
				graphics.drawString( "" + rank.getSymbol(), 7, 43 );
			}
			else {
				suit.drawSymbol( graphics, 22, 20 );
				graphics.drawString( "" + rank.getSymbol(), 14, 43 );
			}
		}
	},
	/**
	 * Face Down
	 * -- Display the back of the Card.
	 */
	FACE_DOWN {
		void draw(Graphics graphics, Suit suit, Rank rank)
		{
			graphics.setColor( Color.ORANGE );
			graphics.fillRect( 0, 0, Card.WIDTH, Card.HEIGHT );
			graphics.setColor( Color.BLACK );
			for ( int x = -Card.WIDTH; x < (2 * Card.WIDTH) + 20; x += 15 ) {
				graphics.drawLine( x, 0, x + Card.HEIGHT, Card.HEIGHT );
				graphics.drawLine( x, 0, x - Card.HEIGHT, Card.HEIGHT );
			}
			drawBorder( graphics );
			graphics.setFont( backFont );
			graphics.drawString( "J", 16, 24 );
			graphics.drawString( "B", 24, 46 );
			graphics.drawString( "J", 32, 68 );
		}
	},
	/**
	 * Empty
	 * -- There is no card, so display an empty playing area in this
	 * position.
	 */
	EMPTY {
		void draw(Graphics graphics, Suit suit, Rank rank)
		{
			graphics.setColor( darkGreen );
			graphics.fillRect( 0, 0, Card.WIDTH, Card.HEIGHT );
			//drawBorder( graphics );
		}
	};
	
	private static final Font backFont = new Font( "Arial Black", Font.PLAIN, 22 ),
			                  rankFont = new Font( "Imprint MT Shadow", Font.PLAIN, 26 );

	/**
	 * Abstract method defined in each specific enum instance which specifies
	 * how that item is to be drawn on screen.
	 * @param graphics the <code>Graphics</code> container to be drawn into.
	 * @param suit of the Card
	 * @param rank of the Card
	 */
	abstract void draw(Graphics graphics, Suit suit, Rank rank);

	/**
	 * Draw a border around the Card
	 * @param graphics the <code>Graphics</code> container to be drawn into.
	 */
	private static void drawBorder(Graphics graphics)
	{
		graphics.setColor( Color.BLACK );
		graphics.drawRect( 0, 0, Card.WIDTH - 1, Card.HEIGHT - 1 );
		graphics.drawRect( 1, 1, Card.WIDTH - 3, Card.HEIGHT - 3 );
	}
}
