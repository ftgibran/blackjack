package edu.csulb.android.blackjack.Game;

import android.graphics.Bitmap;

import java.util.Random;

import edu.csulb.android.blackjack.Utilities.GameObject;

/**
 * Created by FelipeGibran on 4/18/2015.
 */
public class Card extends GameObject {

	private int					cardID;

	private char				rank;							// 'H','S','C','D'
	private char				suit;							// 'A','2','3','4','5','6','7','8','9','T','J','Q','K'
	private boolean				visible;
	private Bitmap              model;

	public static final char[]	RANKDB	= {'D', 'C', 'H', 'S'};
	public static final char[]	SUITDB	= {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};

	int rand;

	public Card(char r, char s) {
		rank = r;
		suit = s;
		visible = true;

		Random random = new Random();
		rand = random.nextInt(2) + 2;
	}

	public Card(char r, char s, boolean v) {
		rank = r;
		suit = s;
		visible = v;

		Random random = new Random();
		rand = random.nextInt(2)+2;
	}

	public void setBitmap(Bitmap bitmap)
	{
		super.setBitmap(bitmap);

		width = this.width/(SUITDB.length+1);
		height = this.height/(RANKDB.length);

		model = bitmap;

		upadateBitmap();
	}

	public void upadateBitmap()
	{
		int i = (getCardNumber()-1)*(int)width;
		int j = (getCardRank()-1)*(int)height;

		if(visible)
			this.bitmap = Bitmap.createBitmap(model,i,j,(int)width,(int)height);
		else
			this.bitmap = Bitmap.createBitmap(model, 13*(int)width, rand*(int)height, (int)width, (int)height);
	}

	@Override
	public String toString() {
		return "[" + rank + "|" + suit + "]";
	}

	public int getCardNumber()
	{
		int n = 1;
		while(Card.SUITDB[n - 1] != suit)
			n++;
		return(n);
	}

	public int getCardRank()
	{
		int n = 1;
		while(Card.RANKDB[n - 1] != rank)
			n++;
		return(n);
	}

	public char getRank()
	{
		return rank;
	}

	public char getSuit()
	{
		return suit;
	}

	public int getCardID()
	{
		return cardID;
	}

	public void setCardID(int id)
	{
		cardID = id;
	}

	public boolean isVisible()
	{
		return visible;
	}

	public void show()
	{
		visible = true;
		upadateBitmap();
	}

	public void hide()
	{
		visible = false;
		upadateBitmap();
	}

	public void flip()
	{
		visible = !visible;
		upadateBitmap();
	}

}
