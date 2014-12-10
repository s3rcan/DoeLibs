package com.groupd2.doelibs;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.groupd2.doelibs.helpers.CallAPI;
import com.groupd2.doelibs.helpers.TokenHelper;

public class LoginActivity extends Activity {

	private int m_LoginResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return true;
	}
	
	
	
	public void loginClick(View btn) {
		
		EditText username = (EditText) findViewById(R.id.editTextUsername);
		
		EditText password = (EditText) findViewById(R.id.editTextPassword);

		
		if(username.getText().toString() == null)
		{
			
		}
		

		final Context context = this;
		CallAPI callAPI = new CallAPI() {
			@Override
			protected void onPostExecute(String result) {
				JSONObject jObject;
				
				try {
					jObject = new JSONObject(result);
					if(jObject.isNull("status"))
						m_LoginResult = 2;
					else if(jObject.getBoolean("status") == false)
						m_LoginResult = 0;
					else
					{
						String token = jObject.getString("token");
						TokenHelper.setToken(context, token);
						m_LoginResult = 1;
						TokenHelper.setLevel(jObject.getString("level"), context);
					}
				} catch(Exception e) {
					m_LoginResult = 2;
				}
				
				afterLogin();
			}
		};
		
		callAPI.execute("http://www.itutbildning.nu:10000/api/Login?username=" + username.getText().toString() + "&password=" + password.getText().toString());
		
		
		
		
	}
	
	private void afterLogin(){

		if(m_LoginResult != 1)
		{
			TextView err = (TextView)findViewById(R.id.textViewLoginError);
			err.setVisibility(TextView.VISIBLE);
			if(m_LoginResult == 0)
				err.setText("Wrong username or password!");
			else if(m_LoginResult == 2)
				err.setText("Unable connect to DoeLibs service");
		}
		setResult(1);
		finish();
	}
}
