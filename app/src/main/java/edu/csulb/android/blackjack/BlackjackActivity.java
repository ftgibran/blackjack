package edu.csulb.android.blackjack;

import android.app.Activity;
import android.os.Bundle;


public class BlackjackActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new BlackjackView(this));
	}
}
