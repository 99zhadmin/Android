package com.example.hw6game;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

public class GameActivity extends Activity implements
		GestureDetector.OnGestureListener {
	private GameView gameView;
	private GestureDetector detector;
	private final static String TAG = "GameActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameview_layout);
		gameView = (GameView) findViewById(R.id.gameView);
		detector = new GestureDetector(this, this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		this.detector.onTouchEvent(event);
		// Be sure to call the superclass implementation
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		Log.i(TAG, "in onFling(): " + e1.getX() + " : " + e2.getX());
		Log.i(TAG, "in onFling(): " + e1.getY() + " : " + e2.getY());
		Log.i(TAG, "in onFling(): " + gameView.getSquare_1_lt_x() + " : "
				+ gameView.getSquare_1_rb_x());
		Log.i(TAG, "in onFling(): " + gameView.getSquare_1_lt_y() + " : "
				+ gameView.getSquare_1_rb_y());
		boolean result = true;
		try {
			float diffY = e2.getY() - e1.getY();
			if (Math.abs(diffY) > 0) {
				if (gameView.getIsFling() == false) {
					if (e1.getX() >= gameView.getSquare_1_lt_x()
							&& e1.getX() <= gameView.getSquare_1_rb_x()
							&& e1.getY() >= gameView.getSquare_1_lt_y()
							&& e1.getY() <= gameView.getSquare_1_rb_y()) {
						gameView.setWhichOne(1);
					} else if (e1.getX() >= gameView.getSquare_2_lt_x()
							&& e1.getX() <= gameView.getSquare_2_rb_x()
							&& e1.getY() >= gameView.getSquare_2_lt_y()
							&& e1.getY() <= gameView.getSquare_2_rb_y()) {
						gameView.setWhichOne(2);
					} else if (e1.getX() >= gameView.getSquare_3_lt_x()
							&& e1.getX() <= gameView.getSquare_3_rb_x()
							&& e1.getY() >= gameView.getSquare_3_lt_y()
							&& e1.getY() <= gameView.getSquare_3_rb_y()) {
						gameView.setWhichOne(3);
					}
					gameView.setIsFling(true);
				}

			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return result;
	}
}
