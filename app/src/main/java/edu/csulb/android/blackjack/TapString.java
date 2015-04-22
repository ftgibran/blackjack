package edu.csulb.android.blackjack;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by FelipeGibran on 4/20/2015.
 */
public class TapString extends Sprite{

	public TapString(Bitmap bitmap) {
		super(bitmap);
	}

	private int alpha = 255;
	@Override
	public void draw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setAlpha(Math.abs(alpha));
		alpha -= 4;
		if(alpha < -255)
			alpha = 255;
		canvas.drawBitmap(bitmap, x, y, paint);
	}

	@Override
	public void update() {

	}
}
