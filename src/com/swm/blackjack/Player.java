package com.swm.blackjack;

/**
 * The specific JPanel for the Users hand
 *
 * @author smartin
 *
 */
class Player extends Hand
{
    private static final long serialVersionUID = 1L;

    /**
	 * Constructor sets the Name of the player on the Panel
	 */
	public Player()
	{
		super("Player");
	}

	@Override
	public Integer pay(Integer winnings, Integer bet)
	{
		return winnings + bet;
	}
}
