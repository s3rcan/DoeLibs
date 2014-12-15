package com.groupd2.doelibs;

import java.util.ArrayList;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.groupd2.doelibs.adapters.LoanableAdapter;
import com.groupd2.doelibs.adapters.ReservationAdapter;
import com.groupd2.doelibs.helpers.ActivityWithSearchBar;
import com.groupd2.doelibs.helpers.CallAPI;
import com.groupd2.doelibs.helpers.NonScrollableListView;
import com.groupd2.doelibs.helpers.TokenHelper;
import com.groupd2.doelibs.models.Book;
import com.groupd2.doelibs.models.Reservation;

public class BookInfoActivity extends ActivityWithSearchBar {

	public static final int RESULT_CODE_REFRESH = 0;

	private EditText ISBN;
	private EditText title;
	private EditText author;
	private EditText publisher;
	private EditText editionNo;
	private EditText editionYear;
	private EditText yearOfFirstEdition;

	private NonScrollableListView reservations;
	private NonScrollableListView loanables;

	private int bookID;
	private Book book;

	private Button reserve;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_info);

		ISBN = (EditText) findViewById(R.id.editTextTitleISBN);
		title = (EditText) findViewById(R.id.editTextTitleTitle);
		author = (EditText) findViewById(R.id.editTextTitleAuthor);
		publisher = (EditText) findViewById(R.id.editTextTitlePublisher);
		editionNo = (EditText) findViewById(R.id.editTextTitleEditionNo);
		editionYear = (EditText) findViewById(R.id.editTextTitleEditionYear);
		yearOfFirstEdition = (EditText) findViewById(R.id.editTextTitleYearOfFirstEdition);

		reservations = (NonScrollableListView) findViewById(R.id.nonScrollableListBookInfoReservations);
		loanables = (NonScrollableListView) findViewById(R.id.nonScrollableListBookInfoLoanables);
		
		reserve = (Button) findViewById(R.id.buttonReserveTitle);
		
		if(TokenHelper.getLevel(this).equals("Borrower")){
			Button tempButton = (Button) findViewById(R.id.buttonEditTitle);
			tempButton.setVisibility(Button.GONE);
			tempButton = (Button) findViewById(R.id.buttonAddLoanableTitle);
			tempButton.setVisibility(Button.GONE);
		}

		Intent intent = getIntent();
		bookID = Integer.parseInt(intent.getStringExtra("bookId"));

		init();
	}

	private void init() {
		// get title info
		CallAPI callAPI = new CallAPI() {
			@Override
			protected void onPostExecute(String result) {
				try {
					JSONObject jObject = new JSONObject(result);
					Book book = new Book(jObject);
					BookInfoActivity.this.book = book;

					ISBN.setText(book.getISBN());
					title.setText(book.getTitle());
					author.setText(book.getAuthor());
					publisher.setText(book.getPublisher());
					editionNo.setText(book.getEditionNo());
					editionYear.setText(book.getEditionYear());
					yearOfFirstEdition.setText(book.getYearOfFirstEdition());

					afterGetReservations();
					afterGetLoanables();

				} catch (Exception e) {
					Toast.makeText(BookInfoActivity.this, e.getMessage(),
							Toast.LENGTH_LONG).show();
					finish();
				}

			};
		};
		callAPI.execute("http://www.itutbildning.nu:10000/api/Title?token="
				+ TokenHelper.getToken(BookInfoActivity.this) + "&id=" + bookID);
		
		reserve.setEnabled(true);
	}

	private void afterGetReservations() {
		ArrayList<Reservation> reservationsList = book.getReservations();
		ReservationAdapter reservationAdapter = new ReservationAdapter(this,
				reservationsList, true);
		reservations.setAdapter(reservationAdapter);
		
		Button reserveBtn = (Button) findViewById(R.id.buttonReserveTitle);
		if(book.isReservedBySelf()){
			reserveBtn.setText("Remove Reserve");
		}else{
			reserveBtn.setText("Reserve");
		}
	}

	private void afterGetLoanables() {
		LoanableAdapter loanableAdapter = new LoanableAdapter(this,
				book.getLoanables());
		loanables.setAdapter(loanableAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	public void onEdit(View btn) {
		Intent intent = new Intent(this, AddEditTitleActivity.class);
		String bookID = String.valueOf(this.bookID);
		intent.putExtra("bookId", bookID);
		intent.putExtra("MODE", AddEditTitleActivity.MODE_EDIT);

		startActivityForResult(intent, AddEditTitleActivity.MODE_EDIT);
	}

	public void onReserve(View btn) {
		//reserve book algorithm here
		btn.setEnabled(false);
		if(book.isReservedBySelf()){
			CallAPI callAPI = new CallAPI() {
				@Override
				protected void onPostExecute(String result) {
					init();
				};
			};
			callAPI.execute("http://www.itutbildning.nu:10000/api/Reservations?token="
					+ TokenHelper.getToken(BookInfoActivity.this), "titleId=" + bookID, "Mode=delete");
		}else{
			CallAPI callAPI = new CallAPI() {
				@Override
				protected void onPostExecute(String result) {
					init();
				};
			};
			callAPI.execute("http://www.itutbildning.nu:10000/api/Reservations?token="
					+ TokenHelper.getToken(BookInfoActivity.this), "titleId=" + bookID, "Mode=add");
		}
	}

	public void onAddLoanable(View btn) {
		Intent intent = new Intent(this, AddEditLoanableActivity.class);
		intent.putExtra("titleID", String.valueOf(bookID));
		intent.putExtra("MODE", AddEditLoanableActivity.MODE_ADD);

		startActivityForResult(intent, AddEditLoanableActivity.MODE_EDIT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_CODE_REFRESH)
			init();
	}
}
