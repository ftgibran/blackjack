package edu.csulb.android.blackjack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;


public class MainMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//turn title off
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		//set to full screen
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(new MainMenuView(this));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if(event.getAction() == MotionEvent.ACTION_DOWN)
		{
			//System.out.println("Hello world!");
			//Intent info_act = new Intent("android.intent.action.BLACKJACK");
			//startActivity(info_act);
			startActivity(new Intent(MainMenuActivity.this, BlackjackActivity.class));
		}
		return super.onTouchEvent(event);
	}
}
