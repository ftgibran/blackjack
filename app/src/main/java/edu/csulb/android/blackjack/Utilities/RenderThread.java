package edu.csulb.android.blackjack.Utilities;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by FelipeGibran on 4/18/2015.
 */
public class RenderThread extends Thread {

	private final static int MAX_FPS = 60; //desired fps
	private final static int FRAME_PERIOD = 1000 / MAX_FPS; // the frame period

	private long averageFPS;
	private boolean running = false;
	private SurfaceHolder surfaceHolder;
	private Stage stage;
	public static Canvas canvas;

	public RenderThread(SurfaceHolder surfaceHolder, Stage stage) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.stage = stage;
	}

	@Override
	public void run() {
		long startTime;
		long timeMillis;
		long waitTime;
		long totalTime = 0;
		int frameCount = 0;
		long targetTime = FRAME_PERIOD;

		while(isRunning()) {
			startTime = System.nanoTime();
			canvas = null;

			try {
				canvas = this.surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					stage.render(canvas);
				}

			}catch(Exception e) {}
			finally {
				if (canvas != null) {
					try {
						surfaceHolder.unlockCanvasAndPost(canvas);
					}catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}

			timeMillis = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime-timeMillis;

			try{
				this.sleep(waitTime);
			}catch (Exception e) {}

			totalTime += System.nanoTime()-startTime;
			frameCount++;
			if(frameCount == MAX_FPS) {
				averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
				frameCount = 0;
				totalTime = 0;
			}
		}
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public long getAverageFPS() {
		return averageFPS;
	}

	public void setAverageFPS(int averageFPS) {
		this.averageFPS = averageFPS;
	}
}
