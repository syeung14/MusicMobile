package com.gwu.seas;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.MimeTypeMap;

public class PlayStrategy implements ItemClickStrategy, Properties {

	@Override
	public void executeStrategy(Activity a, HashMap<String, String> map) {
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

}
