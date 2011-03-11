/**
 * Player is the specific JPanel for the Users hand
 */
package com.swm.blackjack;

/**
 * @author smartin
 *
 */
class Player extends Hand
{
	/**
	 * Constructor sets the Name of the player on the Panel
	 */
	public Player()
	{
		super("Player");
	}

	@Override
	public int pay(int winnings, int bet)
	{
		return winnings + bet;
	}

	private static final long serialVersionUID = 8439914721238477862L;
}
