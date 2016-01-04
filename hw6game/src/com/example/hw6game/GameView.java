package com.example.hw6game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
	private int flinColor;
	private int currColor;// current Color
	private GameThread gameThread;
	private Activity activity;
	private float totalElapsedTime; // the number of seconds elapsed
	private int screenWidth; // width of the screen
	private int screenHeight; // height of the screen

	private float squareTop;
	private float squareLeft;
	private float squareLength;

	private float lineTop;
	private float lineThick;

	private float recTop;
	private float recBottom;

	private float circle_center_x;
	private float circle_center_y;
	private float circle_radious;

	private float xlt_x;
	private float xlt_y;
	private float xrt_x;
	private float xrt_y;
	private float xlb_x;
	private float xlb_y;
	private float xrb_x;
	private float xrb_y;

	private int velocity;

	private Paint backgroundPaint; // Paint used to clear the drawing area
	private Paint squarePaint;
	private Paint linePaint;
	private Paint rectanglePaint;
	private Paint circlePaint;
	private Paint xPaint;

	private int whichOne = 0;// which square is flinged 1,2,3

	public int getWhichOne() {
		return this.whichOne;
	}

	public void setWhichOne(int i) {
		this.whichOne = i;
	}

	private boolean flag = false;// to represent the color matching

	/**
	 * @return the flag
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	private boolean isFling = false;// one of the square is flinged

	public boolean getIsFling() {
		return isFling;
	}

	public void setIsFling(boolean isf) {
		isFling = isf;
	}

	private float square_1_lt_x;
	private float square_1_lt_y;
	private float square_1_rb_x;
	private float square_1_rb_y;

	private float square_2_lt_x;
	private float square_2_lt_y;
	private float square_2_rb_x;
	private float square_2_rb_y;

	private float square_3_lt_x;
	private float square_3_lt_y;
	private float square_3_rb_x;
	private float square_3_rb_y;

	private float tempsquare_top_y;
	private float tempsquare_bottom_y;

	/**
	 * @return the square_1_lt_x
	 */
	public float getSquare_1_lt_x() {
		return square_1_lt_x;
	}

	/**
	 * @return the square_1_lt_y
	 */
	public float getSquare_1_lt_y() {
		return square_1_lt_y;
	}

	/**
	 * @return the square_1_rb_x
	 */
	public float getSquare_1_rb_x() {
		return square_1_rb_x;
	}

	/**
	 * @return the square_1_rb_y
	 */
	public float getSquare_1_rb_y() {
		return square_1_rb_y;
	}

	/**
	 * @return the square_2_lt_x
	 */
	public float getSquare_2_lt_x() {
		return square_2_lt_x;
	}

	/**
	 * @return the square_2_lt_y
	 */
	public float getSquare_2_lt_y() {
		return square_2_lt_y;
	}

	/**
	 * @return the square_2_rb_x
	 */
	public float getSquare_2_rb_x() {
		return square_2_rb_x;
	}

	/**
	 * @return the square_2_rb_y
	 */
	public float getSquare_2_rb_y() {
		return square_2_rb_y;
	}

	/**
	 * @return the square_3_lt_x
	 */
	public float getSquare_3_lt_x() {
		return square_3_lt_x;
	}

	/**
	 * @return the square_3_lt_y
	 */
	public float getSquare_3_lt_y() {
		return square_3_lt_y;
	}

	/**
	 * @return the square_3_rb_x
	 */
	public float getSquare_3_rb_x() {
		return square_3_rb_x;
	}

	/**
	 * @return the square_3_rb_y
	 */
	public float getSquare_3_rb_y() {
		return square_3_rb_y;
	}

	private int[] colors = { Color.RED, Color.BLUE, Color.GREEN };

	private final static String TAG = "com.example.hw6game.GameView";

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs); // call super's constructor
		Log.i(TAG, "GameView construct");
		activity = (Activity) context;

		// register SurfaceHolder.Callback listener
		getHolder().addCallback(this);
		// init

		// get WindowManager
		WindowManager wm = activity.getWindowManager();
		// get size of screen
		Display display = activity.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		screenWidth = size.x;
		screenHeight = size.y;
		Log.i(TAG, "screenWidth = " + screenWidth);
		Log.i(TAG, "screenHeight = " + screenHeight);

		squareTop = screenHeight / 30;
		squareLeft = screenWidth / 10;
		squareLength = screenWidth / 5;

		// draw square

		square_1_lt_x = squareLeft;
		square_1_lt_y = squareTop;
		square_1_rb_x = squareLeft + squareLength;
		square_1_rb_y = squareTop + squareLength;

		square_2_lt_x = 2 * squareLeft + squareLength;
		square_2_lt_y = squareTop;
		square_2_rb_x = 2 * (squareLeft + squareLength);
		square_2_rb_y = squareTop + squareLength;

		square_3_lt_x = 3 * squareLeft + 2 * squareLength;
		square_3_lt_y = squareTop;
		square_3_rb_x = 3 * (squareLeft + squareLength);
		square_3_rb_y = squareTop + squareLength;

		tempsquare_top_y = square_1_lt_y;
		tempsquare_bottom_y = square_1_rb_y;

		lineTop = 5 * screenHeight / 7;
		lineThick = screenHeight / 50;

		recBottom = screenHeight - screenHeight / 10;
		recTop = recBottom - screenHeight / 7;

		circle_center_x = screenWidth / 2;
		circle_center_y = screenHeight / 2;
		circle_radious = 2 * screenWidth / 5;

		xlt_x = screenWidth / 3;
		xlt_y = screenHeight / 5;
		xrt_x = 2 * screenWidth / 3;
		xrt_y = screenHeight / 5;
		xlb_x = screenWidth / 3;
		xlb_y = 3 * screenHeight / 5;
		xrb_x = 2 * screenWidth / 3;
		xrb_y = 3 * screenHeight / 5;

		velocity = this.screenHeight / 6;

		this.colors = getRandomColors(activity);
		currColor = getRandomColor();

		backgroundPaint = new Paint(); // Paint for drawing the target
		backgroundPaint.setColor(Color.BLACK); // set background color
		squarePaint = new Paint();
		squarePaint.setAntiAlias(true);
		squarePaint.setStrokeWidth(3);
		squarePaint.setStyle(Paint.Style.FILL);
		linePaint = new Paint();
		linePaint.setStrokeWidth(lineThick);
		linePaint.setColor(Color.GRAY);
		rectanglePaint = new Paint();
		rectanglePaint.setAntiAlias(true);
		rectanglePaint.setStrokeWidth(3);
		rectanglePaint.setStyle(Paint.Style.FILL);
		circlePaint = new Paint();
		circlePaint.setAntiAlias(true);
		circlePaint.setStrokeWidth(15);
		circlePaint.setStyle(Paint.Style.STROKE);
		circlePaint.setColor(Color.WHITE);
		xPaint = new Paint();
		xPaint.setColor(Color.RED);
		xPaint.setStrokeWidth(15);

	}

	private int[] getRandomColors(Activity a) {
		int[] allcolors = a.getResources().getIntArray(R.array.rainbow);
		int[] retarr = new int[3];
		int min = 1;
		int max = allcolors.length;
		Random random = new Random();
		List<Integer> array = new ArrayList<Integer>();
		int j = 0;
		while ((j < 3)) {
			int i = random.nextInt(max - min + 1) + min;
			if (array.contains(allcolors[i - 1]))
				continue;
			array.add(allcolors[i - 1]);
			j++;
		}
		for (int i = 0; i < array.size(); i++) {
			retarr[i] = array.get(i);
		}
		return retarr;
	}

	public int getRandomColor() {
		int r = this.currColor;
		Random random = new Random();
		int max = 3;
		int min = 1;
		int i = random.nextInt(max - min + 1) + min;
		while (true) {
			if (colors[i - 1] != this.currColor) {
				r = colors[i - 1];
				break;
			} else {
				i = random.nextInt(max - min + 1) + min;
			}
		}
		return r;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		Log.i(TAG, "onSizeChanged");
		// screenWidth = w; // store the width
		// screenHeight = h; // store the height
		// squareTop = 30;
		// squareLeft = 20;
		// squareLength = 40;
		//
		// lineTop = screenHeight - 80;
		// lineThick = 10;
		//
		// recTop = screenHeight - 70;
		// recBottom = screenHeight - 10;
		//
		// circle_center_x = screenWidth / 2;
		// circle_center_y = screenHeight / 2;
		// circle_radious = 40;
		//
		// xlt_x = 50;
		// xlt_y = 80;
		// xrt_x = 130;
		// xrt_y = 80;
		// xlb_x = 50;
		// xlb_y = lineTop - 10;
		// xrb_x = 130;
		// xrb_y = lineTop - 10;
		//
		// velocity = 2;
		//
		// newGame();
	}

	// reset all the screen elements and start a new game
	public void newGame() {
		// gameThread = new GameThread(getHolder());
		// gameThread.start();
	} // end method newGame

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.i(TAG, "surfaceCreated");
		gameThread = new GameThread(holder);
		gameThread.setRunning(true);
		gameThread.start(); // start the game loop thread

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		gameThread.setRunning(false);

		while (retry) {
			try {
				gameThread.join();
				retry = false;
			} // end try
			catch (InterruptedException e) {
			} // end catch
		} // end while

	}

	// Thread subclass to control the game loop
	private class GameThread extends Thread {
		private SurfaceHolder surfaceHolder; // for manipulating canvas
		private boolean threadIsRunning = true; // running by default

		// initializes the surface holder
		public GameThread(SurfaceHolder holder) {
			surfaceHolder = holder;
			setName("GameThread");
		} // end constructor

		// changes running state
		public void setRunning(boolean running) {
			threadIsRunning = running;
		} // end method setRunning

		// controls the game loop
		@Override
		public void run() {
			Canvas canvas = null; // used for drawing
			long previousFrameTime = System.currentTimeMillis();

			while (threadIsRunning) {
				try {
					canvas = surfaceHolder.lockCanvas(null);

					// lock the surfaceHolder for drawing
					synchronized (surfaceHolder) {
						long currentTime = System.currentTimeMillis();
						float elapsedTimeMS = currentTime - previousFrameTime;
						totalElapsedTime += elapsedTimeMS / 1000.00;
						updatePositions(elapsedTimeMS); // update game state
						drawGameElements(canvas); // draw
						previousFrameTime = currentTime; // update previous time
					} // end synchronized block
				} // end try
				finally {
					if (canvas != null)
						surfaceHolder.unlockCanvasAndPost(canvas);
				} // end finally
			} // end while
		} // end method run
	} // end nested class CannonThread
		// called repeatedly by the CannonThread to update game elements

	private void updatePositions(float elapsedTimeMS) {
		double interval = elapsedTimeMS / 1000.0; // convert to seconds

		if (this.getIsFling()) {
			this.tempsquare_top_y += this.velocity * interval;
			this.tempsquare_bottom_y += this.velocity * interval;
			Log.i(TAG, "tempsquare_top_y = " + tempsquare_top_y
					+ "tempsquare_bottom_y = " + tempsquare_bottom_y);
		}
	}// end updatePositions
		// draws the game to the given Canvas

	public void drawGameElements(Canvas canvas) {
		// clear the background
		canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(),
				backgroundPaint);

		// draw square
		squarePaint.setColor(colors[0]);
		canvas.drawRect(squareLeft, squareTop, squareLeft + squareLength,
				squareTop + squareLength, squarePaint);
		squarePaint.setColor(colors[1]);
		canvas.drawRect(2 * squareLeft + squareLength, squareTop,
				2 * (squareLeft + squareLength), squareTop + squareLength,
				squarePaint);
		squarePaint.setColor(colors[2]);
		canvas.drawRect(3 * squareLeft + 2 * squareLength, squareTop,
				3 * (squareLeft + squareLength), squareTop + squareLength,
				squarePaint);

		// draw Line
		canvas.drawLine(0, this.lineTop, this.screenWidth, this.lineTop,
				linePaint);

		// draw Rectangle
		rectanglePaint.setColor(this.currColor);
		canvas.drawRect(squareLeft, recTop, this.screenWidth - squareLeft,
				recBottom, rectanglePaint);

		// draw tempsquare
		if (this.getIsFling()) {
			switch (this.getWhichOne()) {
			case 1:
				squarePaint.setColor(colors[0]);
				this.flinColor = colors[0];
				canvas.drawRect(squareLeft, this.tempsquare_top_y,
						this.square_1_rb_x, this.tempsquare_bottom_y,
						squarePaint);
				break;
			case 2:
				squarePaint.setColor(colors[1]);
				canvas.drawRect(this.square_2_lt_x, this.tempsquare_top_y,
						this.square_2_rb_x, this.tempsquare_bottom_y,
						squarePaint);
				this.flinColor = colors[1];
				break;
			case 3:
				squarePaint.setColor(colors[2]);
				canvas.drawRect(this.square_3_lt_x, this.tempsquare_top_y,
						this.square_3_rb_x, this.tempsquare_bottom_y,
						squarePaint);
				this.flinColor = colors[2];
				break;
			}

		}
		flag = this.flinColor == this.currColor;
		boolean b = (this.getWhichOne() != 0 && this.tempsquare_top_y
				+ this.squareLength >= this.lineTop) ? true : false;

		// draw circle
		if (flag && getIsFling() && b) {// if flag is true and the square
										// collide with rectangle
			canvas.drawCircle(circle_center_x, circle_center_y,
					this.circle_radious, circlePaint);

		} else if (!flag && getIsFling() && b) {// draw X(//if flag is false and
												// the square collide with
			// rectangle)
			canvas.drawLine(this.xlt_x, this.xlt_y, this.xrb_x, this.xrb_y,
					xPaint);
			canvas.drawLine(this.xrt_x, this.xrt_y, this.xlb_x, this.xlb_y,
					xPaint);
		}

		if (tempsquare_top_y >= this.screenHeight) {
			if (flag && getIsFling() && this.getWhichOne() != 0) {

				this.colors = getRandomColors(activity);
				currColor = getRandomColor();
			}
			this.setIsFling(false);
			this.setFlag(false);
			this.setWhichOne(0);
			this.tempsquare_bottom_y = tempsquare_top_y = square_1_lt_y;
			tempsquare_bottom_y = square_1_rb_y;
		}

	} // end method drawGameElements
}
