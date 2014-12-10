package com.groupd2.doelibs.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.groupd2.doelibs.R;
import com.groupd2.doelibs.models.HandoutItem;

public class HandoutAdapter extends ArrayAdapter<HandoutItem> implements
		Filterable {
	@Override
	public Filter getFilter() {

		return new Filter() {

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				FilterResults results = new FilterResults();
				ArrayList<HandoutItem> filteredValues = new ArrayList<HandoutItem>();

				constraint = constraint.toString().toLowerCase();
				for (int i = 0; i < allvalues.size(); i++) {
					if (allvalues.get(i).getBorrower().toLowerCase()
							.contains(constraint.toString())) {
						filteredValues.add(allvalues.get(i));
					}
				}

				results.count = filteredValues.size();
				results.values = filteredValues;

				return results;
			}

			@Override
			protected void publishResults(CharSequence constraint,
					FilterResults results) {
				
					values = (ArrayList<HandoutItem>) results.values;
					notifyDataSetChanged();
				

			}

		};

	}

	@SuppressWarnings("unused")
	private Context context;
	private ArrayList<HandoutItem> values;
	private ArrayList<HandoutItem> allvalues;
	public HandoutAdapter(Context context, ArrayList<HandoutItem> values) {
		super(context, R.layout.listview_twoline, R.id.Title, values);
		this.context = context;
		this.allvalues = (ArrayList<HandoutItem>) values.clone();
		this.values = values;
	}

	@Override
	public int getCount() {
		return values.size();
	}

	@Override
	public HandoutItem getItem(int position) {
		return values.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		// LinearLayout layout =
		// (LinearLayout)view.findViewById(R.id.ListViewTwoLineLayout);
		TextView text1 = (TextView) view.findViewById(R.id.Title);
		TextView text2 = (TextView) view.findViewById(R.id.Subtitle);

		text1.setText(values.get(position).getTitle());
		text2.setText("Reserved by: " + values.get(position).getBorrower());

		return view;
	}
}