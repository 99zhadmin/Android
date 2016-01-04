package com.example.nestlinelayout;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	//The sequence of win;
	private String wins[] ={"012","345","678","036","147","258","048","246"};
	//player 1 click list;
	ArrayList<Integer> A = new ArrayList<Integer>();
	//player 2 click list;
	ArrayList<Integer> B = new ArrayList<Integer>();
	ImageView view1;
	//player 1:flag=1;player2 flag=2;
	int flag = 1;
	//game over end=false; else end =true;
	boolean end = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//setContentView(R.layout.nestlinlayout);

				

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
	public boolean changeImage(View view){
		if(end) {
			return false;
		}else{
		view1 = (ImageView)this.findViewById(view.getId());
		boolean b = view1.isEnabled();
		int selected=-1;
		ArrayList<Integer> temp = new ArrayList<Integer>();
		if (b){
			switch(view.getId()){
			case R.id.imageView1:
				selected = 0;break;
			case R.id.imageView2:
				selected = 1;break;
			case R.id.imageView3:
				selected = 2;break;
			case R.id.imageView4:
				selected = 3;break;
			case R.id.imageView5:
				selected = 4;break;
			case R.id.imageView6:
				selected = 5;break;
			case R.id.imageView7:
				selected = 6;break;
			case R.id.imageView8:
				selected = 7;break;
			case R.id.imageView9:
				selected = 8;break;
			}
			
			if(flag == 1){
				view1.setImageResource(R.drawable.x);
				if (A.size()>=2){
					for(int i=0;i<A.size();i++){
						for(int j=i+1;j<A.size();j++){
							temp.add(A.get(i));
							temp.add(A.get(j));
							temp.add(selected);
			
							if (isWin(temp)){
								//tv = (TextView)this.findViewById(R.id.textView1);
								//tv.setText("player "+String.valueOf(flag)+" win!");
								showMsg("player "+String.valueOf(flag)+" win!");
								end = true;
								return true;
								//break;
							}
							temp.clear();
						}
					}
				}
				A.add(selected);
				flag = 2;
			}else{
				view1.setImageResource(R.drawable.o);
				if (B.size()>=2){
					for(int i=0;i<B.size();i++){
						for(int j=i+1;j<B.size();j++){
							temp.add(B.get(i));
							temp.add(B.get(j));
							temp.add(selected);
							if (isWin(temp)){
								//tv = (TextView)this.findViewById(R.id.textView1);
								//tv.setText("player "+String.valueOf(flag)+" win!");
								showMsg("player "+String.valueOf(flag)+" win!");
								//break;
								end = true;
								return true;
							}
							temp.clear();
						}
					}
				}
				B.add(selected);
				flag = 1;
			}

			view1.setEnabled(false);
			
		}
		return false;
		}
		
	}	
	boolean isWin(ArrayList<Integer> l){
		
		if (l.size()==0){
			return false;
		}
		Collections.sort(l);
		 StringBuffer sb = new StringBuffer();
		 for(int i=0;i<l.size();i++){
			 sb.append(l.get(i));
		 }
		 for (int i=0;i<wins.length;i++){
			 if(wins[i].equals(sb.toString())){
				 return true;
			 }
		 }
		return false;
	}
	public void showMsg(String msg){
		 new AlertDialog.Builder(this).setTitle("").setMessage(msg).setNegativeButton(
			       "OK", null).show();
	}
	public void onClick(View view){
		if(view.getId() == R.id.button1){
			ImageView v1 = (ImageView)this.findViewById(R.id.imageView1);
			v1.setImageResource(R.drawable.neutral);
			ImageView v2 = (ImageView)this.findViewById(R.id.imageView2);
			v2.setImageResource(R.drawable.neutral);
			ImageView v3 = (ImageView)this.findViewById(R.id.imageView3);
			v3.setImageResource(R.drawable.neutral);
			ImageView v4 = (ImageView)this.findViewById(R.id.imageView4);
			v4.setImageResource(R.drawable.neutral);
			ImageView v5 = (ImageView)this.findViewById(R.id.imageView5);
			v5.setImageResource(R.drawable.neutral);
			ImageView v6 = (ImageView)this.findViewById(R.id.imageView6);
			v6.setImageResource(R.drawable.neutral);
			ImageView v7 = (ImageView)this.findViewById(R.id.imageView7);
			v7.setImageResource(R.drawable.neutral);
			ImageView v8 = (ImageView)this.findViewById(R.id.imageView8);
			v8.setImageResource(R.drawable.neutral);
			ImageView v9 = (ImageView)this.findViewById(R.id.imageView9);
			v9.setImageResource(R.drawable.neutral);
			v1.setEnabled(true);
			v2.setEnabled(true);
			v3.setEnabled(true);
			v4.setEnabled(true);
			v5.setEnabled(true);
			v6.setEnabled(true);
			v7.setEnabled(true);
			v8.setEnabled(true);
			v9.setEnabled(true);	
			flag = 1;
			end = false;
			A.clear();
			B.clear();
			
		}
	}
	
}
