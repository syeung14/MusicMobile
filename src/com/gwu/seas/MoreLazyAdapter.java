package com.gwu.seas;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MoreLazyAdapter extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    
    public MoreLazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row_simple, null);

        TextView title = (TextView)vi.findViewById(R.id.title_simple); 
        
        
        HashMap<String, String> app = new HashMap<String, String>();
        app = data.get(position);
        
        // Setting all values in listview
        title.setText(app.get(CustomizedListView.NAME));
        //imageLoader.DisplayImage(app.get(CustomizedListView.KEY_LOGINCOUNT), thumb_image);
        return vi;
    }
}