package edu.csulb.android.blackjack;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by FelipeGibran on 4/18/2015.
 */
public class Background extends Sprite {

	public Background(Bitmap bitmap) {
		super(bitmap);
	}

	public void update()
	{
		super.update();
		if(x < -width) x=0;
	}
	public void draw(Canvas canvas)
	{
		super.draw(canvas);
		x += width;
		super.draw(canvas);
		x -= width;
	}
}