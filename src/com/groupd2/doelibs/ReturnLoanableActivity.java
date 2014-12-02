package com.groupd2.doelibs;

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

public class ReturnLoanableActivity extends ActivityWithSearchBar {

	private EditText tag;
	private EditText loanableTitle;
	private EditText currentBorrower;
	private EditText location;
	private EditText sublocation;
	private Button returnButton;
	private ScrollView loanableInfo;
	
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
		
		/*tag.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				setVisible(false);
				return false;
			}
		});*/
		
		setInfoVisibilty(false);
	}

	private void setInfoVisibilty(boolean b) {
		if(b){
			loanableInfo.setVisibility(LinearLayout.VISIBLE);
			returnButton.setVisibility(Button.VISIBLE);
		}else{
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
	
	public void onOK(View btn){
		@SuppressWarnings("unused")
		String tagText = tag.getText().toString(); // can be number as well
		//do stuff here 
		
		//@if valid
		setInfoVisibilty(true);
		
		loanableTitle.setText("Book Name"); //set name here
		currentBorrower.setText("Borrower Name"); //set name here
		location.setText("Location"); // set location here
		sublocation.setText("Sublocation"); // set sublocation here
		

		//@else not valid
		Toast.makeText(this, "Tag is not valid!", Toast.LENGTH_SHORT).show();
	}
	
	public void onScanTag(View btn){
		setInfoVisibilty(false);
		//implement scan algorithm here
		IntentIntegrator integrator = new IntentIntegrator(this);
		integrator.initiateScan();
		
		tag.setText("0"); // set text here
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode==IntentIntegrator.REQUEST_CODE)
		{
		    IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		    if (scanResult != null)
		    {
		    	tag.setText(scanResult.toString());
		    }
		    else
		    {
		        Toast.makeText(this, "Unable to read barcode!", Toast.LENGTH_SHORT).show();
		    }
		}
	}
	
	public void onReturn(View btn){
		//return book
		//make toast
		//go to somewhere else
	}
}
