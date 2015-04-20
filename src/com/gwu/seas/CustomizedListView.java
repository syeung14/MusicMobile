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
	static final String KEY_USER = "user"; // parent node
	static final String KEY_CREATETIME = "createTime";
	static final String KEY_ISADMIN = "isAdmin";
	static final String KEY_LOGINCOUNT = "loginCount";
	static final String KEY_PASSWORD = "password";
	static final String KEY_USEREMAIL = "userEmail";
	static final String KEY_USERID = "userId";
	static final String KEY_USERNAME = "userName";

	ListView list;
	LazyAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.softwaretab);

		final ArrayList<HashMap<String, String>> appsList = new ArrayList<HashMap<String, String>>();

		XMLParser parser = new XMLParser();
		String url = "http://192.168.1.5:8080/MusicCMS/rest/user/greatwall/1234";
		String xml = parser.getXmlFromUrl(url); // getting XML from URL

		Document doc = parser.getDomElement(xml); // getting DOM element

		NodeList nl = doc.getElementsByTagName(KEY_USER);
		// looping through all nodes
		for (int i = 0; i < nl.getLength(); i++) {
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			// adding each child node to HashMap key => value
			map.put(KEY_CREATETIME, parser.getValue(e, KEY_CREATETIME));
			map.put(KEY_ISADMIN, parser.getValue(e, KEY_ISADMIN));
			map.put(KEY_LOGINCOUNT, parser.getValue(e, KEY_LOGINCOUNT));
			map.put(KEY_PASSWORD, parser.getValue(e, KEY_PASSWORD));
			map.put(KEY_USEREMAIL, parser.getValue(e, KEY_USEREMAIL));
			map.put(KEY_USERID, parser.getValue(e, KEY_USERID));
			map.put(KEY_USERNAME, parser.getValue(e, KEY_USERNAME));
			// adding HashList to ArrayList
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
					String url=map.get(KEY_USEREMAIL);
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