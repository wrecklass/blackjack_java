/**
 * Dealer is the JPanel for the Dealers cards to be displayed on
 */
package com.swm.blackjack;

/**
 * @author smartin
 * 
 */
class Dealer extends Hand
{
	private boolean hitOnSoft17 = false;
	
	/**
	 * Constructor creates the Dealers hand and sets the Dealer name for display
	 */
	public Dealer()
	{
		super( "Dealer" );
	}

	public void setHitOnSoft17(boolean h)
	{
		hitOnSoft17 = h;
	}
	
	/**
	 * Draw cards from the Deck until the dealers total is greater than or equal
	 * to 17. Standard BlackJack rules. 
	 * @param deck the <code>Deck</code> to draw cards from
	 */
	public void play(Deck deck)
	{
		turnFaceUp( 0 );
		
		while ( getTotal() < 17 && getCardIndex() < MAX_CARDS_IN_HAND ) {
			addCard( deck.draw(), true );
		}
		
		if ( hitOnSoft17 && isSoft() && getTotal() == 17 
				&& ( getCardIndex() < MAX_CARDS_IN_HAND )) {
			addCard (deck.draw(), true);
		}
		
		repaint();
	}

	@Override
	public int pay(int winnings, int bet)
	{
		return winnings - bet;
	}

	private static final long serialVersionUID = 3129914725298477862L;
}
