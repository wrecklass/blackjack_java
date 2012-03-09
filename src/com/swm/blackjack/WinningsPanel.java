package com.swm.blackjack;

import static com.swm.blackjack.BlackJack.darkGreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * WinningsPanel - contains the information about bets and 
 * amounts won/lost in the game.
 *
 * @author smartin 
 */
class WinningsPanel extends JPanel implements ItemListener
{
    private static final long serialVersionUID = 1L;
	private static final Integer DEFAULT_WINNINGS = 1500;
	private static final Integer GAP = 20, TEXT_WIDTH = 20;
	private static final Font winningsFont = new Font( "Sans Serif", Font.BOLD,
			14 );
	private JTextField winnings = new JTextField( TEXT_WIDTH );
	private JTextField message = new JTextField( TEXT_WIDTH );
	private JPanel betPanel = new JPanel();
	private Integer[] betList = {25,50,100,200,300,400,500};
	private JComboBox betBox = new JComboBox(betList);
	private Integer bet = 100, winningsAmount = DEFAULT_WINNINGS;
	private boolean dbled = false;

	/**
	 * Constructor for the Winnings panel, add our buttons and labels all here.
	 */
	public WinningsPanel()
	{
		JLabel betLabel = new JLabel( "Players Bet: " );

		setLayout( new BorderLayout( GAP, GAP ) );

		setBackground( darkGreen );

		winnings.setFont( winningsFont );
		winnings.setHorizontalAlignment( JTextField.CENTER );
		winnings.setText( "Winnings = $" + winningsAmount );

		message.setFont( winningsFont );
		message.setHorizontalAlignment( JTextField.CENTER );

		betLabel.setFont( winningsFont );
		betLabel.setBackground( darkGreen );
		betLabel.setForeground( Color.BLACK );

		betPanel.add( betLabel );

		betBox.addItemListener(this);
		betBox.setSelectedIndex(2);
		betPanel.add( betBox );
		
		add( betPanel, BorderLayout.NORTH );
		add( winnings, BorderLayout.CENTER );
		add( message, BorderLayout.SOUTH );
	}

	/**
	 * Enable and Disable the betting buttons so the player cannot enter a new
	 * bet during play.
	 * @param e true to enable the buttons, false otherwise
	 */
	public void enableBetPanel(boolean e)
	{
		betBox.setEnabled(e);
	}

	/**
	 * Clear the Message Label
	 */
	public void clearMessage()
	{
		message.setText( "" );
	}

	/**
	 * Initialize the panel for a new game
	 */
	public void init()
	{
		bet = 100;
		dbled = false;
		betBox.setSelectedIndex(2);
		winningsAmount = DEFAULT_WINNINGS;
		winnings.setText( "Winnings = $" + winningsAmount );
		message.setText( "" );
	}

	/**
	 * Standard event listener call back with the information about what button
	 * has been pressed by the user.
	 * @param e the <code>ItemEvent</code> with the Button that has been
	 *            selected
	 */
	public void itemStateChanged(ItemEvent e)
	{
		Object object = e.getSource();
		if ( object == betBox) {
			bet = (Integer)betBox.getSelectedItem();
		}
		dbled = false;
	}

	/**
	 * Specify that the player has doubled her bet
	 * @param dbl a <code>boolean</code> that specifies when bet doubling is
	 *            on or off.
	 */
	public void doubled(boolean dbl)
	{
		if ( dbl ) {
			if ( dbled == false ) {
				dbled = true;
				bet = bet * 2;
			}
		}
		else if ( dbled == true ) {
			dbled = false;
			bet = bet / 2;
		}
	}

	/**
	 * update the Hand winnings, displaying appropriate messages
	 * @param winner a <code>Hand</code> object specifying who won (dealer
	 *            or player)
	 * @param msg An alternative message to display for special cases
	 */
	public void updateWinnings(Hand winner, String msg)
	{
		String theMsg = "";
		if ( winner == null ) {
			theMsg = msg;
		}
		else {
			winningsAmount = winner.pay( winningsAmount, bet );
			if ( msg != null )
				theMsg = msg;
			else theMsg = winner.toString() + " wins.";
		}
		winnings.setText( "Winnings = $" + winningsAmount );
		message.setText( theMsg );
	}

	/**
	 * Provides the winnings value for external consumption
	 * @return <code>Integer</code>the current winnings amount
	 */
	public Integer getWinnings()
	{
		return winningsAmount;
	}
}
