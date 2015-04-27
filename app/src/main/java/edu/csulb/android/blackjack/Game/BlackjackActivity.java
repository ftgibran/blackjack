package edu.csulb.android.blackjack.Game;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import edu.csulb.android.blackjack.R;


public class BlackjackActivity extends Activity {

	private BlackjackView blackjack;
	private ImageButton deal;
	private ImageButton hit;
	private ImageButton stand;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_blackjack);

		blackjack = (BlackjackView) findViewById(R.id.blackjack);

		deal = (ImageButton)findViewById(R.id.deal);
		deal.setOnClickListener(new Deal());

		hit = (ImageButton)findViewById(R.id.hit);
		hit.setVisibility(Button.INVISIBLE);
		hit.setOnClickListener(new Hit());

		stand = (ImageButton)findViewById(R.id.stand);
		stand.setVisibility(Button.INVISIBLE);
		stand.setOnClickListener(new Stand());

	}

	public class Deal implements Button.OnClickListener
	{
		@Override
		public void onClick(View v) {
			deal.setVisibility(Button.INVISIBLE);
			hit.setVisibility(Button.VISIBLE);
			stand.setVisibility(Button.VISIBLE);
			blackjack.game.deal();
		}
	}

	public class Hit implements Button.OnClickListener
	{
		@Override
		public void onClick(View v) {
			blackjack.game.hit();
		}
	}

	public class Stand implements Button.OnClickListener
	{
		@Override
		public void onClick(View v) {
			blackjack.game.stand();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		blackjack.resume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		blackjack.pause();
	}
}
