package com.groupd2.doelibs;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class ReturnLoanableActivity extends Activity {

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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.return_loanable, menu);
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
		
		tag.setText("0"); // set text here
	}
	
	public void onReturn(View btn){
		//return book
		//make toast
		//go to somewhere else
	}
}
