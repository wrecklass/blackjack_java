/**
 * BoardPanel - JPanel that shows the games primary playing 
 * surface with Cards and player option buttons.
 */
package com.swm.blackjack;

import static com.swm.blackjack.BlackJack.darkGreen;
import static com.swm.blackjack.BlackJack.winningsPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author smartin
 * 
 */
class BoardPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private static final int GAP = 20;
	private Deck deck = new Deck();
	private JPanel controls = new JPanel();

	private Dealer dealer = new Dealer();
	private Player player = new Player();

	private JButton deal = new JButton( "Deal" );
	private JButton hit  = new JButton( "Hit" );
	private JButton stay = new JButton( "Stay" );
	private JButton dbl  = new JButton( "Double" );

	/**
	 * Constructor for the Playing Board and it's contents
	 */
	public BoardPanel()
	{
		setBackground( darkGreen );
		setLayout( new BorderLayout( GAP, GAP ) );

		controls.setLayout( new FlowLayout( FlowLayout.CENTER, GAP, GAP ) );
		controls.setBackground( darkGreen );

		controls.add( deal );
		controls.add( hit );
		controls.add( stay );
		controls.add( dbl );

		add( dealer, BorderLayout.NORTH );
		add( player, BorderLayout.CENTER );
		add( controls, BorderLayout.SOUTH );

		deal.addActionListener( this );
		hit.addActionListener( this );
		stay.addActionListener( this );
		dbl.addActionListener( this );

		deal.setEnabled( true );
		hit.setEnabled( false );
		stay.setEnabled( false );
		dbl.setEnabled( false );

		deal.setDefaultCapable( true );
		deal.setMnemonic( 'd' );
		deal.setToolTipText( "Deal a new hand." );

		hit.setMnemonic( 'h' );
		hit.setToolTipText( "Take another card." );

		stay.setMnemonic( 's' );
		stay.setToolTipText( "Don't take any more cards." );

		dbl.setMnemonic( 'o' );
		dbl.setToolTipText( "Double bet and take one more card." );
	}

	/**
	 * Standard method used to handle actions events caused by the player
	 * clicking on various buttons.
	 * @param e the ActionEvent containing the action selected by user
	 */
	public void actionPerformed(ActionEvent e)
	{
		Object object = e.getSource();

		winningsPanel.enableBetPanel( true );
		
		if ( object == deal ) {
			winningsPanel.enableBetPanel( false );
			newDeal();
			checkBlackJack();
		}
		else if ( object == hit ) {
			dbl.setEnabled( false );
			playHit();
		}
		else if ( object == stay ) {
			playDealer();
			determineWinner();
		}
		else if ( object == dbl ) {
			winningsPanel.doubled( true );
			if (playHit()) {
				playDealer();
				determineWinner();
			}
			hit.setEnabled(false);
			stay.setEnabled(false);
			winningsPanel.doubled( false );
			
		}
		if ( winningsPanel.getWinnings() <= 0 ) {
			deal.setEnabled( false );
			hit.setEnabled( false );
			stay.setEnabled( false );
			dbl.setEnabled( false );
			winningsPanel.updateWinnings( null, "You Are Broke!" );
		}
	}

	/**
	 * Check to see if the player has BlackJack.
	 */
	private void checkBlackJack()
	{
		if ( player.getTotal() == 21 ) {
			// Pay out double on BlackJack.
			winningsPanel.doubled( true );
			winningsPanel.updateWinnings( player, "BLACKJACK!" );
			winningsPanel.doubled( false );
			hit.setEnabled( false );
			stay.setEnabled( false );
			dbl.setEnabled( false );
			deal.setEnabled( true );
			winningsPanel.enableBetPanel(true);
			deal.requestFocus();
		}
	}

	/**
	 * Handle the players using the "Hit" button
	 * @return <code>false</code> if they go over 21 <code>true</code>
	 *         otherwise
	 */
	private boolean playHit()
	{
		boolean ret = true;

		player.addCard( deck.draw(), true );

		if ( player.getTotal() > 21 ) {
			winningsPanel.updateWinnings( dealer, "Player Busted." );
			deal.setEnabled( true );
			deal.requestFocus();
			hit.setEnabled( false );
			stay.setEnabled( false );
			ret = false;
		}
		else if ( player.getTotal() == 21 ) {
			// Keep them from doing something foolish
			deal.setEnabled( false );
			hit.setEnabled( false );
			stay.setEnabled( true );
			stay.requestFocus();
		}
		dbl.setEnabled( false );
		repaint();
		return ret;
	}

	/**
	 * Play the Dealers hand and decide who won.
	 */
	private void playDealer()
	{
		dealer.play( deck );
	}
	
	/**
	 * Determine who the winner of the hand is.
	 * Passes the message to the winningsPanel to update the
	 * players winnings
	 */
	private void determineWinner()
	{
		if ( dealer.getTotal() > 21 || dealer.getTotal() < player.getTotal() ) {
			String msg = null;
			if ( dealer.getTotal() > 21 ) msg = "Dealer Busted.";
			winningsPanel.updateWinnings( player, msg );
		}
		else if ( dealer.getTotal() > player.getTotal() ) {
			winningsPanel.updateWinnings( dealer, null );
		}
		else {
			winningsPanel.updateWinnings( null, "Push." );
		}
		deal.setEnabled( true );
		deal.requestFocus();
		hit.setEnabled( false );
		stay.setEnabled( false );
		dbl.setEnabled( false );
		repaint();
	}

	/**
	 * Reset the hands on the board for a new Hand.
	 * Note that the Deck class automatically reshuffles
	 * if there aren't enough cards to play a hand.
	 */
	private void redeal()
	{
		player.newHand();

		dealer.newHand();

		deal.setEnabled( false );
		hit.setEnabled( true );
		stay.setEnabled( true );
		stay.requestFocus();
		dbl.setEnabled( true );

		winningsPanel.clearMessage();
		repaint();
	}
	
	/**
	 * Set up a new hand --
	 *   - Shuffle the deck if it is empty
	 *   - Draw initial cards for player and dealer
	 *   - Add the totals for each hand display
	 */
	public void newDeal()
	{
		if ( deck.isEmpty() ) {
			deck.shuffle();
		}
		redeal();
		
		player.addCard( deck.draw(), true );
		dealer.addCard( deck.draw(), false );
		player.addCard( deck.draw(), true );
		dealer.addCard( deck.draw(), true );
		player.setTotalText();
		
		dealer.setTotalText();
		
		repaint();
	}

	/**
	 * Generate an event for clicking on the "Deal" button
	 */
	public void clickDeal() {
		deal.doClick();
	}
	
	/**
	 * Generate an event for clicking on the "Hit" button
	 */
	public void clickHit() {
		hit.doClick();
	}
	
	/**
	 * Generate an event for clicking on the "Stay" button
	 */
	public void clickStay() {
		stay.doClick();
	}
	
	/**
	 * Generate an event for clicking on the "Double" button
	 */
	public void clickDouble() {
		dbl.doClick();
	}
}
