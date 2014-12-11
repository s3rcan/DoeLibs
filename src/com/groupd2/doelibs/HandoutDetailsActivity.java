package com.groupd2.doelibs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.groupd2.doelibs.helpers.ActivityWithSearchBar;
import com.groupd2.doelibs.helpers.CallAPI;
import com.groupd2.doelibs.helpers.TokenHelper;

public class HandoutDetailsActivity extends ActivityWithSearchBar {

	private String _title;
	private String _room;
	private String _locationCategory;
	private String _tag;
	@SuppressWarnings("unused")
	private String _borrower;
	private String _reservation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_handout_details);
		
		Intent intent = getIntent();
		
		Bundle b = intent.getExtras();
		_title = b.getString("title");
		_room = b.getString("room");
		_locationCategory = b.getString("locationCategory");
		_tag = b.getString("tag");
		_borrower = b.getString("borrower");
		_reservation = b.getString("reservation");
		TextView title = (TextView) findViewById(R.id.textViewHandoutDetailsTitle);
		TextView room = (TextView) findViewById(R.id.textViewHandoutDetailsRoom);
		TextView locationCategory = (TextView) findViewById(R.id.textViewHandoutDetailsLocationCategory);
		
		title.setText(_title);
		room.setText("Room: " + _room);
		if(_locationCategory != null)
			locationCategory.setText("Location Category: " + _locationCategory);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.handout_details, menu);
		return true;
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
	
	
	public void onClickHandout(View btn)
	{
		TextView error = (TextView) findViewById(R.id.textViewHandoutDetailsErrorText);
		String tag = ((EditText) findViewById(R.id.editTextHandoutDetailsBookTag)).getText().toString();
		
		if(!_tag.equals(tag))
		{
			error.setVisibility(View.VISIBLE);
		}
		else
		{
			error.setVisibility(View.INVISIBLE);
			Button b = (Button) btn;
			b.setEnabled(false);
			registerBorrowing();
		}
	}
	
	private void registerBorrowing()
	{
		CallAPI callAPI = new CallAPI() {
			@Override
			protected void onPostExecute(String result) {
				Toast.makeText(HandoutDetailsActivity.this, "Loan registered!", Toast.LENGTH_SHORT).show();
				setResult(1);
				finish();
			}
		};
		callAPI.execute("http://www.itutbildning.nu:10000/api/Handout?token="
				+ TokenHelper.getToken(HandoutDetailsActivity.this), "tag=" + _tag, "reservation=" + _reservation);

	}
}
