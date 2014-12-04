package com.groupd2.doelibs;

import org.json.JSONObject;

public class InventoryItem {
	private String Title;
	private String Status;
	private String OnLoanTo;
	private String ISBN;
	public String getTitle() {
		return Title;
	}
	public String getStatus() {
		return Status;
	}
	public String getOnLoanTo() {
		return OnLoanTo;
	}
	public String getISBN() {
		return ISBN;
	}
	
	public InventoryItem(){}
	
	public InventoryItem(JSONObject jObject)
	{
		try{
			this.Title = jObject.getString("Title");
			this.Status = jObject.getString("Status");
			this.OnLoanTo = jObject.getString("OnLoanTo");
			this.ISBN = jObject.getString("ISBN");
		}
		catch(Exception e){}
	}
}
