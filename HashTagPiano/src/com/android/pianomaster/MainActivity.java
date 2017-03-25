package com.android.pianomaster;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.mccam.pianopro.R;

public class MainActivity extends Activity implements OnTouchListener {

	public static final String MidiTitleID = "MidiTitleID";
    public static final int settingsRequestCode = 1;
    
	ImageView  pianoView;
	int numWk = 21; // white건반 갯수
	int numBk = 15; // black 건반 갯수
	int numKeys = numBk + numWk; // 건반 갯수 총합
	Boolean[] key;
	Region[] kb; //  건반 그릴 지역
	int menuNum, sw, sh, count, pointerIndex,programNo, octaveShift, soundKey, streamVolume; // 메뉴 번호, 스크린 가로, 스크린 세로, 횟수, 포인터 인덱스 
	float x, y;
	int[] activePointers;
	int[] playseq; // 연주 순서, 운지법 순서
	Drawable drawable_white, drawable_black, drawable_white_press, drawable_black_press; 
	Timer timer;
	Bitmap bitmap_keyboard, bm;
	boolean[] lastPlayingNotes; 
	Canvas canvas;
	public static final String[] PITCHES = new String[]{"c", "cs", "d", "ds", "e", "f", "fs", "g", "gs", "a", "as", "b"}; // Chro
	public static final String KEYS = "cdefgab"; 
	public static int OCTAVE_COUNT = 2; // 옥타브 수 
	private SoundPool sPool; // 사운드 풀 
	private AudioManager mAudioManager; 
	MusicFragment mufragment;
	Bundle b; 
    private MidiFile midifile;   /* The midi file to play */
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main); 
		pianoView = (ImageView) findViewById(R.id.imagePiano);
		
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
        }
        catch (MidiFileException e) {
            this.finish();
            return;
        }
	        
		b = new Bundle(); 
		Intent intent = getIntent();
		menuNum = intent.getIntExtra("menuNum", 1);
		
		if(menuNum == 1) { 
			b.putInt("menuNum", menuNum);
			octaveShift = 3;
			OCTAVE_COUNT = 3;
		} else if(menuNum == 2) {
			b.putInt("menuNum", menuNum);  
			octaveShift = 1;
			OCTAVE_COUNT = 3;
		} else if(menuNum == 3){
			numWk = 42;
			numBk = 30;
			numKeys = numWk + numBk; 
		}
		
		b.putString("title", title);
		b.putByteArray("data", data);
		key = new Boolean[numKeys];
		kb = new Region[numKeys];
		activePointers = new int[numKeys];
		
		mufragment = new MusicFragment();
		mufragment.setArguments(b);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.add(R.id.container, mufragment);
		ft.commit();
		
		count = 0; 
		playseq = new int[]{4,2,2,4}; // 연주 건반 순서 배역
		
		pianoView.setOnTouchListener(this);
		Resources res = this.getResources();
		drawable_white = res.getDrawable(R.drawable.white);
		drawable_black = res.getDrawable(R.drawable.black);
		drawable_white_press = res.getDrawable(R.drawable.white_pressed);
		drawable_black_press = res.getDrawable(R.drawable.black_pressed);

		Display disp = ((WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		sw = disp.getWidth();
		sh = disp.getHeight();

		makeRegions();	
		for (int i = 0; i < numKeys; i++) {
			activePointers[i] = -1;
		} 
		for(int k = 0; k < numKeys; k++){
			key[k] = false;
		}
		resetSoundPool();
		mAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);		 
	}
	
	

	public void makeRegions() {
		int kw;
		int kh;
		int bkw;
		int bkh;

		kw = (int) sw / numWk; // 디스플레이 가로길이 나누기 흰 건반 수 : 흰 건반 하나의 가로 길이
		kh = (int) (sh * 0.8); // 디스플레이 세로길이의 8/10 : 흰 건반 하나의 세로 길이
		bkw = (int) (kw * 0.6); // 흰 건반 하나의 가로길이의 6/10 : 검정 건반 하나의 가로 길이
		bkh = (int) (sh * 0.25); // 디스플레이 세로길이의 2.5/10 : 검정 건반 하나의 세로 길이

		Path[] path = new Path[4];
		path[0] = new Path();
		path[1] = new Path();
		path[2] = new Path();
		path[3] = new Path();  

		path[0].moveTo(0, 120);
		path[0].lineTo(0, 120+kh); 
		path[0].lineTo(kw, 120+kh);
		path[0].lineTo(kw, 120+bkh);
		path[0].lineTo(kw - (bkw / 2), 120+bkh);
		path[0].lineTo(kw - (bkw / 2), 0);
		path[0].close();

		path[1].moveTo(bkw / 2, 120);
		path[1].lineTo(bkw / 2, 120+bkh);
		path[1].lineTo(0, 120+bkh);
		path[1].lineTo(0, 120+kh);
		path[1].lineTo(kw, 120+kh);
		path[1].lineTo(kw, 120+bkh);
		path[1].lineTo(kw - (bkw / 2), 120+bkh);
		path[1].lineTo(kw - (bkw / 2), 0);
		path[1].close();

		path[2].moveTo(bkw / 2, 120);
		path[2].lineTo(bkw / 2, 120+bkh);
		path[2].lineTo(0, 120+bkh);
		path[2].lineTo(0, 120+kh);
		path[2].lineTo(kw, 120+kh);
		path[2].lineTo(kw, 0);
		path[2].close();
		   
		path[3].addRect(0, 0, bkw, bkh, Direction.CCW);

		Region region = new Region(0, 0, sw, sh);
		if(menuNum == 3){
			int kt[] = new int[] { 0, 1, 2, 0, 1, 1, 2,  0, 1, 2, 0, 1, 1, 2, 0, 1, 2, 0,1,1,2,  0, 1, 2, 0, 1, 1, 2, 0, 1, 2, 0, 1, 1, 2,0, 1, 2, 0,1,1,2,
					3, 3, -1, 3, 3,	3, -1, 3, 3, -1, 3, 3, 3 ,-1, 3,3,-1,3,3,3,-1, 3, 3, -1, 3, 3, 3, -1, 3, 3, -1, 3, 3, 3 ,-1, 3,3,-1,3,3,3};//피아노 건반 그림
			for (int i = 0; i < numWk; i++) {
				kb[i] = new Region();
				Path pathtmp = new Path();
				pathtmp.addPath(path[kt[i]], i * kw, 450);
				kb[i].setPath(pathtmp, region);
			}

			int j = numWk;
			for (int i = numWk; i < kt.length; i++) {
				if (kt[i] != -1) {
					kb[j] = new Region();
					Path pathtmp = new Path();
					pathtmp.addPath(path[kt[i]], (i - numWk + 1) * kw - (bkw / 2),
							450);
					kb[j].setPath(pathtmp, region);
					j = j + 1;
				}
			}
		
		}
		else{
		int kt[] = new int[] { 0, 1, 2, 0, 1, 1, 2, 0, 1, 2, 0, 1, 1, 2,0, 1, 2, 0,1,1,2,3, 3, -1, 3, 3,
				3, -1, 3, 3, -1, 3, 3, 3 ,-1, 3,3,-1,3,3,3};//피아노 건반 그림
		for (int i = 0; i < numWk; i++) {
			kb[i] = new Region();
			Path pathtmp = new Path();
			pathtmp.addPath(path[kt[i]], i * kw, 450);
			kb[i].setPath(pathtmp, region);
		}

		int j = numWk;
		for (int i = numWk; i < kt.length; i++) {
			if (kt[i] != -1) {
				kb[j] = new Region();
				Path pathtmp = new Path();
				pathtmp.addPath(path[kt[i]], (i - numWk + 1) * kw - (bkw / 2),
						450);
				kb[j].setPath(pathtmp, region);
				j = j + 1;
			}
		}
		}
	}

	public Bitmap drawKeys() {
		bm = Bitmap.createBitmap(sw, sh, Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bm);
		
		for (int i = 0; i < numWk; i++) { 
			if (key[i]==true) {
				drawable_white_press.setBounds(kb[i].getBounds());
				drawable_white_press.draw(canvas);
			} else if (key[i]==false){
				drawable_white.setBounds(kb[i].getBounds());
				drawable_white.draw(canvas);
			}
		}

		for (int i = numWk; i < numKeys; i++) {
			if (key[i]==true) {
				drawable_black_press.setBounds(kb[i].getBounds());
				drawable_black_press.draw(canvas);
			} else if(key[i]==false){
				drawable_black.setBounds(kb[i].getBounds());
				drawable_black.draw(canvas);
			}
		}  
				
		if(count < playseq.length) { 
			startPractice(canvas); 
		} 
		return bm;
	}
	
	public void startPractice(Canvas canvas) {		
		
		drawable_black_press.setBounds(kb[playseq[count]].getBounds());
		drawable_black_press.draw(canvas);
		
		if(kb[playseq[count]].contains((int)x, (int)y))				
			count++;
		if(menuNum ==3)
			count++; 
	}
	 
	@Override
	public boolean onTouch(View v, MotionEvent event) {  
		pointerIndex = event.getActionIndex(); 
		x = event.getX(pointerIndex);
		y = event.getY(pointerIndex);
		streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		int blackseq[] = {2,4,7,9,11,14,16,19,21,23,26,28,31,33,35};
		
		try {
			for (int j = 0; j < numKeys; j++) {
				if (kb[j].contains((int) x, (int) y)) {  	
					if (0<=j&&2>=j) {
						soundKey = (2*j)+1;
					} else if (3<=j&&6>=j) {
						soundKey = (2*j);
					} else if (7<=j&&9>=j) { 
						soundKey = (2*j)-1;
					} else if (10<=j&&13>=j) { 
						soundKey = (2*j)-2;
					} else if(14<=j && 16>=j){
						soundKey = (2*j)-3;
					} else if(17<=j&&20>=j){
						soundKey=(2*j)-4;
					} else if (j>=21) {
						soundKey = blackseq[j%21];
					} 
					
					switch (event.getActionMasked()) {
						case MotionEvent.ACTION_DOWN:
						case MotionEvent.ACTION_POINTER_DOWN:						
							activePointers[pointerIndex] = j;
							key[j] = true;
							sPool.play(soundKey, streamVolume, streamVolume, 0, 0, 1); 
							break;
						case MotionEvent.ACTION_UP:
							key[j] = false; 
							sPool.stop(soundKey);
							activePointers[pointerIndex] = -1;
							break;
						case MotionEvent.ACTION_POINTER_UP:
							key[j] = false; 
							sPool.stop(soundKey);
							activePointers[pointerIndex] = -1; 
							break;  
						}
					}
			}
			
		} catch(ArrayIndexOutOfBoundsException e) {
			
		}
		return true;
	}
	
	
	/**
	 * Sound Pool을 재설정합니다.
	 */
	private void resetSoundPool(){ 
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Map<Integer, String> tmpMap = new HashMap<Integer, String>();
				SoundPool tmpPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0); 
								
				//미디 파일 생성
				try {
					programNo = 1;
					MidiFileCreator midiFileCreator = new MidiFileCreator(MainActivity.this);
					midiFileCreator.createMidiFiles(programNo, octaveShift);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				String dir = getDir("", MODE_PRIVATE).getAbsolutePath();
				for(int i=octaveShift; i<OCTAVE_COUNT+octaveShift; i++){
					for (int j=0; j<PITCHES.length; j++){
						String soundPath = dir+File.separator+PITCHES[j]+i+".mid";
						tmpMap.put(tmpPool.load(soundPath, 1),PITCHES[j]+i);
					}
				}
				//sMap = tmpMap; //{cs2=2, cs3=14, as2=11, as3=23, b3=24, b2=12, ds2=4, c3=13, ds3=16, e3=17, gs3=21, d2=3, d3=15,
								//gs2=9, e2=5, fs3=19, c2=1, fs2=7, g2=8, a2=10, a3=22, f3=18, f2=6, g3=20}
				sPool = tmpPool; 
			}
		});
		thread.start();
	}
	
	@Override
	protected void onResume() {
		super.onResume(); 
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				Message msg = handler.obtainMessage();
				handler.sendMessage(msg);
			}
		}, 0, 10);
	}
	
	final Handler handler = new Handler() {
		public void handleMessage(Message msg){			
				boolean[] playingNotes = new boolean[numKeys];
				for (int i = 0; i < playingNotes.length; i++) {
					playingNotes[i] = key[i];
				}

				if (!Arrays.equals(playingNotes, lastPlayingNotes)) {
					
					bitmap_keyboard = drawKeys();
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							pianoView.setImageBitmap(bitmap_keyboard);
							mufragment.showFinger(count);
						}
					});
				}
				
				lastPlayingNotes = playingNotes;
			}
	};
	
	@Override
	protected void onPause() {
		super.onPause();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.rate:
			break;

		default:
			break;
		}
		return true;
	}
}
	
