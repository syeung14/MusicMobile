package com.gwu.seas;

import java.util.HashMap;

import android.app.Activity;

public interface ItemClickStrategy {
	void executeStrategy(Activity a, HashMap<String, String> map);
}
