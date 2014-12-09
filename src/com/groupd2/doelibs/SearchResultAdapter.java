package com.groupd2.doelibs;


import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SearchResultAdapter extends ArrayAdapter<SearchResult> {
	@SuppressWarnings("unused")
	private Context context;
	private ArrayList<SearchResult> values;
	
	public SearchResultAdapter(Context context, ArrayList<SearchResult> values) {
		super(context,R.layout.listview_twoline,R.id.Title,values);
		this.context = context;
		this.values = values;
	}

	@Override
	public int getCount() {
		return values.size();
	}

	@Override
	public SearchResult getItem(int position) {
		return values.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position,convertView,parent);
		TextView text1 = (TextView)view.findViewById(R.id.Title);
		TextView text2 = (TextView)view.findViewById(R.id.Subtitle);
		
		text1.setText(values.get(position).getTitle());
		text2.setText(values.get(position).getAuthors());
		
		
		return view;
	}
}