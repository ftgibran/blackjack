package edu.csulb.android.blackjack;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


public class BlackjackActivity extends Activity {

	protected BlackjackView blackjackView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		blackjackView = new BlackjackView(this);
		setContentView(blackjackView);
	}

	@Override
	protected void onResume() {
		super.onResume();
		blackjackView.resume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		blackjackView.pause();
	}
}
