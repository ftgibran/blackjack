package edu.csulb.android.blackjack.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Stack;

import edu.csulb.android.blackjack.R;
import edu.csulb.android.blackjack.Utilities.GameObject;

/**
 * Created by FelipeGibran on 4/18/2015.
 */
public class Deck extends GameObject {

	private static final boolean DEFAULT_VISIBILITY = false;
	public static final int DEFAULT_DECK_DRAWABLE = R.drawable.card_deck;

	private Stack<Card> cardPool;

	public Deck()
	{
		setCardPool();
	}

	@Override
	public void render(Canvas canvas){
		float x = getX();
		float y =getY();
		for(int i=0; i < cardPool.size(); i += 5)
		{
			Card c = cardPool.get(i);
			c.setX(x);
			c.setY(y);
			c.render(canvas);
			x -= 1.5f;
			y -= 1.5f;
		}
	}

	@Override
	public void setBitmap(Bitmap bitmap)
	{
		width = 0;
		height = 0;
		for(Card c: cardPool)
			c.setBitmap(bitmap);
	}

	@Override
	public float getWidth() {
		if(cardPool.isEmpty())
			return 0;
		else
			return cardPool.get(0).getWidth();
	}

	@Override
	public float getHeight() {
		if(cardPool.isEmpty())
			return 0;
		else
			return cardPool.get(0).getHeight();
	}

	public Card pop()
	{
		return pop(DEFAULT_VISIBILITY);
	}
	public Card pop(boolean visibility)
	{
		Card c;

		cardPool.peek().flip();
		//BlackjackManager.sleep(1000);

		try {
			c = cardPool.pop();
		} catch (EmptyStackException e) {
			System.err.println("There is no more card left!");
			return null;
		}

		if (visibility)	c.show();
		else 			c.hide();

		return c;
	}

	public Card peek()
	{
		return cardPool.peek();
	}

	public void shuffle()
	{
		Collections.shuffle(cardPool);
	}

	public int getAmount()
	{
		return cardPool.size();
	}

	private void setCardPool()
	{
		cardPool = new Stack<>();
		for(int i=0;i<Card.RANKDB.length;i++)
			for(int j=0;j<Card.SUITDB.length;j++)
			{
				Card c = new Card(Card.RANKDB[i], Card.SUITDB[j], DEFAULT_VISIBILITY);
				cardPool.add(c);

				c.setCardID(cardPool.indexOf(c));
			}
	}

	public Card getCardByID(int id) { return cardPool.get(id); }

	public void showCards() {

	}
}
