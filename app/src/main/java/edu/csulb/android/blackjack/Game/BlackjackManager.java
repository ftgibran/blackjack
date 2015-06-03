package edu.csulb.android.blackjack.Game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import edu.csulb.android.blackjack.R;
import edu.csulb.android.blackjack.Utilities.GameObject;
import edu.csulb.android.blackjack.Utilities.Stage;

/**
 * Created by FelipeGibran on 4/22/2015.
 */
public class BlackjackManager extends GameObject{
	private final static int DEFAULT_SLEEP_TIME = 500;

	public final static int MAX_CARD_VALUE = 10; 	//the maximum value of a card in Black Jack (excluding aces!)
	public final static int MIN_A_VALUE = 1; 		//the minimum value of a aces card
	public final static int MAX_A_VALUE = 11;		//the maximum value of a aces card
	public final static int MAX_BJ_VALUE = 21;		//amount value that it doesn't burst a player
	public final static int MIN_DEALER_AMOUNT = 17; //the minimum amount value that allows dealer to stop

	public final static String DEFAULT_PLAYER_NAME = "Player";
	public final static String DEFAULT_DEALER_NAME = "Dealer";

	private Stage stage;

	private Bitmap cardModel;

	private Player dealer;
	private Player player;
	private Deck deck;
	private Deck trash;

	private Prompt prompt;

	/**
	 * Defines the main attributes in game like: dealer and players
	 * Also, it calls main menu after everything is set.
	 */
	public BlackjackManager(Stage stage)
	{
		this.stage = stage;
		cardModel = BitmapFactory.decodeResource(stage.getResources(), R.drawable.card_deck);
		setGame();
	}

	public void setGame()
	{
		deck = new Deck();
		deck.shuffle();
		deck.setBitmap(cardModel);
		deck.setX(stage.screenWidth - (int)(deck.getWidth() * 1.5));
		deck.setY(100);
		deck.setRenderListener(stage);

		trash = new Deck(true);

		prompt = new Prompt();
		prompt.setX((int)(deck.getWidth() * 1.5));
		prompt.setY(stage.screenHeight - 20);
		prompt.setRenderListener(stage);

		dealer = new Player(DEFAULT_DEALER_NAME); //this is the dealer
		dealer.setBitmap(cardModel);
		dealer.setX(deck.getWidth());
		dealer.setY(100);
		dealer.setRenderListener(stage);

		player = new Player(DEFAULT_PLAYER_NAME);
		player.setX(deck.getWidth());
		player.setY(dealer.getY() + (int)(deck.getHeight() * 1.5));
		player.setRenderListener(stage);
	}

	public boolean restart()
	{
		plusSleep();
		Card[] cards1 = dealer.clearCards();
		Card[] cards2 = player.clearCards();

		for(Card c: cards1)
			c.hide();

		for(Card c: cards2)
			c.hide();

		trash.push(cards1);
		trash.push(cards2);

		if(deck.getAmount() <= 10)
		{
			prompt.show("There are only " + deck.getAmount() + " cards in Deck.");
			plusSleep();
			prompt.show("The dealer will restore the Deck!");
			deck.push(trash);
			deck.shuffle();
		}

		return true;
	}

	public boolean deal()
	{
		dealer.pickCard(pop_ani(true));
		dealer.pickCard(pop_ani(false));
		player.pickCard(pop_ani(true));
		player.pickCard(pop_ani(true));

		if(player.getAmount() == MAX_BJ_VALUE)
		{
			prompt.show("Blackjack! Instant won!");
			return restart();
		}
		return false;
	}

	public boolean hit()
	{
		player.pickCard(pop_ani(true));

		if(player.getAmount() == MAX_BJ_VALUE) {
			prompt.show("Blackjack!");
			return restart();
		}
		else if(player.getAmount() > MAX_BJ_VALUE)
		{
			prompt.show("You have bursted! You lose");
			return restart();
		}

		return false;
	}

	public void stand()
	{
		prompt.show("The dealer will now show his card!");

		plusSleep();

		dealer.showAllCards();
		sleep();

		while (dealer.getAmount() < MIN_DEALER_AMOUNT)
			dealer.pickCard(pop_ani(true));

		if ((player.getAmount() >= dealer.getAmount() || dealer.getAmount() > MAX_BJ_VALUE) && player.getAmount() != MAX_BJ_VALUE)
		{
			prompt.show("You Won!");
			restart();
		} else
		{
			prompt.show("You Lose!");
			restart();
		}
	}

	public Card pop_ani(boolean flip)
	{
		sleep();

		if(flip)
		{
			deck.peek().flip();
			sleep();
		}

		return deck.pop();
	}

	public class Prompt extends GameObject {

		String text;
		Paint paint;
		int alpha = 255;

		public Prompt() {
			super();
			text = "";
			paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		}

		public String getText()
		{
			return this.text;
		}

		public void show(String text)
		{
			this.text = text;
			this.alpha = 255;
		}

		public void clear() {
			this.text = "";
		}

		@Override
		public void onUpdate() {
			super.onUpdate();

			if(text.equals("")) return;
			if(alpha == 255) longSleep();

			alpha -= 1;

			if(alpha <= 0)
				clear();
		}

		@Override
		public void onRender(Canvas canvas) {
			paint.setColor(Color.WHITE);
			paint.setTextSize(28);
			paint.setShadowLayer(3f, 0f, 3f, Color.BLACK);
			paint.setAlpha(alpha);
			canvas.drawText(text, getX(), getY(), paint);
		}
	}

	public void sleep() {
		stage.delay(DEFAULT_SLEEP_TIME);
	}

	public void plusSleep()
	{
		stage.delay(4*DEFAULT_SLEEP_TIME);
	}

	public void longSleep()
	{
		stage.delay(6*DEFAULT_SLEEP_TIME);
	}

}
