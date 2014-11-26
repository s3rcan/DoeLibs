package com.groupd2.doelibs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddEditLoanableActivity extends Activity {
	public static final int MODE_ADD = 0;
	public static final int MODE_EDIT = 1;

	private EditText location;
	private EditText sublocation;
	private Button resultButton;

	private int mode;
	
	// MODE ADD
	private int titleID;

	// MODE EDIT
	private int loanableID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_edit_loanable);

		location = (EditText) findViewById(R.id.editTextloanableLocation);
		sublocation = (EditText) findViewById(R.id.editTextloanableSublocation);
		resultButton = (Button) findViewById(R.id.buttonAddEditLoanable);
		
		Intent intent = getIntent();
		mode = intent.getIntExtra("MODE", MODE_ADD);

		if (mode == MODE_ADD) {
			setTitle(this.getText(R.string.titleAddLoanable));
			resultButton.setText(this.getText(R.string.add));
			// get title id extra and stuff
		} else if (mode == MODE_EDIT) {
			setTitle(this.getText(R.string.titleEditLoanable));// Title name can
																// be set here
			resultButton.setText(this.getText(R.string.edit));
			// get loanable id extra and fill existing data
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_edit_loanable, menu);
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
