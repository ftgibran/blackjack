package edu.csulb.android.blackjack;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by FelipeGibran on 4/21/2015.
 */
public class BlackjackView extends SurfaceView implements SurfaceHolder.Callback {

	private Activity activity;
	private MainThread thread;
	public BlackjackView(Activity activity)
	{
		super(activity);
		this.activity = activity;
		getHolder().addCallback(this);
		thread = new MainThread(getHolder(), this);
		//make gamePanel focusable so it can handle events
		setFocusable(true);
	}

	@Override
	public void draw(Canvas canvas) {
		if (canvas == null) return;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		System.out.println("Hello world!");
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

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		return super.onTouchEvent(event);
	}
}
