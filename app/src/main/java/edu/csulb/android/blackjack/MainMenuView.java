package edu.csulb.android.blackjack;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by FelipeGibran on 4/18/2015.
 */
public class MainMenuView extends SurfaceView implements SurfaceHolder.Callback {
	//
	private Background background;
	private Sprite logo;
	private TapString tapString;
	//
	private GameThread gameThread;
	private SurfaceHolder holder;

	private int screenWidth;
	private int screenHeight;

	public MainMenuView(Context context) {
		super(context);
		//

		//
		holder = getHolder();
		holder.addCallback(this);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		screenWidth = this.getWidth();
		screenHeight = this.getHeight();

		logo = new Sprite(BitmapFactory.decodeResource(getResources(), R.drawable.logo));
		logo.setX(screenWidth / 2.f - logo.getWidth() / 2.f);
		logo.setY(10);

		tapString = new TapString(BitmapFactory.decodeResource(getResources(), R.drawable.tap));
		tapString.setX(screenWidth / 2.f - tapString.getWidth() / 2.f);
		tapString.setY(this.getHeight() - tapString.getHeight());

		background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.bg));
		float scale = background.getWidth()/background.getHeight();
		background.setWidth(screenHeight*scale);
		background.setHeight(screenHeight);
		background.setVx(-.4f);
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
		background.draw(canvas);
		logo.draw(canvas);
		tapString.draw(canvas);
	}
}
