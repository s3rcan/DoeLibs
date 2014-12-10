package com.groupd2.doelibs;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.groupd2.doelibs.adapters.HandoutAdapter;
import com.groupd2.doelibs.helpers.ActivityWithSearchBar;
import com.groupd2.doelibs.helpers.CallAPI;
import com.groupd2.doelibs.helpers.TokenHelper;
import com.groupd2.doelibs.models.HandoutItem;

public class HandoutActivity extends ActivityWithSearchBar {

	private EditText filterText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_handout);

		filterText = (EditText) findViewById(R.id.editTextHandoutFilter);
		filterText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				adapter.getFilter().filter(s);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// filterList(s.toString());
			}
		});

		loadReservations();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private ArrayList<HandoutItem> _allHandouts;

	private void loadReservations() {

		CallAPI callAPI = new CallAPI() {
			@Override
			protected void onPostExecute(String result) {
				JSONArray jArray;
				_allHandouts = new ArrayList<HandoutItem>();
				try {
					jArray = new JSONArray(result);
					for (int i = 0; i < jArray.length(); i++) {
						JSONObject jObject = jArray.getJSONObject(i);
						_allHandouts.add(new HandoutItem(jObject));
					}

				} catch (Exception e) {
				}

				afterLoadReservations();
			}
		};

		callAPI.execute("http://www.itutbildning.nu:10000/api/Handout?token="
				+ TokenHelper.getToken(this));

	}

	private HandoutAdapter adapter;

	private void afterLoadReservations() {

		adapter = new HandoutAdapter(this, _allHandouts);
		ListView list = (ListView) findViewById(R.id.listViewHandout);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapte, View v,
					int position, long id) {
				HandoutItem item = adapter.getItem(position);
				Intent intent = new Intent(HandoutActivity.this, HandoutDetailsActivity.class);
				intent.putExtra("title",item.getTitle());
				intent.putExtra("room",item.getRoom());
				intent.putExtra("locationCategory",item.getLocationCategory());
				intent.putExtra("tag",item.getTag());
				intent.putExtra("borrower", item.getBorrower());
				intent.putExtra("reservation", item.getReservation());
				
				startActivityForResult(intent, 10);
			}

		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 10)
			finish();
	}
	

}