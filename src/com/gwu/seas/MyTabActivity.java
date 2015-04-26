package com.gwu.seas;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MyTabActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		TabHost tabHost=getTabHost();
		TabSpec spec;
		Intent intent;
		String info;
		
		//3 tabs
		intent=new Intent().setClass(this,SearchView.class);
		info=getString(R.string.search);
		spec = tabHost.newTabSpec(info).setIndicator(info).setContent(intent);
		tabHost.addTab(spec);
		
		intent=new Intent().setClass(this,SearchView.class);
		info=getString(R.string.favorite);
		spec = tabHost.newTabSpec(info).setIndicator(info).setContent(intent);
		tabHost.addTab(spec);
		
		intent=new Intent().setClass(this,SearchView.class);
		info=getString(R.string.profile);
		spec = tabHost.newTabSpec(info).setIndicator(info).setContent(intent);
		tabHost.addTab(spec);
		
		tabHost.setCurrentTab(0);
	}
	
}
