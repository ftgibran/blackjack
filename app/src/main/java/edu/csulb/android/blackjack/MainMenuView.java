package edu.csulb.android.blackjack;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by FelipeGibran on 4/18/2015.
 */
public class MainMenuView extends SurfaceView implements SurfaceHolder.Callback{

	private MainThread thread;
	private Background bg;
	private Sprite logo;
	private TapString tap;

	public MainMenuView(Activity activity)
	{
		super(activity);
		getHolder().addCallback(this);
		thread = new MainThread(getHolder(), this);
		//make gamePanel focusable so it can handle events
		setFocusable(true);
	}
	@Override
	public void draw(Canvas canvas) {
		if (canvas == null) return;
		bg.draw(canvas);
		logo.draw(canvas);
		tap.draw(canvas);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		final float MIDDLE = this.getWidth() / 2.f;
		final float BOTTOM = this.getHeight();

		logo = new Sprite(BitmapFactory.decodeResource(getResources(), R.drawable.logo));
		logo.setX(MIDDLE - logo.getWidth() / 2.f);
		logo.setY(10);

		tap = new TapString(BitmapFactory.decodeResource(getResources(), R.drawable.tap));
		tap.setX(MIDDLE - tap.getWidth() / 2.f);
		tap.setY(BOTTOM - tap.getHeight());

		bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.bg));
		float scale = 4.44f;
		bg.setWidth(this.getHeight()*scale);
		bg.setHeight(this.getHeight());
		bg.setVx(-.4f);

		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder){
		boolean retry = true;
		while(retry)
		{
			try{
				thread.setRunning(false);
				thread.join();
			}catch(InterruptedException e){e.printStackTrace();}
			retry = false;
		}
	}

}
