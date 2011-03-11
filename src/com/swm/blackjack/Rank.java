/**
 * Ranks represents the standard Card ranks Ace-King, with Ace as lowest value.
 */
package com.swm.blackjack;

/**
 * @author smartin
 * 
 */
enum Rank implements Comparable<Rank>
{
	ACE("A"), 
	TWO("2"), 
	THREE("3"), 
	FOUR("4"), 
	FIVE("5"), 
	SIX("6"), 
	SEVEN("7"), 
	EIGHT("8"), 
	NINE("9"), 
	TEN("10"), 
	JACK("J"), 
	QUEEN("Q"), 
	KING("K");

	private String symbol;

	/**
	 * Constructor initializes the symbol String
	 * @param symbol
	 */
	private Rank(String symbol)
	{
		this.symbol = symbol;
	}

	/**
	 * Retrieve the Rank based on a given Ordinal (0-12)
	 * @param ordinal the <code>int</code> of the ordinal
	 * @return the Rank value for the ordinal specified
	 */
	public static Rank aRank(int ordinal)
	{
		return Rank.values()[ordinal];
	}

	/**
	 * Get the Symbol for the Rank (e.g. "A" for Ace)
	 * @return the Symbol string
	 */
	public String getSymbol()
	{
		return symbol;
	}
}
