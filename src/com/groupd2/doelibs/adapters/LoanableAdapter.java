package com.groupd2.doelibs.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.groupd2.doelibs.R;
import com.groupd2.doelibs.models.Loanable;

public class LoanableAdapter extends ArrayAdapter<Loanable> {
	@SuppressWarnings("unused")
	private Context context;
	private ArrayList<Loanable> values;

	public LoanableAdapter(Context context, ArrayList<Loanable> values) {
		super(context, R.layout.listview_twoline, R.id.Title, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public int getCount() {
		return values.size();
	}

	@Override
	public Loanable getItem(int position) {
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
		
		text1.setText(values.get(position).getOwner());
		text2.setText(	"Location: " + values.get(position).getLocation() + "/" + values.get(position).getSubLocation() 
						+ "   " 
						+ "Status: " + values.get(position).getStatus());
		
		
		return view;
	}
}
