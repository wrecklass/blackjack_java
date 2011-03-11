/**
 * CardOrientation - Specifies whether the card is Face Up, Face Down
 * or Empty. Empty is used to specify a card position that contains no
 * card at this time for display purposes.
 */
package com.swm.blackjack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import static com.swm.blackjack.BlackJack.darkGreen;

enum CardOrientation
{

	FACE_UP {
		void draw(Graphics graphics, Suit suit, Rank rank)
		{
			graphics.setColor( Color.WHITE );
			graphics.fillRect( 0, 0, Card.WIDTH, Card.HEIGHT );
			drawBorder( graphics );
			graphics.setFont( rankFont );
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
	EMPTY {
		void draw(Graphics graphics, Suit suit, Rank rank)
		{
			graphics.setColor( darkGreen );
			graphics.fillRect( 0, 0, Card.WIDTH, Card.HEIGHT );
			//drawBorder( graphics );
		}
	};

	private static final Font rankFont = new Font( "Serif", Font.BOLD, 24 ),
			backFont = new Font( "Serif", Font.ITALIC, 24 );

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
