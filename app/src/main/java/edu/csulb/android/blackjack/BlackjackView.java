package edu.csulb.android.blackjack;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by FelipeGibran on 4/21/2015.
 */
public class BlackjackView extends SurfaceView implements SurfaceHolder.Callback {

	//
	private Card card;
	//
	private GameThread gameThread;
	private SurfaceHolder holder;

	private int screenWidth;
	private int screenHeight;

	public BlackjackView(Context context) {
		super(context);
		//
		card = new Card('C', 'A');
		card.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.card_deck));
		//
		holder = getHolder();
		holder.addCallback(this);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		screenWidth = this.getWidth();
		screenHeight = this.getHeight();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}

	/**
	 * Start or resume the game.
	 */
	public void resume() {
		gameThread = new GameThread(holder, this);
		gameThread.setRunning(true);
		gameThread.start();
	}

	/**
	 * Pause the game loop
	 */
	public void pause() {
		gameThread.setRunning(false);
		boolean retry = true;
		while (retry) {
			try {
				gameThread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
	}

	@Override
	public void draw(Canvas canvas) {
		card.draw(canvas);
	}
}
