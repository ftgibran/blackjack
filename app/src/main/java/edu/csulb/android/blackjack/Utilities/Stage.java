package edu.csulb.android.blackjack.Utilities;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FelipeGibran on 4/18/2015.
 */
public abstract class Stage extends SurfaceView implements SurfaceHolder.Callback, Renderable {

	protected Context context;

	private List<Renderable> listeners;

	protected RenderThread renderThread;
	protected UpdateThread updateThread;

	protected SurfaceHolder holder;

	public int screenWidth;
	public int screenHeight;

	public Stage(Context context) {
		super(context);
		this.context = context;
		this.listeners = new ArrayList<>();
		holder = getHolder();
		holder.addCallback(this);
	}

	@Override
	public abstract void surfaceCreated(SurfaceHolder holder);

	@Override
	public abstract void surfaceChanged(SurfaceHolder holder, int format, int width, int height);

	@Override
	public abstract void surfaceDestroyed(SurfaceHolder holder);

	/**
	 * Start or resume the game.
	 */
	public void resume() {
		renderThread = new RenderThread(holder, this);
		renderThread.setRunning(true);
		renderThread.start();

		updateThread = new UpdateThread(this);
		updateThread.setRunning(true);
		updateThread.start();
	}

	/**
	 * Pause the game loop
	 */
	public void pause() {
		renderThread.setRunning(false);
		updateThread.setRunning(false);
		boolean retry = true;
		while (retry) {
			try {
				renderThread.join();
				updateThread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
	}

	@Override
	public void update()
	{
		for(Renderable r:listeners)
			r.update();
	}

	@Override
	public void render(Canvas canvas)
	{
		for(Renderable r:listeners)
			r.render(canvas);
	}

	public List<Renderable> getListeners() {
		return listeners;
	}

	public UpdateThread Game() {
		return updateThread;
	}

	public RenderThread Render() {
		return renderThread;
	}
}
