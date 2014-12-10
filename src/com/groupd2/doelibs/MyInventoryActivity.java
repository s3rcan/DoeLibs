package com.groupd2.doelibs;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.groupd2.doelibs.adapters.InventoryAdapter;
import com.groupd2.doelibs.helpers.ActivityWithSearchBar;
import com.groupd2.doelibs.helpers.CallAPI;
import com.groupd2.doelibs.helpers.TokenHelper;
import com.groupd2.doelibs.models.InventoryItem;

public class MyInventoryActivity extends ActivityWithSearchBar {

  private ArrayList<InventoryItem> _inventory;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my_inventory);
    getInventory();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
	  return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    return super.onOptionsItemSelected(item, MyInventoryActivity.class);
  }
  
  private void getInventory(){

    CallAPI callAPI = new CallAPI() {
      @Override
      protected void onPostExecute(String result) {
        JSONArray jArray;
        _inventory = new ArrayList<InventoryItem>();
        try {
          jArray = new JSONArray(result);
          for (int i = 0; i < jArray.length(); i++) {
            JSONObject jObject = jArray.getJSONObject(i);
            _inventory.add(new InventoryItem(jObject));
          }

        } catch (Exception e) {
        }
        
        afterGetInventory();
      }
    };

    callAPI.execute("http://www.itutbildning.nu:10000/api/Inventory?token="
        + TokenHelper.getToken(this));

  }
  
  private void afterGetInventory()
  {
    InventoryAdapter adapter = new InventoryAdapter(this,
        _inventory);
    ListView list = (ListView) findViewById(R.id.listViewMyInventory);
    list.setAdapter(adapter);
    
    
    list.setOnItemClickListener(new OnItemClickListener(){
      @Override
      public void onItemClick(AdapterView<?>adapter, View v, int position, long id){
        InventoryItem i = ((InventoryAdapter)adapter.getAdapter()).getItem(position);
        Intent intent = new Intent(MyInventoryActivity.this,AddEditLoanableActivity.class);
        intent.putExtra("MODE", AddEditLoanableActivity.MODE_EDIT);
        intent.putExtra("loanableId", i.getTag());
        startActivity(intent);
      }

    });
  }
}
