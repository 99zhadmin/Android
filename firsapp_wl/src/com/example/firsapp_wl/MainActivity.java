package com.example.firsapp_wl;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView myName;
	TextView myID;
	ImageView myImage;
	Button myButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		myName = (TextView)this.findViewById(R.id.nametxt);
		myID = (TextView)this.findViewById(R.id.idtxt);
		myImage = (ImageView)this.findViewById(R.id.myfaceimg);
		myButton =(Button)this.findViewById(R.id.btnOK);
		
		myName.setText("Name: Weifeng Li");
		myID.setText("ID: 0984558");
		
		//Drawable drawable = this.getResources().getDrawable(R.drawable.mypic);
		//myImage.setBackground(drawable);
		
		Bitmap	myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mypic);
		myImage.setImageBitmap(myBitmap);
		
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
