package edu.csulb.android.blackjack.Utilities;

import android.graphics.Canvas;

/**
 * Created by FelipeGibran on 4/22/2015.
 */
public interface Renderable {

	void onUpdate();
	void onRender(Canvas canvas);
}
