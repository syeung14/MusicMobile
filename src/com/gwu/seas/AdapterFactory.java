package com.gwu.seas;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.widget.BaseAdapter;

public class AdapterFactory{
	static final int LAZY_ADAPTER = 1;
	static final int MORE_LAZY_ADAPTER = 2;
    public static BaseAdapter adapterCreate(int type, Activity a, ArrayList<HashMap<String, String>> appsList){
         if(type==LAZY_ADAPTER){
                return new LazyAdapter(a, appsList);
         }else if(type==MORE_LAZY_ADAPTER){
               return new MoreLazyAdapter(a, appsList);
         }
         return new LazyAdapter(a, appsList);
   }
}
