package edu.csulb.android.blackjack.Game;

import android.graphics.Bitmap;

import java.util.Random;

import edu.csulb.android.blackjack.Utilities.GameObject;

/**
 * Created by FelipeGibran on 4/18/2015.
 */
public class Card extends GameObject {

	private int					cardID;

	private char				rank; // 'H','S','C','D'
	private char				suit; // 'A','2','3','4','5','6','7','8','9','T','J','Q','K'
	private boolean				visible;

	/**
	 * Default bitmap model of a card.
	 */
	private Bitmap              model;

	/**
	 * Standard rank structure of a Card Class.
	 */
	public static final char[]	RANKDB	= {'D', 'C', 'H', 'S'};
	/**
	 * Standard suit structure of a Card Class.
	 */
	public static final char[]	SUITDB	= {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};

	/**
	 * It is a random variable to card face off, so the cards may alter his face off between orange and blue color.
	 */
	int rand;

	/**
	 * Creates a unique card.
	 * @param r Rank of the card.
	 * @param s Suit of the card.
	 */
	public Card(char r, char s) {
		rank = r;
		suit = s;
		visible = true;

		Random random = new Random();
		rand = random.nextInt(2) + 2;
	}

	/**
	 * Creates a unique card.
	 * @param r Rank of the card.
	 * @param s Suit of the card.
	 * @param v Sets if the card is visible or not.
	 */
	public Card(char r, char s, boolean v) {
		rank = r;
		suit = s;
		visible = v;

		Random random = new Random();
		rand = random.nextInt(2)+2;
	}

	/**
	 * Sets bitmap image of the card.
	 * @param bitmap Card's Bitmap.
	 */
	public void setBitmap(Bitmap bitmap)
	{
		super.setBitmap(bitmap);

		width = this.width/(SUITDB.length+1);
		height = this.height/(RANKDB.length);

		model = bitmap;

		upadateBitmap();
	}

	/**
	 * Update Card's bitmap if you, for example, flip it.
	 */
	public void upadateBitmap()
	{
		int i = (getCardNumber()-1)*(int)width;
		int j = (getCardRank()-1)*(int)height;

		if(visible)
			this.bitmap = Bitmap.createBitmap(model,i,j,(int)width,(int)height);
		else
			this.bitmap = Bitmap.createBitmap(model, 13*(int)width, rand*(int)height, (int)width, (int)height);
	}

	/**
	 * Shows the card as a String using this format: [R|S].
	 * @return Card in String format.
	 */
	@Override
	public String toString() {
		return "[" + rank + "|" + suit + "]";
	}

	/**
	 * Gets the standard value of a card. (e.g. A=1,2=2,...,Q=12,K=13)
	 * @return Card 'value'
	 */
	public int getCardNumber()
	{
		int n = 1;
		while(Card.SUITDB[n - 1] != suit)
			n++;
		return(n);
	}

	/**
	 * Gets the Rank of a card in Integer format.
	 * 1 -> Diamonds
	 * 2 -> Clubs
	 * 3 -> Hearts
	 * 4 -> Spades
	 * @return An Integer between 1 and 4.
	 */
	public int getCardRank()
	{
		int n = 1;
		while(Card.RANKDB[n - 1] != rank)
			n++;
		return(n);
	}

	/**
	 * Gets Rank of a card in Character format
	 * 'D' -> Diamonds
	 * 'C' -> Clubs
	 * 'H' -> Hearts
	 * 'S' -> Spades
	 * @return 'D','C','H', or 'S'
	 */
	public char getRank()
	{
		return rank;
	}

	/**
	 * Gets Suit of a card in Character format
	 * @return 'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'
	 */
	public char getSuit()
	{
		return suit;
	}

	/**
	 * Gets the card ID that you may set.
	 * @return Card ID.
	 */
	public int getCardID()
	{
		return cardID;
	}

	/**
	 * Sets Card ID.
	 * @param id Card ID.
	 */
	public void setCardID(int id)
	{
		cardID = id;
	}

	/**
	 * Gets if Card is flipped or not.
	 * @return Card visibility.
	 */
	public boolean isVisible()
	{
		return visible;
	}

	/**
	 * Shows card.
	 * Note: it does anything if the card is already visible.
	 */
	public void show()
	{
		visible = true;
		upadateBitmap();
	}

	/**
	 * Hides card.
	 * Note: it does anything if the card is already not visible.
	 */
	public void hide()
	{
		visible = false;
		upadateBitmap();
	}

	/**
	 * Flips the card in opposite side.
	 */
	public void flip()
	{
		visible = !visible;
		upadateBitmap();
	}

}
