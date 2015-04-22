package com.gwu.seas;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CustomizedListView extends Activity {
	// XML node keys
	static final String MUSIC = "music"; // parent node
	static final String ARTIST = "artist";
	static final String NAME = "name";
	static final String DURATION = "duration";
	static final String KEY_RES = "resource";

	ListView list;
	LazyAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.softwaretab);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String url = bundle.getString("url");

		final ArrayList<HashMap<String, String>> appsList = new ArrayList<HashMap<String, String>>();

		XMLParser parser = new XMLParser();
		final String baseURL = "http://musiccms.cloudapp.net:8080/MusicCMS";
		//String url = baseURL+"/rest/music";//"http://http://musiccms.cloudapp.net:8080/MusicCMS/rest/user/greatwall/1234";
		String xml = parser.getXmlFromUrl(url); // getting XML from URL

		Document doc = parser.getDomElement(xml); // getting DOM element

		NodeList nl = doc.getElementsByTagName(MUSIC);
		// looping through all nodes
		for (int i = 0; i < nl.getLength(); i++) {
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			// adding each child node to HashMap key => value
			map.put(ARTIST, parser.getValue(e, ARTIST));
			map.put(NAME, parser.getValue(e, NAME));
			map.put(DURATION, parser.getValue(e, DURATION));
			map.put(KEY_RES, parser.getValue(e, KEY_RES));
			appsList.add(map);
		}

		list = (ListView) findViewById(R.id.list);
		
		// Getting adapter by passing xml data ArrayList
		adapter = new LazyAdapter(this, appsList);
		list.setAdapter(adapter);
		
		

		// Click event for single list row
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				HashMap<String, String> map = appsList.get(position);
				try {
					String url=baseURL+map.get(KEY_RES);
					String extension = MimeTypeMap.getFileExtensionFromUrl(url);
					String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
					Intent mediaIntent = new Intent(Intent.ACTION_VIEW);
					mediaIntent.setDataAndType(Uri.parse(url), mimeType);
					startActivity(mediaIntent);
				} catch (ActivityNotFoundException e) {
					
				}
			}
		});
		
		
	}
}