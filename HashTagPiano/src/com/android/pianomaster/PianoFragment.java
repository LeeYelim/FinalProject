package com.android.pianomaster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.mccam.pianopro.R;


public class PianoFragment extends Fragment implements OnTouchListener{

	View v;
	Drawable drawable_white, drawable_black, drawable_white_press, 
	drawable_black_press, drawable_right, drawable_left, drawable_black_right, drawable_black_left;  
	Bitmap bitmap_keyboard, bm;
	int numWk = 21; // white건반 갯수
	int numBk = 15; // black 건반 갯수
	int numKeys = numBk + numWk; // 건반 갯수 총합 
	Region[] kb; //  건반 그릴 지역
	int menuNum, sw, sh, pointerIndex,programNo, soundKey, streamVolume, notescale, octave, channel; // 메뉴 번호, 스크린 가로, 스크린 세로, 횟수, 포인터 인덱스 
	Canvas canvas;
	ImageView  pianoView;
	Timer timer;
	boolean[] lastPlayingNotes, playingNotes, key; 
	float x, y;
	Bundle bundle; 
	int[] activePointers;
	private AudioManager mAudioManager;
	private ArrayList<MidiNote> notes;
	private boolean useTwoColors;  
	private MidiPlayer player;  
	private int maxShadeDuration;  
	private int showNoteLetters; 
	private int shade1, shade2;
	int MaxOctave, current, prenotenumber;
	private Paint paint;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.activity_main, container, false);
		pianoView = (ImageView)v.findViewById(R.id.imagePiano); 
		pianoView.setOnTouchListener(this);
		Resources res = this.getResources();
		drawable_white = res.getDrawable(R.drawable.whitekeyboard);
		drawable_black = res.getDrawable(R.drawable.blackkeyboard);
		drawable_black_right = res.getDrawable(R.drawable.blackkeypressedr2);
		drawable_black_left = res.getDrawable(R.drawable.blackkeypressedl2);
		drawable_right = res.getDrawable(R.drawable.whitekeypressedr2);
		drawable_left = res.getDrawable(R.drawable.whitekeypressedl2);
		Display disp = ((WindowManager) getActivity()
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		sw = disp.getWidth();
		sh = disp.getHeight();  
		
		current = 0; 
		prenotenumber = -1;
		bundle = getArguments();
		menuNum = bundle.getInt("menuNum");
		showNoteLetters = MidiOptions.NoteNameNone;
		
		MaxOctave = 3;
		paint = new Paint();
        paint.setAntiAlias(false);
        paint.setTextSize(9.0f);
        
		if (menuNum == 3) {
			numWk = 42;
			numBk = 30;
			numKeys = numWk + numBk;
			pianoView.setEnabled(false);
			MaxOctave = 6;
		} else if(menuNum == 1) {
			channel = 0;
		} else if(menuNum == 2) {
			channel = 1;
		}

		mAudioManager = (AudioManager)getActivity().getSystemService(getActivity().AUDIO_SERVICE); 
		kb = new Region[numKeys];
		playingNotes = new boolean[numKeys];
		activePointers = new int[numKeys];
		key = new boolean[numKeys]; 
		
		makeRegions();	 
		for (int i = 0; i < numKeys; i++) {
			activePointers[i] = -1;
		}
		for (int k = 0; k < numKeys; k++) {
			key[k] = false;
		}
		return v;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

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

		path[0].moveTo(0, 0);
		path[0].lineTo(0, kh); 
		path[0].lineTo(kw, kh);
		path[0].lineTo(kw, bkh);
		path[0].lineTo(kw - (bkw / 2), bkh);
		path[0].lineTo(kw - (bkw / 2), 0);
		path[0].close();

		path[1].moveTo(bkw / 2, 0);
		path[1].lineTo(bkw / 2, bkh);
		path[1].lineTo(0, bkh);
		path[1].lineTo(0, kh);
		path[1].lineTo(kw, kh);
		path[1].lineTo(kw, bkh);
		path[1].lineTo(kw - (bkw / 2), bkh);
		path[1].lineTo(kw - (bkw / 2), 0);
		path[1].close();

		path[2].moveTo(bkw / 2, 0);
		path[2].lineTo(bkw / 2, bkh);
		path[2].lineTo(0, bkh);
		path[2].lineTo(0, kh);
		path[2].lineTo(kw, kh);
		path[2].lineTo(kw, 0);
		path[2].close();
		   
		path[3].addRect(0, 0, bkw, bkh, Direction.CCW);

		Region region = new Region(0, 0, sw, sh);
		if(menuNum == 3){
			int kt[] = new int[] { 0, 1, 2, 0, 1, 1, 2,  0, 1, 2, 0, 1, 1, 2, 0, 1, 2, 0,1,1,2,  0, 1, 2, 0, 1, 1, 2, 0, 1, 2, 0, 1, 1, 2,0, 1, 2, 0,1,1,2,
					3, 3, -1, 3, 3,	3, -1, 3, 3, -1, 3, 3, 3 , -1, 3, 3, -1, 3, 3, 3, -1, 3, 3, -1, 3, 3, 3, -1, 3, 3, -1, 3, 3, 3 ,-1, 3,3,-1,3,3,3};//피아노 건반 그림
			for (int i = 0; i < numWk; i++) {
				kb[i] = new Region();
				Path pathtmp = new Path();
				pathtmp.addPath(path[kt[i]], i * kw, 0);
				kb[i].setPath(pathtmp, region);
			}

			int j = numWk;
			for (int i = numWk; i < kt.length; i++) {
				if (kt[i] != -1) {
					kb[j] = new Region();
					Path pathtmp = new Path();
					pathtmp.addPath(path[kt[i]], (i - numWk + 1) * kw - (bkw / 2),
							0);
					kb[j].setPath(pathtmp, region);
					j = j + 1;
				}
			}
		
		}
		else{
			int kt[] = new int[] { 0, 1, 2, 0, 1, 1, 2, 0, 1, 2, 0, 1, 1, 2, 0, 1, 2, 0, 1, 1, 2, 3, 3, -1, 3, 3,
					3, -1, 3, 3, -1, 3, 3, 3 ,-1, 3, 3, -1, 3, 3, 3};//피아노 건반 그림
			for (int i = 0; i < numWk; i++) {
				kb[i] = new Region();
				Path pathtmp = new Path();
				pathtmp.addPath(path[kt[i]], i * kw, 0);
				kb[i].setPath(pathtmp, region);
			}
	
			int j = numWk;
			for (int i = numWk; i < kt.length; i++) {
				if (kt[i] != -1) {
					kb[j] = new Region();
					Path pathtmp = new Path();
					pathtmp.addPath(path[kt[i]], (i - numWk + 1) * kw - (bkw / 2),
							0);
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
						if(menuNum != 2 && channel != 1) { //오
							drawable_right.setBounds(kb[i].getBounds());
						} else if (menuNum!=1 && channel != 0) {  //왼
							drawable_left.setBounds(kb[i].getBounds());
						}
						
					 } else if (key[i]==false){
						drawable_white.setBounds(kb[i].getBounds());
						drawable_white.draw(canvas);
					} 
				}
				
				for (int i = numWk; i < numKeys; i++) {
					if (key[i]==true) {
						if(menuNum != 2 && channel != 1) { //오
							drawable_black_right.setBounds(kb[i].getBounds());
						} else if (menuNum!=1 && channel != 0) {  //왼
							drawable_black_left.setBounds(kb[i].getBounds());
						}
					} else if(key[i]==false){
						drawable_black.setBounds(kb[i].getBounds());
						drawable_black.draw(canvas);
					}
				}
				
				drawable_black_right.draw(canvas);
				drawable_black_left.draw(canvas);
				drawable_right.draw(canvas);
				drawable_left.draw(canvas);

			return bm;
		}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
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
		public void handleMessage(Message msg) {
			boolean[] playingNotes = new boolean[numKeys];
			for (int i = 0; i < playingNotes.length; i++) { 
					playingNotes[i] = key[i];
			} 
			if (!Arrays.equals(playingNotes, lastPlayingNotes)) {

		    	
				bitmap_keyboard = drawKeys();
				getActivity().runOnUiThread(new Runnable() {

					@Override
					public void run() {
						pianoView.setImageBitmap(bitmap_keyboard);  
					}
				});
			}

			lastPlayingNotes = playingNotes;
		}
	}; 

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
						SheetMusicActivity.sPool.play(soundKey, streamVolume, streamVolume, 0, 0,
								1);
						break;
					case MotionEvent.ACTION_UP:
						key[j] = false;
						SheetMusicActivity.sPool.stop(soundKey);
						activePointers[pointerIndex] = -1;
						break;
					case MotionEvent.ACTION_POINTER_UP:
						key[j] = false;
						SheetMusicActivity.sPool.stop(soundKey);
						activePointers[pointerIndex] = -1;
						break;
					}
				}
			}

		} catch (ArrayIndexOutOfBoundsException e) {

		}
		return true; 
	}

	 public void SetMidiFile(MidiFile midifile, MidiOptions options, 
             MidiPlayer player) {
			if (midifile == null) {
			notes = null;
			useTwoColors = false;
			return;
			}
			this.player = player;
			ArrayList<MidiTrack> tracks = midifile.ChangeMidiNotes(options);
			MidiTrack track = MidiFile.CombineToSingleTrack(tracks);
			notes = track.getNotes();
			
			maxShadeDuration = midifile.getTime().getQuarter() * 2;
			
			/* We want to know which track the note came from.
			* Use the 'channel' field to store the track.
			*/
			for (int tracknum = 0; tracknum < tracks.size(); tracknum++) {
			for (MidiNote note : tracks.get(tracknum).getNotes()) {
			 note.setChannel(tracknum);
			}
			}
			
			/* When we have exactly two tracks, we assume this is a piano song,
			* and we use different colors for highlighting the left hand and
			* right hand notes.
			*/
			useTwoColors = false;
			if (tracks.size() == 2) {
			useTwoColors = true;
			}
			
			showNoteLetters = options.showNoteLetters;
			
			}

	    
	    /** Return the next StartTime that occurs after the MidiNote
	     *  at offset i.  If all the subsequent notes have the same
	     *  StartTime, then return the largest EndTime.
	     */
	    private int NextStartTime(int i) {
	        int start = notes.get(i).getStartTime();
	        int end = notes.get(i).getEndTime();

	        while (i < notes.size()) {
	            if (notes.get(i).getStartTime() > start) {
	                return notes.get(i).getStartTime();
	            }
	            end = Math.max(end, notes.get(i).getEndTime());
	            i++;
	        }
	        return end;
	    }
	    private int NextStartTimeSameTrack(int i) {
	        int start = notes.get(i).getStartTime();
	        int end = notes.get(i).getEndTime();
	        int track = notes.get(i).getChannel();

	        while (i < notes.size()) {
	            if (notes.get(i).getChannel() != track) {
	                i++;
	                continue;
	            }
	            if (notes.get(i).getStartTime() > start) {
	                return notes.get(i).getStartTime();
	            }
	            end = Math.max(end, notes.get(i).getEndTime());
	            i++;
	        }
	        return end;
	    }

	    
	    
	    public void ShadeNotes(int currentPulseTime, int prevPulseTime) { 
	        /* Loop through the Midi notes.
	         * Unshade notes where StartTime <= prevPulseTime < next StartTime
	         * Shade notes where StartTime <= currentPulseTime < next StartTime
	         */ 
	    	
	        int lastShadedIndex = FindClosestStartTime(prevPulseTime - maxShadeDuration * 2); 
	        for (int i = lastShadedIndex; i < notes.size(); i++) {
	            int start = notes.get(i).getStartTime();
	            int end = notes.get(i).getEndTime();
	            int notenumber = notes.get(i).getNumber();
	            int nextStart = NextStartTime(i);
	            int nextStartTrack = NextStartTimeSameTrack(i);
	            end = Math.max(end, nextStartTrack);
	            end = Math.min(end, start + maxShadeDuration-1); 
	             
	            
	            /* If we've past the previous and current times, we're done. */
	            if ((start > prevPulseTime) && (start > currentPulseTime)) {
	                break;
	            }
 
	            /* If shaded notes are the same, we're done */
	            if ((start <= currentPulseTime) && (currentPulseTime < nextStart) &&
	                (currentPulseTime < end) && 
	                (start <= prevPulseTime) && (prevPulseTime < nextStart) &&
	                (prevPulseTime < end)) {
	                break;
	            }

	            /* If the note is in the current time, shade it */
	            if ((start <= currentPulseTime) && (currentPulseTime < end)) {
	                if (useTwoColors) {  
	                	Log.i("channel", ""+notes.get(i).getChannel());
	                    if (notes.get(i).getChannel() == 1 && menuNum!=1) {  //왼 
	                    	channel = 1;
	                    	ShadeOneNote(notenumber);  
	                    } else if(notes.get(i).getChannel() == 0 && menuNum!=2) {  //오
	                    	channel = 0;
	                        ShadeOneNote(notenumber);    
	                    }
	                }
	                else {
	                    ShadeOneNote(notenumber); 
	                    
	                }  
	            }  
	        }
	       
	    }    
	    private int FindClosestStartTime(int pulseTime) {
	        int left = 0;
	        int right = notes.size()-1;

	        while (right - left > 1) {
	            int i = (right + left)/2;
	            if (notes.get(left).getStartTime() == pulseTime)
	                break;
	            else if (notes.get(i).getStartTime() <= pulseTime)
	                left = i;
	            else
	                right = i;
	        }
	        while (left >= 1 && 
	               (notes.get(left-1).getStartTime() == notes.get(left).getStartTime())) {
	            left--;
	        }
	        return left;
	    }
    

	    /* Shade the given note with the given brush.
	     * We only draw notes from notenumber 24 to 96.
	     * (Middle-C is 60).
	     */
	    private void ShadeOneNote(int notenumber) { 	    	
	    	int blackseq[] = {21,22,23,24,25};  
	    	int black[] = {1,3,6,8,10};
	    	int white[] = {0,2,4,5,7,9,11};
	    	int whiteseq[] = {0,1,2,3,4,5,6}; 
	    	int blackseq2[] = {42,43,44,45,46};
	    	
	    	if(prenotenumber!=notenumber) { 
	    		key[current] = false; 
	    	}
	    	
	    	octave = notenumber / 12;  
	    	Log.i("octave", ""+octave); 
	    	notescale = notenumber % 12 ;    
	    	Log.i("notescale", ""+notescale);
	    	
	    	if(menuNum==1) { //오
			    if(octave==4) {
			    	octave = 0;
			    } else if(octave==5) {
			    	octave = 1;
			    } else if(octave>=6) {
			    	octave = 2;
			    }
	    	} else if(menuNum==2) { //왼
	    		if(octave==3) {
	    			octave = 0;
	    		} else if(octave==4) {
	    			octave = 1;
	    		} else if(octave>=5) {
	    			octave = 2;
	    		}
	    	} else if(menuNum==3) { //양
	    		if(octave==2) {
	    			octave = 0;
	    		} else if(octave==3) {
	    			octave = 1;
	    		} else if(octave==4) {
	    			octave = 2;
	    		} else if(octave==5) {
	    			octave = 3;
	    		} else if(octave==6) {
	    			octave = 4;
	    		} else if(octave>=7) {
	    			octave = 5;
	    		}
	    	}
	    	
	    	for(int i = 0; i<white.length; i++) {
	    		if(white[i] == notescale) {
	    			current = whiteseq[i]+(7*octave);
	    		}
	    	} 
	    	for(int i = 0; i<black.length; i++) {
	    		if(black[i] == notescale) {
	    			if(menuNum==3) {
	    				current = blackseq2[i]+(5*octave);
	    			} else {
	    				current = blackseq[i]+(5*octave);
	    			}
	    		}
	    	} 
	    	 
	    	Log.i("current", ""+current);
	        key[current] = true;  
	        prenotenumber = notenumber;  
	        
	    }
	    
	    @Override
	    public void onPause() {
	    	bm.recycle();
	    	super.onPause();
	    }
}
