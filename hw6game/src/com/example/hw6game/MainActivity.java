package com.example.hw6game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	ViewGroup container;
	Scene scene1;
	Scene scene2;
	Transition customTransition;
	Button startbutton;
	RadioGroup group;
	int flag;// 0 :easy 1 :hard
	private final static String TAG = "com.example.hw6game.MainActivity";

	View v;
	RadioButton r0, r1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mypage);
		RelativeLayout sceneBase = (RelativeLayout) findViewById(R.id.scene_base);
		container = (ViewGroup) getLayoutInflater().inflate(
				R.layout.gameoptions, sceneBase, false);
		scene2 = new Scene(sceneBase, container);
		customTransition = TransitionInflater.from(this).inflateTransition(
				R.transition.cust_trans);
		scene2.setEnterAction(new Runnable() {
			@Override
			public void run() {
				scene2.getSceneRoot().findViewById(R.id.button1)
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								switch (((RadioGroup) scene2.getSceneRoot()
										.findViewById(R.id.radioGroup1))
										.getCheckedRadioButtonId()) {
								case R.id.radio0:
									flag = 0;
									break;
								case R.id.radio1:
									flag = 1;
									break;
								}
								Intent intent = new Intent(MainActivity.this,
										GameActivity.class);
								intent.putExtra("flag", flag);
								startActivity(intent);

							}
						});
				;
			}
		});
		sceneBase.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				TransitionManager.go(scene2, customTransition);

			}
		});
		// group = (RadioGroup)findViewById(R.id.radioGroup1);
		// int checked = group.getCheckedRadioButtonId();
		// startbutton = (Button) findViewById(R.id.button1);
		// startbutton.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// switch(group.getCheckedRadioButtonId()){
		// case R.id.radio0:
		// flag = 0;
		// break;
		// case R.id.radio1:
		// flag = 1;
		// break;
		// }
		// Intent intent = new Intent(MainActivity.this, GameActivity.class);
		// intent.putExtra("flag", flag);
		// startActivity(intent);
		//
		// }
		// });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
}
