package com.groupd2.doelibs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.groupd2.doelibs.helpers.ActivityWithSearchBar;
import com.groupd2.doelibs.helpers.TokenHelper;

public class MainActivity extends ActivityWithSearchBar {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TokenHelper.removeToken(this);

		if (TokenHelper.getToken(this) == null) {
			Login();
		}
	}
@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 10) {
			if (resultCode == 1)
				setContentView(R.layout.activity_main);
			else
				Login();
		}
	};

	private void Login() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivityForResult(intent, 10);
	}


	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

	public void onClickAddEdit(View button) {
		Intent intent = new Intent(this, AddEditLoanableActivity.class);
		intent.putExtra("MODE", AddEditLoanableActivity.MODE_EDIT);
		startActivity(intent);
	}

	public void onClickMyBorrowings(View button) {
		Intent intent = new Intent(this, MyBorrowingsActivity.class);
		startActivity(intent);
	}

	public void onClickReturn(View button) {
		Intent intent = new Intent(this, ReturnLoanableActivity.class);
		startActivity(intent);
	}

}
