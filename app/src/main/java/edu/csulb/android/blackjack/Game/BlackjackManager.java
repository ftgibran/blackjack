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
	private final static int DEFAULT_SLEEP_TIME = 600;

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

	private Prompt prompt;

	public boolean end = false;

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
		prompt = new Prompt();
		prompt.setX(30);
		prompt.setY(stage.screenHeight /2 - 50);
		prompt.setRenderListener(stage);

		deck = new Deck();
		deck.shuffle();
		deck.setBitmap(cardModel);
		deck.setX(stage.screenWidth - 300);
		deck.setY(100);
		deck.setRenderListener(stage);

		dealer = new Player(DEFAULT_DEALER_NAME); //this is the dealer
		dealer.setBitmap(cardModel);
		dealer.setX(stage.screenWidth/2);
		dealer.setY(100);
		dealer.setRenderListener(stage);

		player = new Player(DEFAULT_PLAYER_NAME);
		player.setX(stage.screenWidth/2);
		player.setY(stage.screenHeight - deck.getHeight() -  200);
		player.setRenderListener(stage);
	}

	public void deal()
	{
		dealer.pickCard(pop_ani(true));
		dealer.pickCard(pop_ani(false));
		player.pickCard(pop_ani(true));
		player.pickCard(pop_ani(true));

		if(player.getAmount() == MAX_BJ_VALUE)
		{
			prompt.show("Blackjack! Instantly won!");
			plusSleep();
			//setGame();
		}
	}

	public void hit()
	{
		player.pickCard(pop_ani(true));

		if(player.getAmount() > MAX_BJ_VALUE)
		{
			prompt.show("You have bursted! You lose");
			plusSleep();
			//setGame();
		}
	}

	public void stand()
	{
		prompt.show("The dealer will now show his card!");

		for(int i = 0; i<3; i++) plusSleep();

		dealer.showAllCards();
		sleep();

		while (dealer.getAmount() < MIN_DEALER_AMOUNT)
			dealer.pickCard(pop_ani(true));

		if ((player.getAmount() >= dealer.getAmount() || dealer.getAmount() > MAX_BJ_VALUE) && player.getAmount() != MAX_BJ_VALUE)
		{
			prompt.show("You Won!");
			plusSleep();
			//setGame();
		} else
		{
			prompt.show("You Lose!");
			plusSleep();
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

	/**
	 * This is the main function of the game. It controls all the game progress!
	 */
	@Override
	public void onUpdate() {

		/*
		 * That is the first part of the game
		 * It shows all cards in game, and asks for each if they want another card
		 */

		/*
		 * That is the first part of the game
		 * It shows all cards in game, and asks for each if they want another card
		 */
		/*boolean retry;

		do {
			retry = false;

			this.showBoard();
			this.cardRequest();

			for (int i = 0; i < player.size(); i++)
				retry |= player.isRequest(); //verifies if all players have denied a card request
		} while (retry); //repeats while all players haven't denied a card request

		/*
		 * That is the second part of the game
		 * Now it shows dealer cards, and he will request more cards if he needs
		 */
		/*BlackjackManager.sleep(DEFAULT_SLEEP_TIME);
		BlackjackManager.show("The dealer will now show his card!");
		BlackjackManager.loadingSleep();

		dealer.showAllCards();
		this.showBoard();

		while (dealer.getAmount() < MIN_DEALER_AMOUNT) {
			BlackjackManager.sleep(DEFAULT_SLEEP_TIME);
			BlackjackManager.show("Dealer will pick another card!");
			BlackjackManager.loadingSleep();
			dealer.pickCard(deck.getNextCard(true));
			this.showBoard();
		}*/

		/*
		 * That is the third part of the game
		 * Now it will set all the winner in a list and then it will be shown
		 */
		/*for (Player p : playersList)
			if ((p.getAmount() >= dealer.getAmount() || dealer.getAmount() > MAX_BJ_VALUE) && p.getAmount() != MAX_BJ_VALUE)
				winnerList.add(p);

		playersList.clear();

		JackGame.sleep(DEFAULT_SLEEP_TIME);
		JackGame.show(DASH_LINE);
		JackGame.show("End of match!");
		JackGame.show(DASH_LINE);
		JackGame.show(DOUBLE_LINE_SPLIT);
		JackGame.show("Players who won this match:");

		if (!winnerList.isEmpty())
			for (Player p : winnerList)
				JackGame.show(p.getNickname());
		else
			JackGame.show("No one has won!");

		JackGame.show(DOUBLE_LINE_SPLIT);

		/*
		 * That is the fourth part of the game
		 * Now it will reset all attributes for a rematch
		 */
		/*playersList = (ArrayList<Player>) registeredPlayersList.clone();
		winnerList.clear();
		loserList.clear();

		for (Player p : playersList)
			p.resetPlayer();
		dealer.resetPlayer();

		this.callMenu();*/
	}

	/**
	 * This method shows the current board of a match
	 */
	/*private void showBoard()
	{
		JackGame.show(DOUBLE_LINE_SPLIT);
		JackGame.show(CURRENT_BOARD);
		JackGame.show(DOUBLE_LINE_SPLIT);

		/*
		 * Prints all players' cards
		 */
		/*for(int i=0;i<playersList.size();i++)
		{
			Card.print(playersList.get(i).getPlayerCards());
			JackGame.show(playersList.get(i).getNickname() + "'s amount: " + playersList.get(i).getAmount());

			if(playersList.get(i).getAmount() > JackGame.MAX_BJ_VALUE) //if someone has bursted
			{
				JackGame.sleep(DEFAULT_SLEEP_TIME);
				JackGame.show(playersList.get(i).getNickname() + " has bursted!");
				JackGame.sleep(DEFAULT_SLEEP_TIME);
				JackGame.show(playersList.get(i).getNickname() + " is eliminated! ");
				loserList.add(playersList.get(i));
				playersList.remove(i--);
			} else if(playersList.get(i).getAmount() == JackGame.MAX_BJ_VALUE) //if someone gets 21
				if(playersList.get(i).isRequest())
				{
					JackGame.sleep(DEFAULT_SLEEP_TIME);
					JackGame.show(playersList.get(i).getNickname() + " got 21!");
					JackGame.sleep(DEFAULT_SLEEP_TIME);
					JackGame.show(playersList.get(i).getNickname() + "  has won! ");
					playersList.get(i).dennyCard();
					winnerList.add(playersList.get(i));
				} else
					JackGame.show(playersList.get(i).getNickname() + " is a winner!");

			JackGame.show(SINGLE_LINE_SPLIT);
			JackGame.sleep(DEFAULT_SLEEP_TIME);
		}
		/*
		 * Prints all dealer's cards
		 */
	/*	Card.print(dealer.getPlayerCards());
		JackGame.show(dealer.getNickname() + "'s amount: " + dealer.getAmount());

		if(dealer.getAmount() > JackGame.MAX_BJ_VALUE) //if dealer has bursted
		{
			JackGame.sleep(DEFAULT_SLEEP_TIME);
			JackGame.show(dealer.getNickname() + " has bursted!");
		}

		JackGame.show(DOUBLE_LINE_SPLIT);
		JackGame.sleep(DEFAULT_SLEEP_TIME);
	}*/

	/**
	 * This method asks if a player wants a new card
	 */
	/*private void cardRequest() throws IOException
	{
		String opt;
		for(int i=0;i<playersList.size();i++) if(playersList.get(i).isRequest())
		{
			JackGame.show(DOUBLE_LINE_SPLIT);

			JackGame.show(playersList.get(i).getNickname() + ", do you want another card? ("+YES+"/"+NO+")");

			opt = inputString(new String[]{YES,NO});

			if(opt.equalsIgnoreCase(YES))
			{
				playersList.get(i).pickCard(deck.getNextCard(true));
				playersList.get(i).allowCard();
			}
			if(opt.equalsIgnoreCase(NO))
				playersList.get(i).dennyCard();

		}
	}*/

	/**
	 * Displays a message
	 * @param m The message
	 */
	public static void show(Object m)
	{
		System.out.println(m);
	}

	/**
	 * Displays a message in the same line
	 * @param m The message
	 */
	public static void showInLine(Object m)
	{
		System.out.print(m);
	}

	public void sleep() {
		stage.delay(DEFAULT_SLEEP_TIME);
	}

	public void plusSleep()
	{
		stage.delay(3*DEFAULT_SLEEP_TIME);
	}

	public void longSleep()
	{
		stage.delay(6*DEFAULT_SLEEP_TIME);
	}

	public boolean end()
	{
		return end;
	}
}
