package edu.csulb.android.blackjack.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Stack;

import edu.csulb.android.blackjack.Utilities.GameObject;

/**
 * Created by FelipeGibran on 4/18/2015.
 *
 */
public class Deck extends GameObject {

	private static final boolean DEFAULT_VISIBILITY = false;

	private Stack<Card> cardPool;

	public Deck()
	{
		cardPool = new Stack<>();
		setPool();
	}

	public Deck(boolean empty)
	{
		cardPool = new Stack<>();
		if(!empty)
			setPool();
	}

	@Override
	public void onRender(Canvas canvas){
		float x = getX();
		float y = getY();
		for(int i=0; i < cardPool.size()-1; i += 1)
		{
			Card c = cardPool.get(i);
			c.setX(x);
			c.setY(y);
			c.onRender(canvas);
			x -= c.getWidth() * 0.03;
		}

		Card last = peek();
		last.setX(x);
		last.setY(y);
		last.onRender(canvas);
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

	public void push(Card card) {
		cardPool.push(card);
	}

	public void push(Card[] cards) {
		for(Card c: cards)
			cardPool.push(c);
	}

	public void push(Deck deck) {
		cardPool.addAll(deck.getPool());
		deck.clear();
	}

	public Card pop()
	{
		Card c;

		try {
			c = cardPool.pop();
		} catch (EmptyStackException e) {
			System.err.println("There is no more card left!");
			return null;
		}

		return c;
	}

	public Card pop(boolean visibility)
	{
		Card c;

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

	public void clear()
	{
		cardPool.clear();
	}

	public void shuffle()
	{
		Collections.shuffle(cardPool);
	}

	public int getAmount()
	{
		return cardPool.size();
	}

	public void setPool()
	{
		for(int i=0;i<Card.RANKDB.length;i++)
			for(int j=0;j<Card.SUITDB.length;j++)
			{
				Card c = new Card(Card.RANKDB[i], Card.SUITDB[j], DEFAULT_VISIBILITY);
				c.setRotation(rotation);
				cardPool.add(c);

				c.setCardID(cardPool.indexOf(c));
			}
	}

	public Stack<Card> getPool() {
		return cardPool;
	}

	public Card getCardByID(int id) { return cardPool.get(id); }

}
