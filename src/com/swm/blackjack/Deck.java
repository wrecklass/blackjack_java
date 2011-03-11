/**
 * Deck is a representation of a deck of Cards, typically
 * being 52 cards per deck. Note that a deck simply contains
 * an Array of 52 Integers, each of which forms the Ordinal of
 * the Cards to be displayed.
 */
package com.swm.blackjack;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * @author smartin
 * 
 */
class Deck
{
	// Implement a 7 deck 'shoe'
	// 52 cards * 7 decks = 364 total cards
	private static final int CARDS_IN_DECK = 364;
	private ArrayList<Card> deck = new ArrayList<Card>();
	private int nextCard;

	/**
	 * Constructor creates our Array and shuffles it
	 */
	public Deck()
	{
		shuffle();
	}

	/**
	 * determine if the deck has enough cards for another hand.
	 * @return <code>true</code> if there is enough cards for a new hand or
	 *         <code>false</code> otherwise
	 */
	public boolean isEmpty()
	{
		return nextCard >= (CARDS_IN_DECK - 20);
	}

	/**
	 * Deal the next card from the deck
	 * @return the <code>ordinal</code> of the next card
	 */
	public Card draw()
	{
		return deck.get(nextCard++);
	}

	/**
	 * Shuffle the deck. If the deck is currently empty, insert the original 52
	 * cards, then shuffle.
	 * Note we simply use the standard java.util.Collections.shuffle() method,
	 * with the current Long Date in seconds as a Seed.
	 */
	public void shuffle()
	{
		int cardCount = 0;
		if ( deck.isEmpty() ) {
			while ( cardCount < CARDS_IN_DECK) {
				for (Suit suit : Suit.values()) {
					for (Rank rank : Rank.values()) {
						deck.add(new Card(suit, rank));
						cardCount++;
					}
				}
			}
		}
		Random rand = new Random();
		rand.setSeed( new Date().getTime() );
		java.util.Collections.shuffle( deck, rand );
		nextCard = 0;
	}
}
