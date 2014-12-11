package com.groupd2.doelibs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.IntentIntegrator;
import com.google.zxing.integration.IntentResult;
import com.groupd2.doelibs.helpers.ActivityWithSearchBar;

public class MenuActivity extends ActivityWithSearchBar {

	final String Extra_Borrower = "BorrowerName";
	final String Extra_Tag = "Tag";

	private EditText Borrower;
	private EditText Tag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		Borrower = (EditText) findViewById(R.id.BorrowerName);
		Tag = (EditText) findViewById(R.id.WriteTag);
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == IntentIntegrator.REQUEST_CODE) {
			IntentResult scanResult = IntentIntegrator.parseActivityResult(
					requestCode, resultCode, data);
			if (scanResult != null) {
				Tag.setText(scanResult.getContents());
			} else {
				Toast.makeText(this, "Unable to read barcode!",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	public void onBorrower(View button) {
		Intent intent = new Intent(this, HandoutActivity.class);
		intent.putExtra("Extra_Borrower", Borrower.getText().toString());
		startActivity(intent);
	}

	public void onWriteTag(View button) {

		Intent intent = new Intent(this, ReturnLoanableActivity.class);
		intent.putExtra(Extra_Tag, Tag.getText().toString());
		startActivity(intent);
	}

	public void onScanTag(View button) {
		IntentIntegrator integrator = new IntentIntegrator(this);
		integrator.initiateScan();
	}
}
