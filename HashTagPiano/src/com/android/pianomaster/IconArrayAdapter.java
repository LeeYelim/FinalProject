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

package com.android.pianomaster;

import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mccam.pianopro.R;

/** @class IconArrayAdapter
 *  The ListAdapter for displaying the list of songs,
 *  and for displaying the list of files in a directory.
 *
 *  Similar to the array adapter, but adds an icon
 *  to the left side of each item displayed.
 *  Midi files show a NotePair icon.
 */
class IconArrayAdapter<T> extends ArrayAdapter<T> {
    private LayoutInflater inflater;
    private static Bitmap midiIcon;       /* The midi icon */
    private static Bitmap directoryIcon;  /* The directory icon */
    TextView text;
    Context context;
    /** Load the NotePair image into memory. */
    public void LoadImages(Context context) {
        if (midiIcon == null) {
            Resources res = context.getResources();
            midiIcon = BitmapFactory.decodeResource(res, R.drawable.songicon1);
            directoryIcon = BitmapFactory.decodeResource(res, R.drawable.songicon2);
        }
    }

    /** Create a new IconArrayAdapter. Load the NotePair image */
    public IconArrayAdapter(Context context, int resourceId, List<T> objects) {
        super(context, resourceId, objects);
        LoadImages(context);
        this.context = context;
        inflater = LayoutInflater.from(context); 
    }

    /** Create a view for displaying a song in the ListView.
     *  The view consists of a Note Pair icon on the left-side,
     *  and the name of the song.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	if (convertView == null) {
            convertView = inflater.inflate(R.layout.choose_song_item, null);
         }
    	
         text = (TextView)convertView.findViewById(R.id.choose_song_name);
         text.setTypeface(Typeface.createFromAsset(context.getAssets(), "dxfont.ttf"));
         ImageView image = (ImageView)convertView.findViewById(R.id.choose_song_icon);
         text.setHighlightColor(Color.BLACK);

         
         FileUri file = (FileUri) this.getItem(position);
         if (file.isDirectory()) {
             image.setImageBitmap(directoryIcon);
             text.setText(file.getUri().getPath());
         }
         else {
             image.setImageBitmap(midiIcon);
             text.setText(file.toString());
         }
         return convertView;
    }
}

