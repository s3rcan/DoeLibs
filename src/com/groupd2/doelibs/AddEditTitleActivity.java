package com.groupd2.doelibs;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.IntentIntegrator;
import com.google.zxing.integration.IntentResult;
import com.groupd2.doelibs.helpers.ActivityWithSearchBar;
import com.groupd2.doelibs.helpers.CallAPI;
import com.groupd2.doelibs.helpers.TokenHelper;
import com.groupd2.doelibs.models.Book;

@SuppressWarnings(value = { "unused" })
public class AddEditTitleActivity extends ActivityWithSearchBar {
	public static final int MODE_ADD = 0;
	public static final int MODE_EDIT = 1;

	private EditText ISBN;
	private EditText title;
	private EditText author;
	private EditText publisher;
	private EditText editionNo;
	private EditText editionYear;
	private EditText yearOfFirstEdition;

	private Button resultButton;

	private int mode;

	// MODE EDIT
	private Book book;
	private int bookID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_edit_title);

		ISBN = (EditText) findViewById(R.id.editTextTitleISBN);
		title = (EditText) findViewById(R.id.editTextTitleTitle);
		author = (EditText) findViewById(R.id.editTextTitleAuthor);
		publisher = (EditText) findViewById(R.id.editTextTitlePublisher);
		editionNo = (EditText) findViewById(R.id.editTextTitleEditionNo);
		editionYear = (EditText) findViewById(R.id.editTextTitleEditionYear);
		yearOfFirstEdition = (EditText) findViewById(R.id.editTextTitleYearOfFirstEdition);

		resultButton = (Button) findViewById(R.id.buttonAddEditTitle);

		Intent intent = getIntent();
		mode = intent.getIntExtra("MODE", MODE_ADD);

		if (mode == MODE_ADD) {
			setTitle(this.getText(R.string.title_activity_add_title));
			resultButton.setText(this.getText(R.string.add));

		} else if (mode == MODE_EDIT) {
			setTitle(this.getText(R.string.title_activity_edit_title));
			resultButton.setText(this.getText(R.string.edit));
			// get title id extra
			bookID = Integer.parseInt(intent.getStringExtra("bookId"));

			// fill existing data
			CallAPI callAPI = new CallAPI() {
				@Override
				protected void onPostExecute(String result) {
					try {
						JSONObject jObject = new JSONObject(result)
								.getJSONObject("book");
						Book book = new Book(jObject);

						ISBN.setText(book.getISBN());
						title.setText(book.getTitle());
						author.setText(book.getAuthor());
						publisher.setText(book.getPublisher());
						editionNo.setText(book.getEditionNo());
						editionYear.setText(book.getEditionYear());
						yearOfFirstEdition
								.setText(book.getYearOfFirstEdition());

					} catch (Exception e) {
						Toast.makeText(AddEditTitleActivity.this,
								e.getMessage(), Toast.LENGTH_LONG).show();
					}

				};
			};
			callAPI.execute("http://www.itutbildning.nu:10000/api/Title?token="
					+ TokenHelper.getToken(AddEditTitleActivity.this) + "&id="
					+ bookID);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	private String toastText = "";

	public void onResult(View btn) {
		String ISBN = this.ISBN.getText().toString();
		String title = this.title.getText().toString();
		String author = this.author.getText().toString();
		String publisher = this.publisher.getText().toString();
		String editionNo = this.editionNo.getText().toString();
		String editionYear = this.editionYear.getText().toString();
		String yearOfFirstEdition = this.yearOfFirstEdition.getText()
				.toString();

		String modeText = "";
		if (mode == MODE_ADD) {
			modeText = "add";
			toastText = "Added Succesfuly!";
		} else if (mode == MODE_EDIT) {
			modeText = "edit";
			toastText = "Edited Succesfuly!";
		}

		CallAPI callAPI = new CallAPI() {
			@Override
			protected void onPostExecute(String result) {
				// check result make toast
				if (result.startsWith("true")) {
					Toast.makeText(AddEditTitleActivity.this, toastText,
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(AddEditTitleActivity.this,
							"Error: " + result, Toast.LENGTH_SHORT).show();
				}
				finish();
			}
		};
		callAPI.execute("http://www.itutbildning.nu:10000/api/Title?token="
				+ TokenHelper.getToken(AddEditTitleActivity.this), "Id="
				+ bookID, "ISBN=" + ISBN, "Title=" + title, "Author=" + author,
				"Publisher=" + publisher, "EditionNo=" + editionNo,
				"EditionYear=" + editionYear, "YearOfFirstEdition="
						+ yearOfFirstEdition, "Mode=" + modeText);
		btn.setClickable(false);
	}

	public void onScan(View btn) {
		// implement scan algorithm here
		IntentIntegrator integrator = new IntentIntegrator(this);
		integrator.initiateScan();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == IntentIntegrator.REQUEST_CODE) {
			IntentResult scanResult = IntentIntegrator.parseActivityResult(
					requestCode, resultCode, data);
			if (scanResult != null) {
				ISBN.setText(scanResult.toString());
			} else {
				Toast.makeText(this, "Unable to read barcode!",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}
