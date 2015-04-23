package com.gwu.seas;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.TabSpec;

public class SearchView extends Activity {
	private static final String MUSIC = "music"; // parent node
	private static final String ARTIST = "artist";
	private static final String NAME = "name";
	private static final String DURATION = "duration";
	private static final String KEY_RES = "resource";
	private static final String[] cat={"All","Pop","Country","R&B","Rock","Classic", "Other"};
	
    private EditText mNameText = null;
    private EditText mArtistText = null;
    private EditText mPaceText = null;
    private EditText mAlbumText = null;
    private EditText mYearText = null;
    private Spinner Category = null;
    private ArrayAdapter<String> arradapter;
    private Button getButton = null;
    private String selectedCat = "";

    private ListView ResultList = null;
    private LazyAdapter lzadapter;
    
    private String baseURL = "http://musiccms.cloudapp.net:8080/MusicCMS/rest/music/search";//+"/rest/music"
    private final String baseResURL = "http://musiccms.cloudapp.net:8080/MusicCMS";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        mNameText = (EditText) findViewById(R.id.musicname);
        mArtistText = (EditText) findViewById(R.id.artist);
        mPaceText = (EditText) findViewById(R.id.pace);
        mAlbumText = (EditText) findViewById(R.id.album);
        mYearText = (EditText) findViewById(R.id.year);
        Category = (Spinner) findViewById(R.id.category);
        
        getButton = (Button) findViewById(R.id.get);
        getButton.setOnClickListener(mGetClickListener);
        
        arradapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cat);
        arradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Category.setAdapter(arradapter);
        Category.setOnItemSelectedListener(mItemSelListener);
        Category.setVisibility(View.VISIBLE);
        
    }
    private OnItemSelectedListener mItemSelListener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			selectedCat = cat[position];
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	}; 
    private OnClickListener mGetClickListener = new View.OnClickListener()
    {

        @Override
        public void onClick(View v)
        {
            String name;
			try {
				name = URLEncoder.encode(mNameText.getText().toString(),"UTF-8");
				String artist = URLEncoder.encode(mArtistText.getText().toString(),"UTF-8");
	            String album = URLEncoder.encode(mAlbumText.getText().toString(), "UTF-8");
	            String pace = mPaceText.getText().toString();
	            String year = mYearText.getText().toString();
	            
	            String url = baseURL+"?";
	            boolean flag = false;
	            if(name!=null && !"".equals(name)){
	            	url += "name=" + name;
	            	flag = true;
	            }
	            if(artist!=null && !"".equals(artist)){
	            	url += flag?"&artist=":"artist=";
	            	url += artist;
	            	flag = true;
	            }
	            if(album!=null && !"".equals(album)){
	            	url += flag?"&album=":"album=";
	            	url += album;
	            	flag = true;
	            }
	            if(pace!=null && !"".equals(pace)){
	            	url += flag?"&pace=":"pace=";
	            	url += pace;
	            	flag = true;
	            }
	            if(year!=null && !"".equals(year)){
	            	url += flag?"&year=":"year=";
	            	url += year;
	            	flag = true;
	            }
	            if(selectedCat!=null && !"".equals(selectedCat) && !"All".equals(selectedCat)){
	            	url += flag?"&category=":"category=";
	            	url += URLEncoder.encode(selectedCat,"UTF-8");
	            	flag = true;
	            }
	            Log.i("http", "GET request");
	            //String url = baseURL + "?username=" + name + "&age=" + age;
	            showResponseResult(url);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            

        }
    };
    private void showResponseResult(String url)
    {
    	Intent intent=new Intent().setClass(this,CustomizedListView.class);
    	intent.putExtra("url", url);
    	startActivity(intent);

    }
}
