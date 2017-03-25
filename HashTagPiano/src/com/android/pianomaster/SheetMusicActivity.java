package com.android.pianomaster;

/*
 * Copyright (c) 2011-2012 Madhav Vaidyanathan
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License version 2.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 */

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.zip.CRC32;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mccam.pianopro.R;

/**
 * @class SheetMusicActivity
 *
 *        The SheetMusicActivity is the main activity. The main components are:
 *        - MidiPlayer : The buttons and speed bar at the top. - Piano : For
 *        highlighting the piano notes during playback. - SheetMusic : For
 *        highlighting the sheet music notes during playback.
 *
 */
public class SheetMusicActivity extends Activity {

	public static final String MidiTitleID = "MidiTitleID";
	public static final int settingsRequestCode = 1;

	private MidiPlayer player; /* The play/stop/rewind toolbar */
	//private Piano piano; /* The piano at the top */
	private SheetMusic sheet; /* The sheet music */
	private LinearLayout layout; /* THe layout */
	private MidiFile midifile; /* The midi file to play */
	private MidiOptions options; /* The options for sheet music and sound */
	private long midiCRC; /* CRC of the midi bytes */

	private FrameLayout framelayout;
	PianoFragment pianofragment;

	public static final String[] PITCHES = new String[] { "c", "cs", "d", "ds",
			"e", "f", "fs", "g", "gs", "a", "as", "b" }; // Chro
	public static int OCTAVE_COUNT = 3; // 옥타브 수
	public static final String KEYS = "cdefgab";
	int numWk = 21; // white 건반 갯수
	int numBk = 15; // black 건반 갯수
	int numKeys = numBk + numWk; // 건반 갯수 총합 
	int programNo, octaveShift, menuNum, pointerIndex, streamVolume, soundKey;
	Bundle bundle;
	public static SoundPool sPool;
	float x, y;
	Timer timer;
	boolean[] lastPlayingNotes;
	private Map<Integer, String> sMap;

	/**
	 * Create this SheetMusicActivity. The Intent should have two parameters: -
	 * data: The uri of the midi file to open. - MidiTitleID: The title of the
	 * song (String)
	 */

	@Override
	public void onCreate(Bundle state) {
		super.onCreate(state);
		bundle = new Bundle();
		Intent intent = getIntent();
		menuNum = intent.getIntExtra("menuNum", -1);

		if (menuNum == 1) {
			octaveShift = 4;
			OCTAVE_COUNT = 3;
		} else if (menuNum == 2) {
			octaveShift = 3;
			OCTAVE_COUNT = 3;
		} else if (menuNum == 3) {
			octaveShift = 1;
			OCTAVE_COUNT = 6;
			numWk = 42;
			numBk = 30;
			numKeys = numWk + numBk;
		}
		 
		
		ClefSymbol.LoadImages(this);
		TimeSigSymbol.LoadImages(this);
		MidiPlayer.LoadImages(this);

		// Parse the MidiFile from the raw bytes
		Uri uri = this.getIntent().getData();
		String title = this.getIntent().getStringExtra(MidiTitleID);
		if (title == null) {
			title = uri.getLastPathSegment();
		}
		FileUri file = new FileUri(uri, title);
		this.setTitle("MidiSheetMusic: " + title);
		byte[] data;
		try {
			data = file.getData(this);
			midifile = new MidiFile(data, title);
		} catch (MidiFileException e) {
			this.finish();
			return;
		}

		// Initialize the settings (MidiOptions).
		// If previous settings have been saved, used those
		options = new MidiOptions(midifile);
		CRC32 crc = new CRC32();
		crc.update(data);
		midiCRC = crc.getValue();
		SharedPreferences settings = getPreferences(0);
		options.scrollVert = settings.getBoolean("scrollVert", false);
		options.shade1Color = settings.getInt("shade1Color",
				options.shade1Color);
		options.shade2Color = settings.getInt("shade2Color",
				options.shade2Color);
		options.showPiano = settings.getBoolean("showPiano", true);
		String json = settings.getString("" + midiCRC, null);
		MidiOptions savedOptions = MidiOptions.fromJson(json);
		
		if (savedOptions != null) {
			options.merge(savedOptions);
		}
 
		createView();
		createSheetMusic(options); 
		resetSoundPool();
	}

