package com.groupd2.doelibs.helpers;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.groupd2.doelibs.HandoutActivity;
import com.groupd2.doelibs.MainActivity;
import com.groupd2.doelibs.MyBorrowingsActivity;
import com.groupd2.doelibs.MyInventoryActivity;
import com.groupd2.doelibs.R;
import com.groupd2.doelibs.SearchActivity;

public class ActivityWithSearchBar extends Activity {

	protected EditText searchBar;
	protected Button searchButton;
	protected LinearLayout searchBarLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		searchBar = (EditText) findViewById(R.id.editTextSearchBar);
		searchButton = (Button) findViewById(R.id.buttonSearch);
		searchBarLayout = (LinearLayout) findViewById(R.id.layoutSearchBar);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	public void onSearch(View btn) {
		// perform search
		EditText sb = (EditText) findViewById(R.id.editTextSearchBar);
		Intent intent = new Intent(this, SearchActivity.class);
		intent.putExtra("search", sb.getText().toString());
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if(TokenHelper.getLevel(this).equals("Lender"))
			getMenuInflater().inflate(R.menu.doelibs_menu, menu);
		else if(TokenHelper.getLevel(this).equals("Borrower"))
			getMenuInflater().inflate(R.menu.doelibs_borrower_menu,menu);
		
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		return onOptionsItemSelected(item, ActivityWithSearchBar.class);
	}
	
	public boolean onOptionsItemSelected(MenuItem item, Class<?> cls) {
		
		Intent intent;
		switch (item.getItemId()) {
		case R.id.menuMainMenu:
			if(cls == MainActivity.class)
				return true;
			intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			return true;
		case R.id.menuMyBorrowings:
			if (cls == MyBorrowingsActivity.class)
				return true;
			intent = new Intent(this, MyBorrowingsActivity.class);
			startActivity(intent);
			return true;
		case R.id.menuMyInventory:
			if(cls == MyInventoryActivity.class)
				return true;
			
			intent = new Intent(this, MyInventoryActivity.class);
			startActivity(intent);
			return true;
		case R.id.menuLogout:
			TokenHelper.removeToken(this);
			intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			return true;
		case R.id.menuHandout:
			intent = new Intent(this, HandoutActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
