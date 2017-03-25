package com.android.pianomaster;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

import com.mccam.pianopro.R;

public class ChooseSongActivity extends TabActivity {
	
	ViewPager pager;
	static ChooseSongActivity globalActivity;
	Bundle bundle;
	int menuNum;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		globalActivity = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_song);
		
		Intent intent = getIntent();
		menuNum = intent.getIntExtra("menuNum", -1);
		
		final TabHost tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec("All")
				.setIndicator("")
				.setContent(new Intent(this, AllSongsActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("Browse")
				.setIndicator("")
                .setContent(new Intent(this, FileBrowserActivity.class)));
		tabHost.getTabWidget().getChildAt(0).
		setBackgroundResource(R.drawable.songchoiceselected);
		tabHost.getTabWidget().getChildAt(1).
		setBackgroundResource(R.drawable.songchoicefolder);
		
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				if(tabId.equals("All")) {
					tabHost.getTabWidget().getChildAt(0).
					setBackgroundResource(R.drawable.songchoiceselected);
					tabHost.getTabWidget().getChildAt(1).
					setBackgroundResource(R.drawable.songchoicefolder);
				} else {
					tabHost.getTabWidget().getChildAt(0).
					setBackgroundResource(R.drawable.songchoice);
					tabHost.getTabWidget().getChildAt(1).
					setBackgroundResource(R.drawable.songchoicefolderselected);
				}
			}
		});
	}

	public static void openFile(FileUri file) {
		globalActivity.doOpenFile(file);
	}

	public void doOpenFile(FileUri file) {
		byte[] data = file.getData(this);
		if (data == null || data.length <= 6 || !MidiFile.hasMidiHeader(data)) {
			ChooseSongActivity.showErrorDialog("Error: Unable to open song: "
					+ file.toString(), this);
			return;
		}
		Intent intent = new Intent(Intent.ACTION_VIEW, file.getUri(), this,
				SheetMusicActivity.class);
		intent.putExtra(SheetMusicActivity.MidiTitleID, file.toString());
		intent.putExtra("menuNum", menuNum);
		startActivity(intent);
	}

	/** Show an error dialog with the given message */
	public static void showErrorDialog(String message, Activity activity) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setMessage(message);
		builder.setCancelable(false);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) { 
		return super.onCreateOptionsMenu(menu);
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) { 
		return super.onOptionsItemSelected(item);
	}
}