	/* Create the MidiPlayer and Piano views */
	void createView() {
		Display disp = ((WindowManager) getSystemService(Context.WINDOW_SERVICE))
				.getDefaultDisplay();
		int sw = disp.getWidth();
		int sh = disp.getHeight();
		LayoutParams params = new LayoutParams(sw, sh);
		layout = new LinearLayout(this);
		layout.setLayoutParams(params);
		layout.setOrientation(LinearLayout.VERTICAL); 
		player = new MidiPlayer(this);
		//piano = new Piano(this);
		layout.addView(player);
		setContentView(layout);
		//player.SetPiano(piano);
		layout.requestLayout();
	}

	/** Create the SheetMusic view with the given options */
	private void createSheetMusic(MidiOptions options) {
		if (sheet != null) {
			layout.removeView(sheet);
		} 
		Display disp = ((WindowManager) getSystemService(Context.WINDOW_SERVICE))
				.getDefaultDisplay();
		int sw = disp.getWidth();
		int sh = disp.getHeight() / 3;
		LayoutParams params = new LayoutParams(sw, sh);
		sheet = new SheetMusic(this);
		sheet.init(midifile, options);
		sheet.setPlayer(player);
		sheet.setLayoutParams(params);
		layout.addView(sheet);
		framelayout = new FrameLayout(this);
		framelayout.setId(R.id.container);
		params = new LayoutParams(sw, LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
		//params.setMargins(0, 1000, 0, 0);
		framelayout.setLayoutParams(params);
		layout.addView(framelayout);
		layout.requestLayout();
		bundle = new Bundle();
		bundle.putInt("menuNum", menuNum);
		pianofragment = new PianoFragment(); 
		pianofragment.setArguments(bundle);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.add(R.id.container, pianofragment);
		ft.commit(); 
		player.SetPiano(pianofragment);
		pianofragment.SetMidiFile(midifile, options, player); 
		player.SetMidiFile(midifile, options, sheet);
		layout.requestLayout();
		sheet.callOnDraw();
	}

	/** Always display this activity in landscape mode. */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	/** When the menu button is pressed, initialize the menus. */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (player != null) {
			player.Pause();
		}
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.sheet_music, menu);
		return true;
	}

	/**
	 * Callback when a menu item is selected. - Choose Song : Choose a new song
	 * - Song Settings : Adjust the sheet music and sound options - Save As
	 * Images: Save the sheet music as PNG images - Help : Display the HTML help
	 * screen
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		{
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Sound Pool을 재설정합니다.
	 */
	private void resetSoundPool() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Map<Integer, String> tmpMap = new HashMap<Integer, String>();
				SoundPool tmpPool = new SoundPool(3, AudioManager.STREAM_MUSIC,
						0);

				// 미디 파일 생성
				try {
					programNo = 1;
					MidiFileCreator midiFileCreator = new MidiFileCreator(
							SheetMusicActivity.this);
					midiFileCreator.createMidiFiles(programNo, octaveShift);
				} catch (IOException e) {
					e.printStackTrace();
				}

				String dir = getDir("", MODE_PRIVATE).getAbsolutePath();
				for (int i = octaveShift; i < OCTAVE_COUNT + octaveShift; i++) {
					for (int j = 0; j < PITCHES.length; j++) {
						String soundPath = dir + File.separator + PITCHES[j]
								+ i + ".mid";
						tmpMap.put(tmpPool.load(soundPath, 1), PITCHES[j] + i);
					}
				}
				sMap = tmpMap; //{cs2=2, cs3=14, as2=11, as3=23, b3=24,
				// b2=12, ds2=4, c3=13, ds3=16, e3=17, gs3=21, d2=3, d3=15,
				// gs2=9, e2=5, fs3=19, c2=1, fs2=7, g2=8, a2=10, a3=22, f3=18,
				// f2=6, g3=20}
				sPool = tmpPool;
			}
		});
		thread.start();
	}

	/** When this activity resumes, redraw all the views */
	@Override
	protected void onResume() {
		super.onResume();
		layout.requestLayout();
		player.invalidate();
		//piano.invalidate();
		if (sheet != null) {
			sheet.invalidate();
		}
		layout.requestLayout(); 
	} 

	/** When this activity pauses, stop the music */
	@Override
	protected void onPause() {
		if (player != null) {
			player.Pause();
		}
		super.onPause();
	}

	
}
