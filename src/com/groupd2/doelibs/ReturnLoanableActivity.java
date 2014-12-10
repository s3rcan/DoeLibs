package com.groupd2.doelibs;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.zxing.integration.IntentIntegrator;
import com.google.zxing.integration.IntentResult;
import com.groupd2.doelibs.helpers.ActivityWithSearchBar;
import com.groupd2.doelibs.helpers.CallAPI;
import com.groupd2.doelibs.helpers.TokenHelper;
import com.groupd2.doelibs.models.Loan;

public class ReturnLoanableActivity extends ActivityWithSearchBar {

	private EditText tag;
	private EditText loanableTitle;
	private EditText currentBorrower;
	private EditText location;
	private EditText sublocation;
	private Button returnButton;
	private ScrollView loanableInfo;
	private Loan loan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_return_loanable);

		tag = (EditText) findViewById(R.id.editTextloanableTag);
		loanableTitle = (EditText) findViewById(R.id.textReturnTitleName);
		currentBorrower = (EditText) findViewById(R.id.textReturnBorrowerName);
		returnButton = (Button) findViewById(R.id.buttonReturnReturn);
		loanableInfo = (ScrollView) findViewById(R.id.layoutReturnLoanInfo);
		location = (EditText) findViewById(R.id.textReturnLoanableLocation);
		sublocation = (EditText) findViewById(R.id.textReturnLoanableSublocation);

		/*
		 * tag.setOnEditorActionListener(new OnEditorActionListener() {
		 * 
		 * @Override public boolean onEditorAction(TextView v, int actionId,
		 * KeyEvent event) { setVisible(false); return false; } });
		 */
		
		//TODO get extra here from main page and fill data

		setInfoVisibilty(false);
	}

	private void setInfoVisibilty(boolean b) {
		if (b) {
			loanableInfo.setVisibility(LinearLayout.VISIBLE);
			returnButton.setVisibility(Button.VISIBLE);
		} else {
			loanableInfo.setVisibility(LinearLayout.INVISIBLE);
			returnButton.setVisibility(Button.INVISIBLE);
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

	public void onOK(View btn) {
		String tagText = tag.getText().toString(); // can be number as well
		loan = null;
		// do stuff here
		if (tagText == null || tagText == "") {
			Toast.makeText(ReturnLoanableActivity.this, "Tag is not valid!",
					Toast.LENGTH_SHORT).show();
			return;
		}

		CallAPI callAPI = new CallAPI() {
			protected void onPostExecute(String result) {
				try {
					JSONObject jObject = new JSONObject(result).getJSONObject("loan");
					loan = new Loan(jObject);
				} catch (Exception e) {
					Toast.makeText(ReturnLoanableActivity.this,
							"Tag is not valid!", Toast.LENGTH_SHORT).show();
					return;
				}
				afterGetLoan();
			}
		};
		callAPI.execute("http://www.itutbildning.nu:10000/api/ReturnLoanable?token="
				+ TokenHelper.getToken(ReturnLoanableActivity.this)
				+ "&tag="
				+ tagText);

	}

	private void afterGetLoan() {
		if (loan == null)
			return;

		// @if valid
		setInfoVisibilty(true);

		loanableTitle.setText(loan.getTitle()); // set name here
		currentBorrower.setText(loan.getBorrowerName()); // set name here
		location.setText(loan.getLocation()); // set location here
		sublocation.setText(loan.getSubLocation()); // set sublocation here
	};

	public void onScanTag(View btn) {
		setInfoVisibilty(false);
		loan = null;
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
				tag.setText(scanResult.toString());
			} else {
				Toast.makeText(this, "Unable to read barcode!",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	public void onReturn(View btn) {
		// return book
		CallAPI callAPI = new CallAPI() {
			@Override
			protected void onPostExecute(String result) {
				// TODO check result and make toast
				if(result.contains("Room")){
					Toast.makeText(ReturnLoanableActivity.this, "Returned Succesfuly!", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(ReturnLoanableActivity.this, "Error: " + result, Toast.LENGTH_SHORT).show();
				}
				finish();
			}
		};

		callAPI.execute(
				"http://www.itutbildning.nu:10000/api/ReturnLoanable?token="
						+ TokenHelper.getToken(ReturnLoanableActivity.this),
				"Tag=" + tag.getText());
		btn.setClickable(false);
	}
}
