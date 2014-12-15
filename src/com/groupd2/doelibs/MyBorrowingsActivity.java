package com.groupd2.doelibs;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.groupd2.doelibs.adapters.BorrowingAdapter;
import com.groupd2.doelibs.adapters.ReservationAdapter;
import com.groupd2.doelibs.helpers.ActivityWithSearchBar;
import com.groupd2.doelibs.helpers.CallAPI;
import com.groupd2.doelibs.helpers.TokenHelper;
import com.groupd2.doelibs.models.Borrowing;
import com.groupd2.doelibs.models.Reservation;

public class MyBorrowingsActivity extends ActivityWithSearchBar {

	private ArrayList<Borrowing> _myBorrowings;
	private ArrayList<Reservation> _myReservations;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_borrowings);

		progressDialog = ProgressDialog.show(this, "", 
                "Loading. Please wait...", true);
		getBorrowings();
		getReservations();
	}
	
	private void getBorrowings(){

		CallAPI callAPI = new CallAPI() {
			@Override
			protected void onPostExecute(String result) {
				JSONArray jArray;
				_myBorrowings = new ArrayList<Borrowing>();
				try {
					jArray = new JSONArray(result);
					for (int i = 0; i < jArray.length(); i++) {
						JSONObject jObject = jArray.getJSONObject(i);
						_myBorrowings.add(new Borrowing(jObject));
					}

				} catch (Exception e) {
					Toast.makeText(MyBorrowingsActivity.this,
							e.getMessage(), Toast.LENGTH_LONG).show();
				}
				afterGetBorrowings();
			}
		};

		callAPI.execute("http://www.itutbildning.nu:10000/api/Borrowings?token="
				+ TokenHelper.getToken(this));

	}
	private void getReservations(){

		CallAPI callAPI = new CallAPI() {
			@Override
			protected void onPostExecute(String result) {
				JSONArray jArray;
				_myReservations = new ArrayList<Reservation>();
				try {
					jArray = new JSONArray(result);
					for (int i = 0; i < jArray.length(); i++) {
						JSONObject jObject = jArray.getJSONObject(i);
						_myReservations.add(new Reservation(jObject));
					}

				} catch (Exception e) {
					Toast.makeText(MyBorrowingsActivity.this,
							e.getMessage(), Toast.LENGTH_LONG).show();
				}
				afterGetReservations();
				progressDialog.dismiss();
			}
		};

		callAPI.execute("http://www.itutbildning.nu:10000/api/Reservations?token="
				+ TokenHelper.getToken(this));

	}

	private void afterGetBorrowings() {
		BorrowingAdapter borrowingAdapter = new BorrowingAdapter(this,
				_myBorrowings);
		ListView myBorrowingsList = (ListView) findViewById(R.id.listViewMyBorrowings);
		myBorrowingsList.setAdapter(borrowingAdapter);

	}
	private void afterGetReservations() {
		ReservationAdapter reservationAdapter = new ReservationAdapter(this,
				_myReservations);
		ListView myReservationsList = (ListView) findViewById(R.id.listViewMyReservations);
		myReservationsList.setAdapter(reservationAdapter);

	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item, MyBorrowingsActivity.class);
    }
}
