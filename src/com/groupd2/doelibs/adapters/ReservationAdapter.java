package com.groupd2.doelibs.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.groupd2.doelibs.R;
import com.groupd2.doelibs.models.Reservation;

public class ReservationAdapter extends ArrayAdapter<Reservation> {
	@SuppressWarnings("unused")
	private Context context;
	private ArrayList<Reservation> values;
	private boolean isOnTitleActivity = false;

	public ReservationAdapter(Context context, ArrayList<Reservation> values) {
		this(context, values, false);
	}

	public ReservationAdapter(Context context, ArrayList<Reservation> values,
			boolean isOnTitleActivity) {
		super(context, R.layout.listview_twoline, R.id.Title, values);
		this.isOnTitleActivity = isOnTitleActivity;
		this.context = context;
		this.values = values;
	}

	public boolean isOnTitleActivity() {
		return isOnTitleActivity;
	}

	public void setOnTitleActivity(boolean isOnTitleActivity) {
		this.isOnTitleActivity = isOnTitleActivity;
	}

	@Override
	public int getCount() {
		return values.size();
	}

	@Override
	public Reservation getItem(int position) {
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
		
		if (!isOnTitleActivity) {
			text1.setText(values.get(position).getTitle()
					+ (values.get(position).isAvailable() ? " (Available)" : ""));
			text2.setText("Reserved: "
					+ values.get(position).getReservedAsString());
		} else {
			text1.setText(values.get(position).getReserver());
			text2.setText("Reserved at: " + values.get(position).getReservedAsString());
		}
		return view;
	}
}