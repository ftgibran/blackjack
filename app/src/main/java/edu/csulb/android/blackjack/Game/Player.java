package edu.csulb.android.blackjack.Game;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

import edu.csulb.android.blackjack.Utilities.GameObject;

/**
 * Created by FelipeGibran on 4/22/2015.
 */
public class Player extends GameObject {
	private final String DEFAULT_NICKNAME = "Default Player";

	private int amount;
	private String nickname = DEFAULT_NICKNAME;

	private boolean request;

	private List<Card> cardsInHand = new ArrayList<Card>();

	public Player()
	{
		// TODO Auto-generated constructor stub
		request = true;
	}

	public Player(String nickname)
	{
		// TODO Auto-generated constructor stub
		this.nickname = nickname;
		request = true;
	}

	@Override
	public void render(Canvas canvas){
		float x = getX();
		float y = getY();

		for (Card c : cardsInHand) {
			c.setX(x);
			c.setY(y);
			c.render(canvas);
			x += 22;
		}
	}

	@Override
	public float getWidth() {
		if(cardsInHand.isEmpty())
			return 0;
		else
			return cardsInHand.get(0).getWidth();
	}

	@Override
	public float getHeight() {
		if(cardsInHand.isEmpty())
			return 0;
		else
			return cardsInHand.get(0).getHeight();
	}

	public int getAmount()
	{
		int aCount=0;
		amount=0;

		for(Card c : cardsInHand)
			if(c.isVisible() && c.getSuit() != 'A')
				amount += Math.min(c.getCardNumber(), BlackjackManager.MAX_CARD_VALUE);
			else if(c.isVisible() && c.getSuit() == 'A') aCount++;

		for(int i=0;i<aCount;i++)
			if(amount + BlackjackManager.MAX_A_VALUE > BlackjackManager.MAX_BJ_VALUE)
				amount += BlackjackManager.MIN_A_VALUE;
			else
				amount += BlackjackManager.MAX_A_VALUE;

		return amount;
	}

	public void pickCard(Card c)
	{
		cardsInHand.add(c);
	}

	public Card[] getPlayerCards()
	{
		Card[] playerCards = new Card[cardsInHand.size()];
		playerCards = cardsInHand.toArray(playerCards);
		return playerCards;
	}

	public void showAllCards()
	{
		for(Card c : cardsInHand)
			c.show();
	}

	public void hideAllCards()
	{
		for(Card c : cardsInHand)
			c.hide();
	}

	public void invertAllCards()
	{
		for(Card c : cardsInHand)
			c.flip();
	}

	public Card[] clearCards()
	{
		Card[] discardedCards = getPlayerCards();
		cardsInHand.clear();
		return discardedCards;
	}

	public void resetPlayer()
	{
		request = true;
		clearCards();
	}

	public void setNickname (String nickname) { this.nickname = nickname; }
	public String getNickname () { return nickname; }

	public void allowCard () { request=true; }
	public void dennyCard () { request=false; }
	public boolean isRequest () { return request; }
}
