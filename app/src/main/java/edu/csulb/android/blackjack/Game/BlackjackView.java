package edu.csulb.android.blackjack.Game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.SurfaceHolder;

import edu.csulb.android.blackjack.R;
import edu.csulb.android.blackjack.Utilities.GameObject;
import edu.csulb.android.blackjack.Utilities.Stage;

/**
 * Created by FelipeGibran on 4/21/2015.
 *
 */
public class BlackjackView extends Stage {

	public BlackjackManager game;

	public BlackjackView(Context context) {
		super(context);
	}

	public BlackjackView(Context context, AttributeSet attrs){
		super(context, attrs);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		screenWidth = this.getWidth();
		screenHeight = this.getHeight();

		GameObject background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.game_bg));
		GameObject texture1 = new Texture1(BitmapFactory.decodeResource(getResources(), R.drawable.stamp));
		GameObject texture2 = new Texture2(BitmapFactory.decodeResource(getResources(), R.drawable.coins));

		background.setRenderListener(this);
		//texture1.setRenderListener(this);
		//texture2.setRenderListener(this);

		game = new BlackjackManager(this);
		game.setRenderListener(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}

	class Background extends GameObject{

		float scale = width/height;

		public Background(Bitmap bitmap) {
			super(bitmap);
			setWidth(screenHeight*scale);
			setHeight(screenHeight);

			x = screenWidth/2 - width/2;
		}
	}

	class Texture1 extends GameObject{

		public Texture1(Bitmap bitmap) {
			super(bitmap);
			x = screenWidth/2 - width/2;
			y = screenHeight/2 - height/2 - 70;
		}
	}

	class Texture2 extends GameObject{

		public Texture2(Bitmap bitmap) {
			super(bitmap);
			x = screenWidth/2 - width/2;
		}
	}

}
