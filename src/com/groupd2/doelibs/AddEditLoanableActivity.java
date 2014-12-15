package com.groupd2.doelibs;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.groupd2.doelibs.helpers.ActivityWithSearchBar;
import com.groupd2.doelibs.helpers.CallAPI;
import com.groupd2.doelibs.helpers.TokenHelper;
import com.groupd2.doelibs.models.Loanable;

public class AddEditLoanableActivity extends ActivityWithSearchBar {
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
			titleID = Integer.parseInt(intent.getStringExtra("titleID"));
		} else if (mode == MODE_EDIT) {
			setTitle(this.getText(R.string.titleEditLoanable));
			resultButton.setText(this.getText(R.string.edit));
			// get loanable id extra
			loanableID = Integer.parseInt(intent.getStringExtra("loanableId"));

			// fill existing data
			CallAPI callAPI = new CallAPI() {
				@Override
				protected void onPostExecute(String result) {
					try {
						JSONObject jObject = new JSONObject(result)
								.getJSONObject("loanable");
						Loanable loanable = new Loanable(jObject);

						location.setText(loanable.getLocation());
						sublocation.setText(loanable.getSubLocation());
					} catch (Exception e) {
						Toast.makeText(AddEditLoanableActivity.this,
								e.getMessage(), Toast.LENGTH_LONG).show();
						finish();
					}

				};
			};
			callAPI.execute("http://www.itutbildning.nu:10000/api/Loanable?token="
					+ TokenHelper.getToken(AddEditLoanableActivity.this)
					+ "&id=" + loanableID);
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

	public void onResult(View btn) {
		String location = this.location.getText().toString();
		String sublocation = this.sublocation.getText().toString();

		if (location == null || location == "") {
			Toast.makeText(this, "Please fill in info!", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		if (mode == MODE_ADD) {
			// add algorithm here
			CallAPI callAPI = new CallAPI() {
				@Override
				protected void onPostExecute(String result) {
					// make toast
					try {
						int loanableId = Integer.parseInt(result.replace("\n",
								""));

						AlertDialog.Builder builder = new AlertDialog.Builder(
								AddEditLoanableActivity.this);

						builder.setMessage(
								"Tag the book with " + loanableId + ".")
								.setTitle("Added Succesfuly!")
								.setPositiveButton("OK",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												finish();
											}
										});

						builder.create().show();

					} catch (NumberFormatException e) {
						Toast.makeText(AddEditLoanableActivity.this,
								"Error: " + result, Toast.LENGTH_SHORT).show();
					}
					// redirect
					// finish();
				};
			};
			callAPI.execute(
					"http://www.itutbildning.nu:10000/api/Loanable?token="
							+ TokenHelper
									.getToken(AddEditLoanableActivity.this),
					"Id=" + titleID, "Location=" + location, "SubLocation="
							+ sublocation, "Mode=add");
		} else if (mode == MODE_EDIT) {

			CallAPI callAPI = new CallAPI() {
				@Override
				protected void onPostExecute(String result) {
					// make toast
					if (result.startsWith("true")) {
						Toast.makeText(AddEditLoanableActivity.this,
								"Edited Succesfuly!", Toast.LENGTH_SHORT)
								.show();
						finish();
					} else {
						Toast.makeText(AddEditLoanableActivity.this,
								"Error: " + result, Toast.LENGTH_SHORT).show();
					}
					// redirect
				};
			};
			callAPI.execute(
					"http://www.itutbildning.nu:10000/api/Loanable?token="
							+ TokenHelper
									.getToken(AddEditLoanableActivity.this),
					"Id=" + loanableID, "Location=" + location, "SubLocation="
							+ sublocation, "Mode=edit");

		}
		btn.setClickable(false);
	}
}
