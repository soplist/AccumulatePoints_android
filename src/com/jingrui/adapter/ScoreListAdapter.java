package com.jingrui.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ScoreListAdapter extends ArrayAdapter<String>{
	
	private int resourceId;
	private List<String> list;
	private Context context;

	public ScoreListAdapter(Context context,int textViewResourceId,List<String> objects){
		super(context,textViewResourceId,objects);
		resourceId = textViewResourceId;
		this.list = objects;
		this.context = context;
	}
	
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	// TODO Auto-generated method stub
    	TextView textView = new TextView(context);
    	textView.setText(list.get(position));
    	textView.setTextSize(10);
    	textView.setTextColor(Color.BLUE);
    	return textView;
    }
}
