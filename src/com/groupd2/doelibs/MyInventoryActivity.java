package com.groupd2.doelibs;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.groupd2.doelibs.helpers.ActivityWithSearchBar;
import com.groupd2.doelibs.helpers.CallAPI;
import com.groupd2.doelibs.helpers.TokenHelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_inventory, menu);
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
	}
}
