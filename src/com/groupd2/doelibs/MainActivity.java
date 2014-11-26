package com.groupd2.doelibs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.groupd2.doelibs.helpers.ActivityWithSearchBar;


public class MainActivity extends ActivityWithSearchBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    
    
    public void onClickAddEdit(View button){
    	Intent intent = new Intent(this,AddEditLoanableActivity.class);
    	intent.putExtra("MODE", AddEditLoanableActivity.MODE_EDIT);
    	startActivity(intent);
    }
    
    public void onClickMyBorrowings(View button){
    	Intent intent = new Intent(this,MyBorrowingsActivity.class);
    	startActivity(intent);
    }
    
    public void onClickReturn(View button){
    	Intent intent = new Intent(this,ReturnLoanableActivity.class);
    	startActivity(intent);
    }
    
}
