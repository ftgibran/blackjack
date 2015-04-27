package edu.csulb.android.blackjack.Utilities;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FelipeGibran on 4/18/2015.
 *
 */
public abstract class Stage extends SurfaceView implements SurfaceHolder.Callback, Renderable {

	protected Context context;

	private List<Renderable> listeners;

	protected RenderThread renderThread;
	protected UpdateThread updateThread;

	protected SurfaceHolder holder;

	public int screenWidth;
	public int screenHeight;

	/**
	 * Creates a Stage that manages the loop of the Game.
	 * It has two loops from UpdateThread Class that uses onUpdate() method and Render Thread Class that uses onRender() method.
	 * @param context Context
	 */
	public Stage(Context context) {
		super(context);
		this.context = context;
		this.listeners = new ArrayList<>();
		holder = getHolder();
		holder.addCallback(this);
	}

	/**
	 * Creates a Stage that manages the loop of the Game.
	 * It has two loops from UpdateThread Class that uses onUpdate() method and Render Thread Class that uses onRender() method.
	 * @param context Context
	 * @param attrs Attributes
	 */
	public Stage(Context context, AttributeSet attrs){
		super(context, attrs);
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

	/**
	 * This is a Thread from UpdateThread class responsible to onUpdate data.
	 * It has a loop of 60 fps.
	 */
	@Override
	public void onUpdate()
	{
		for(Renderable r:listeners)
			r.onUpdate();
	}

	/**
	 * This is a Thread from RenderThread class responsible to onRender bitmaps image.
	 * It has a loop of 60 fps.
	 * @param canvas Canvas.
	 */
	@Override
	public void onRender(Canvas canvas)
	{
		for(Renderable r:listeners)
			r.onRender(canvas);
	}

	/**
	 * Gets listeners of the UpdateThread and RenderThread.
	 * @return List of listeners.
	 */
	public List<Renderable> getListeners() {
		return listeners;
	}

	/**
	 * Gets UpdateThread.
	 * @return UpdateThreads.
	 */
	public UpdateThread getUpdateThread() {
		return updateThread;
	}

	/**
	 * Gets RenderThread.
	 * @return RenderThread.
	 */
	public RenderThread getRenderThread() {
		return renderThread;
	}

	/**
	 * Sleep on the UpdateThread.
	 * @param time sleep time in milliseconds.
	 */
	public void delay(long time)
	{
		try {
			updateThread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sleep on the RenderThread.
	 * @param time sleep time in milliseconds.
	 */
	public void RenderDelay(long time)
	{
		try {
			renderThread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
