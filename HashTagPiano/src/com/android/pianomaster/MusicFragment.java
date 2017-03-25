package com.android.pianomaster;

import java.util.zip.CRC32;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mccam.pianopro.R;

public class MusicFragment extends Fragment {


	 private MidiPlayer player;   /* The play/stop/rewind toolbar */
	 private Piano piano;         /* The piano at the top */
	 private SheetMusic sheet;    /* The sheet music */
	 private LinearLayout layout; /* THe layout */
	 private MidiFile midifile;   /* The midi file to play */
	 private MidiOptions options; /* The options for sheet music and sound */
	 private long midiCRC;      /* CRC of the midi bytes */
	 byte[] data;
	int menuNum, count1;
	ImageView rightHandView, leftHandView,
	fingerOneView, fingerTwoView, fingerThreeView, fingerFourView, fingerFiveView;
	View v;
	String title;
	int[] fingerseq;// 운지법 배열  
	int[] fingerViewList = { 
			R.id.imagefinger1,
			R.id.imagefinger2,
			R.id.imagefinger3,
			R.id.imagefinger4,
			R.id.imagefinger5
			};
	 		 
	     /** Create this SheetMusicActivity.  
	      * The Intent should have two parameters:
	      * - data: The uri of the midi file to open.
	      * - MidiTitleID: The title of the song (String)
	      */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//v = inflater.inflate(R.layout.fragment_music, container, false);
		
		Bundle b = getArguments();
		menuNum = b.getInt("menuNum");
		data = b.getByteArray("byte");
		title = b.getString("title");
//		rightHandView = (ImageView)v.findViewById(R.id.imageRhand);
//		leftHandView = (ImageView)v.findViewById(R.id.imageLhand);
//		fingerOneView = (ImageView)v.findViewById(R.id.imagefinger1);
//		fingerTwoView = (ImageView)v.findViewById(R.id.imagefinger2);
//		fingerThreeView = (ImageView)v.findViewById(R.id.imagefinger3);
//		fingerFourView = (ImageView)v.findViewById(R.id.imagefinger4);
//		fingerFiveView = (ImageView)v.findViewById(R.id.imagefinger5);
//		
//		
//		if(menuNum == 1) {
//			leftHandView.setVisibility(View.GONE); //오른손 버전
//		} else if (menuNum == 2) {
//			rightHandView.setVisibility(View.GONE); //왼손 버전
//		} 
//		
//		fingerseq = new int[]{5,2,2,4}; 
//		
//		for (int i = 0; i < fingerViewList.length; i++) {
//			ImageView iv = (ImageView)v.findViewById(fingerViewList[i]); 
//			iv.setTag((Integer)i);
//		}
		
		 ClefSymbol.LoadImages(getActivity());
	     TimeSigSymbol.LoadImages(getActivity());
	     MidiPlayer.LoadImages(getActivity());
	     midifile = new MidiFile(data, title);
	  // Initialize the settings (MidiOptions).
	        // If previous settings have been saved, used those
	        options = new MidiOptions(midifile);
	        CRC32 crc = new CRC32();
	        crc.update(data); 
	        midiCRC = crc.getValue();
	        SharedPreferences settings = getActivity().getPreferences(0);
	        options.scrollVert = settings.getBoolean("scrollVert", false);
	        options.shade1Color = settings.getInt("shade1Color", options.shade1Color);
	        options.shade2Color = settings.getInt("shade2Color", options.shade2Color);
	        options.showPiano = settings.getBoolean("showPiano", true);
	        String json = settings.getString("" + midiCRC, null);
	        MidiOptions savedOptions = MidiOptions.fromJson(json);
	        if (savedOptions != null) {
	            options.merge(savedOptions);
	        }
	        createView();
	        createSheetMusic(options);
	        
	        return v;
	    }
		 
	 
	
	 
	public void showFinger(int count) {
		if(count<fingerseq.length && menuNum==1) {
			switch (fingerseq[count]) {
			case 2: 
				fingerTwoView.setVisibility(View.VISIBLE); 
				break;
			case 5: 
				fingerFiveView.setVisibility(View.VISIBLE);
				break;
			}
			setVisibilty(fingerseq[count]-1);
		}
	}

	public void setVisibilty(int num) { 
		for (int i = 0; i < fingerViewList.length; i++) {
			if(num != i) {
				ImageView iv = (ImageView)v.findViewById(fingerViewList[i]);
				iv.setVisibility(View.GONE);
			}
		}
	}

	/* Create the MidiPlayer and Piano views */
    void createView() {  
        layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        player = new MidiPlayer(getActivity()); 
        layout.addView(player); 
        getActivity().setContentView(layout); 
        layout.requestLayout();
    }
    
	 /** Create the SheetMusic view with the given options */
    private void 
    createSheetMusic(MidiOptions options) {
        if (sheet != null) {
            layout.removeView(sheet);
        }
        if (!options.showPiano) {
            piano.setVisibility(View.GONE);
        }
        else {
            piano.setVisibility(View.VISIBLE);
        }
        sheet = new SheetMusic(getActivity());
        sheet.init(midifile, options);
        sheet.setPlayer(player);
        layout.addView(sheet);
        piano.SetMidiFile(midifile, options, player);
        piano.SetShadeColors(options.shade1Color, options.shade2Color);
        player.SetMidiFile(midifile, options, sheet);
        layout.requestLayout();
        sheet.callOnDraw();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
