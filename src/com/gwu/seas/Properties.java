package com.gwu.seas;

public interface Properties {
	static final String MUSIC = "music"; // parent node
	static final String ARTIST = "artist";
	static final String NAME = "name";
	static final String DURATION = "duration";
	static final String KEY_RES = "resource";
	static final String ALBUM = "album";
	static final String PACE = "pace";
	static final String YEAR = "year";
	static final String CATEGORY = "category";
	static final int ADAPTER_THRESHOLD = 3;
	
	final String baseURL = "http://musiccms.cloudapp.net:8080/MusicCMS";
	final String userBaseURL = "http://musiccms.cloudapp.net:8080/MusicCMS/rest/user/";
	final String searchBaseURL = "http://musiccms.cloudapp.net:8080/MusicCMS/rest/music/search";
	
	static final int LAZY_ADAPTER = 1;
	static final int MORE_LAZY_ADAPTER = 2;
	
	static final String CONTENT = "content";
	static final String UNAME = "userName";
	
	static final String[] cat={"All","Pop","Country","R&B","Rock","Classic", "Other"};
}
