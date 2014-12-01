package com.groupd2.doelibs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.groupd2.doelibs.helpers.ActivityWithSearchBar;

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
			setTitle(this.getText(R.string.title_activity_edit_title));// Title
																		// name
																		// can
																		// be
																		// set
																		// here
			resultButton.setText(this.getText(R.string.edit));
			// get title id extra and fill existing data
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_edit_title, menu);
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

	public void onResult(View btn) {
		if (mode == MODE_ADD) {
			// add algorithm here
			// make toast
			// redirect
		} else if (mode == MODE_EDIT) {
			// edit algorithm here
			// make toast
			// redirect
		}
	}
}
