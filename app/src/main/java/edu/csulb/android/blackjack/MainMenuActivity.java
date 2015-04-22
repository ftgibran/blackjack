package edu.csulb.android.blackjack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class MainMenuActivity extends Activity {

	protected MainMenuView mainMenuView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		mainMenuView = new MainMenuView(this);
		setContentView(mainMenuView);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mainMenuView.resume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mainMenuView.pause();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if(event.getAction() == MotionEvent.ACTION_DOWN)
			startActivity(new Intent(MainMenuActivity.this, BlackjackActivity.class));
		return super.onTouchEvent(event);
	}
}
