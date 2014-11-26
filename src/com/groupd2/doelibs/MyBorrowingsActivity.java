package com.groupd2.doelibs;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.groupd2.doelibs.helpers.ActivityWithSearchBar;

public class MyBorrowingsActivity extends ActivityWithSearchBar {

	
	ArrayList<StringTuple> _myBorrowings;
	ArrayList<StringTuple> _myReservations;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_borrowings);
		
		_myBorrowings = new ArrayList<StringTuple>();
		_myReservations = new ArrayList<StringTuple>();
		
		_myReservations.add(new StringTuple("Reservation 1","Reserved: 2014-01-01"));
		_myReservations.add(new StringTuple("Reservation 2", "Reserved: 2014-02-02"));
		
		_myBorrowings.add(new StringTuple("Borrowing 1", "Expires: 2014-12-14"));
		_myBorrowings.add(new StringTuple("Borrowing 2","Expires 2014-11-30"));
		
		TwoLineAdapter myReservationsAdapter = new TwoLineAdapter(this, _myReservations);
		TwoLineAdapter myBorrowingsAdapter = new TwoLineAdapter(this, _myBorrowings);
		
		
		ListView myReservationsList = (ListView) findViewById(R.id.listViewMyReservations);
		ListView myBorrowingsList = (ListView) findViewById(R.id.listViewMyBorrowings);
		
		myReservationsList.setAdapter(myReservationsAdapter);
		myBorrowingsList.setAdapter(myBorrowingsAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_borrowings, menu);
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
}
