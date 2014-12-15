package com.groupd2.doelibs;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.groupd2.doelibs.adapters.SearchResultAdapter;
import com.groupd2.doelibs.helpers.ActivityWithSearchBar;
import com.groupd2.doelibs.helpers.CallAPI;
import com.groupd2.doelibs.helpers.TokenHelper;
import com.groupd2.doelibs.models.SearchResult;

public class SearchActivity extends ActivityWithSearchBar {

  private ArrayList<SearchResult> _searchResults;
  private String search;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);
    

	Intent intent = getIntent();
	search = intent.getStringExtra("search");

    
    performSearch();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
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
  
  private void performSearch(){

    CallAPI callAPI = new CallAPI() {
      @Override
      protected void onPostExecute(String result) {
        JSONArray jArray;
        _searchResults = new ArrayList<SearchResult>();
        try {
          jArray = new JSONArray(result);
          for (int i = 0; i < jArray.length(); i++) {
            JSONObject jObject = jArray.getJSONObject(i);
            _searchResults.add(new SearchResult(jObject));
          }

        } catch (Exception e) {
        }
        
        afterPerformSearch();
        progressDialog.dismiss();
      }
    };

    callAPI.execute("http://www.itutbildning.nu:10000/api/Search?token="
        + TokenHelper.getToken(this) + "&search=" + search);
    progressDialog = ProgressDialog.show(this, "", 
            "Loading. Please wait...", true);

  }
  
  private void afterPerformSearch()
  {
    SearchResultAdapter adapter = new SearchResultAdapter(this,_searchResults);
    ListView list = (ListView) findViewById(R.id.listViewSearchResults);
    list.setAdapter(adapter);
    
    
    list.setOnItemClickListener(new OnItemClickListener(){
      @Override
      public void onItemClick(AdapterView<?>adapter, View v, int position, long id){
        SearchResult i = ((SearchResultAdapter)adapter.getAdapter()).getItem(position);
        //Add functionallity for navigating to search result details.
        Intent intent = new Intent(SearchActivity.this, BookInfoActivity.class);
        intent.putExtra("bookId", i.getTitleId());
        
        startActivity(intent);
      }

    });
  }
}
