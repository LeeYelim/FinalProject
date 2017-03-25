package com.android.pianomaster;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mccam.pianopro.R;

public class MenuActivity extends ActionBarActivity {
	TextView textRightView, textLeftView, textBothView;
	int menuNum = 0;  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		startActivity(new Intent(this, SplashActivity.class)); // Splash띄움
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu); 
		
		textRightView = (TextView)findViewById(R.id.text_right);
		textLeftView = (TextView)findViewById(R.id.text_left);
		textBothView = (TextView)findViewById(R.id.text_both); 
	
		textRightView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				menuNum = 1;
				onStartActivity(v);
			}
		});
		
		textLeftView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				menuNum = 2;
				onStartActivity(v);
			}
		});

		textBothView.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View v) {
				menuNum = 3;
				onStartActivity(v);
			}
		});
		
	}

	public void onStartActivity(View v) { 
			Intent i = new Intent(this, ChooseSongActivity.class);
			i.putExtra("menuNum", menuNum);   
			startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
