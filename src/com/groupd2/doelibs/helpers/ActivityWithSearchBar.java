package com.groupd2.doelibs.helpers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.groupd2.doelibs.R;

public class ActivityWithSearchBar extends Activity{

	protected EditText searchBar;
	protected Button searchButton;
	protected LinearLayout searchBarLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		searchBar = (EditText) findViewById(R.id.editTextSearchBar);
		searchButton = (Button) findViewById(R.id.buttonSearch);
		searchBarLayout = (LinearLayout) findViewById(R.id.layoutSearchBar);

	}
	
	public void onSearch(View btn) {
		//perform search
		
		Toast.makeText(this, "Search!", Toast.LENGTH_SHORT).show();
	}
}
