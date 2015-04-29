package com.gwu.seas;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.webkit.MimeTypeMap;

public class PresentAndPlayStrategy implements Properties, ItemClickStrategy {

	@Override
	public void executeStrategy(final Activity a, final HashMap<String, String> map) {
		// TODO Auto-generated method stub
		String name = map.get(NAME);
		String message = NAME + ": " + name + "\n"
				+ ARTIST + ": " + map.get(ARTIST) + "\n"
				+ ALBUM + ": " + map.get(ALBUM) + "\n"
				+ YEAR + ": " + map.get(YEAR) + "\n"
				+ CATEGORY + ": " + map.get(CATEGORY) + "\n"
				+ PACE + ": " + map.get(PACE) + "\n"
				+ DURATION + ": " + map.get(DURATION) + "\n";
		new AlertDialog.Builder(a).setTitle(map.get(NAME))
		.setMessage(message)
		.setPositiveButton("Play", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				try{
					String url=baseURL+map.get(KEY_RES);
					String extension = MimeTypeMap.getFileExtensionFromUrl(url);
					String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
					Intent mediaIntent = new Intent(Intent.ACTION_VIEW);
					mediaIntent.setDataAndType(Uri.parse(url), mimeType);
					a.startActivity(mediaIntent);
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}).setNegativeButton("Return", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		}).show();
	}

}
