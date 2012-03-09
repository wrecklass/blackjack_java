package com.swm.blackjack;

import java.util.ArrayList;

/**
 * Deck is a representation of a deck of Cards, typically
 * being 52 cards per deck. The constructor, Deck(Integer), 
 * actually allows us to specify how many decks we wish to 
 * represent in a 'shoe.' In most casino's this is used to
 * provide a larger Deck that is used to make card counting
 * more difficult.
 * 
 * @author smartin
 */
class Deck
{
	// Implement a 7 deck 'shoe'
	// 52 cards * 7 decks = 364 total cards
	private static final Integer CARDS_IN_DECK = 52;
	private ArrayList<Card> deck;
	private Integer nextCard;
	private Integer cards = CARDS_IN_DECK;
	
	/**
	 * Constructor creates our Deck of Cards and shuffles it. By specifying 
	 * the number of decks at runtime, we can mimic having a
	 * "multiple-Deck" shoe as used in most casino's.
	 * 
	 * @param <code>Integer</code> numberOfDecks
	 */
	public Deck(Integer numberOfDecks)
	{
	    cards = numberOfDecks * CARDS_IN_DECK;
	    deck = new ArrayList<Card>(cards);
		shuffle();
	}

	@SuppressWarnings("unused")
    private Deck()
	{
	    // Never invoked
	}
	
	/**
	 * determine if the deck has enough cards for another hand.
	 * @return <code>true</code> if there is enough cards for a new hand or
	 *         <code>false</code> otherwise
	 */
	public boolean isEmpty()
	{
		return nextCard >= (cards - 20);
	}

	/**
	 * Deal the next Card from the Deck
	 * @return the next <code>Card</code>
	 */
	public Card draw()
	{
		return deck.get(nextCard++);
	}

	/**
	 * Shuffle the deck. If the deck is currently empty, insert the original 
	 * Cards, then shuffle.
	 * 
	 * Note we simply use the standard java.util.Collections.shuffle() method.
	 * The current implementation of the java shuffle method will start us
	 * with a date/time related Random seed.
	 */
	public void shuffle()
	{
		int cardCount = 0;
		if ( deck.isEmpty() ) {
			while ( cardCount < cards) {
				for (Suit suit : Suit.values()) {
					for (Rank rank : Rank.values()) {
						deck.add(new Card(suit, rank));
						cardCount++;
					}
				}
			}
		}
		java.util.Collections.shuffle(deck);
		nextCard = 0;
	}
}
