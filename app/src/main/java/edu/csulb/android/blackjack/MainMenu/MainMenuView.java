package edu.csulb.android.blackjack.MainMenu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import edu.csulb.android.blackjack.R;
import edu.csulb.android.blackjack.Utilities.GameObject;
import edu.csulb.android.blackjack.Utilities.Stage;

/**
 * Created by FelipeGibran on 4/18/2015.
 */
public class MainMenuView extends Stage {
	//
	private GameObject background;
	private GameObject blackjackLogo;
	private GameObject tapString;
	//

	public MainMenuView(Context context) {
		super(context);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		screenWidth = this.getWidth();
		screenHeight = this.getHeight();

		background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.bg));
		background.setRenderListener(this);

		blackjackLogo = new BlackjackLogo(BitmapFactory.decodeResource(getResources(), R.drawable.logo));
		blackjackLogo.setRenderListener(this);

		tapString = new TapString(BitmapFactory.decodeResource(getResources(), R.drawable.tap));
		tapString.setRenderListener(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}

	public class Background extends GameObject {

		static final float DEFAULT_VX = -.5f;
		float scale = width/height;

		public Background(Bitmap bitmap) {
			super(bitmap);
			setWidth(screenHeight*scale);
			setHeight(screenHeight);
			vx = DEFAULT_VX;
		}

		@Override
		public void update()
		{
			super.update();
			if(x < -width) x=0;
		}

		@Override
		public void render(Canvas canvas)
		{
			super.render(canvas);
			x += width;
			super.render(canvas);
			x -= width;
		}
	}

	public class BlackjackLogo extends GameObject
	{
		public BlackjackLogo(Bitmap bitmap) {
			super(bitmap);
			x = screenWidth/2 - width/2;
			y = 10;
		}
	}

	public class TapString extends GameObject {

		int alpha = 255;

		public TapString(Bitmap bitmap) {
			super(bitmap);
			x = screenWidth/2 - width/2;
			y = screenHeight - height;
		}

		@Override
		public void render(Canvas canvas) {
			Paint paint = new Paint();
			paint.setAlpha(Math.abs(alpha));
			if(alpha < -255)
				alpha = 255;
			canvas.drawBitmap(bitmap, x, y, paint);
		}

		@Override
		public void update() {
			alpha -= 4;
		}
	}
}
