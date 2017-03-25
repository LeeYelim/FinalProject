
package com.android.pianomaster; 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.util.Log;

/**
 * 미디 파일을 생성해내기 위한 클래스
 */
public class MidiFileCreator {
	private Context mCtx;
	//index:100, 104번이 음계, 0x3C=가온다
	//index:89번이 패치 번호, 0x01=standard grand piano, 0x21=electric bass guitar(finger)
	private final int[] DUMMY_BYTES = new int[]{
			0x4d, 0x54, 0x68, 0x64, // chunk id
			0x00, 0x00, 0x00, 0x06, // header 길이
			0x00, 0x01, //파일 포맷
			0x00, 0x02, // 트랙 갯수
			0x00, 0xc0, // msb, 쿼터노트 당 tick의 갯수
			
			0x4d, 0x54,	0x72, 0x6b, // chunk id
			0x00, 0x00, 0x00, 0x2e, // 트랙 길이
			0x00, 0xff, 0x03, 0x02, 0x61, 0x31, //악기 
			0x00, 0xff, 0x01, 0x20,	0x47, 0x65, 0x6e, 0x65, 0x72, 0x61, 0x74, 0x65, 0x64, 0x20, 0x62, 0x79, 0x20, 0x4e, 0x6f, 0x74,
			0x65, 0x57, 0x6f, 0x72, 0x74, 0x68, 0x79, 0x20, 0x43, 0x6f, 0x6d, 0x70, 0x6f, 0x73, 0x65, 0x72, //copyright
			0x00, 0xff, 0x2f, 0x00, //end of track
			
			0x4d, 0x54, 0x72, 0x6b, //chunk id
			0x00, 0x00, 0x00, 0x22, //트랙 길이
			0x00, 0xff, 0x21, 0x01, 0x00, // ?
			0x00, 0xff, 0x03, 0x02, 0x61, 0x31, 0x00, 0xc4, 0x01, 0x00, 0xb4, 0x07, 0x7f, 0x00, 0xb4,
			0x0a, 0x40, 0x00, 0x94, 0x39, 0x6e, 0x5e, 0x94, 0x39, 0x00, //악기
			0x00, 0xff, 0x2f, 0x00  //end of track
		};
	
	public MidiFileCreator(Context mCtx) {
		super();
		this.mCtx = mCtx;
	}
	/**
	 * 음계별 미디 파일을 생성합니다.
	 * @param patch 악기 패치 번호
	 * @param octaveShift 옥타브 시프트 (이 기능은 아직 구현되지 않았으며, 추후에도 구현될 것 같지 않다)
	 * @throws IOException
	 */
	public void createMidiFiles(int patch, int octaveShift) throws IOException{
		int pitch = 0x3c-36+(octaveShift*12);
		
		int[] bytes = DUMMY_BYTES;
		String[] pitches = MainActivity.PITCHES;
		
		String dir = mCtx.getDir("", Context.MODE_PRIVATE).getAbsolutePath();
		
		for(int i=octaveShift; i<SheetMusicActivity.OCTAVE_COUNT+octaveShift; i++){
			for (int j=0; j<pitches.length; j++){
				File file = new File(dir+File.separator+pitches[j]+i+".mid"); //dir = /data/data/net.hyeongkyu.android.androInstruments/app_
				//if(!file.exists())
					file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				bytes[100] = pitch;
				bytes[104] = pitch;
				bytes[89] = patch;
							
				for(int c=0;c<bytes.length;c++) 
					fos.write(bytes[c]);
				fos.close();
				Log.i("createpitch", ""+pitch); //48~71
				pitch++;
				
			}
		}
	}
}

//직접 midi file을 생성해서 activity에서는 생성된 midi를 sound pool로 재생하는 방식으로 되어있네요...