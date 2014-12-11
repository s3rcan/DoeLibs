package com.groupd2.doelibs.models;

import org.json.JSONObject;

public class HandoutItem {
	private String Title;

	private String Borrower;
	private String Room;
	private String LocationCategory;
	private String Tag;
	private String Reservation;
	
	public String getTitle() {
		return Title;
	}
	public String getBorrower() {
		return Borrower;
	}
	public String getRoom() {
		return Room;
	}
	public String getLocationCategory() {
		return LocationCategory;
	}
	public String getTag() {
		return Tag;
	}
	public String getReservation(){
		return Reservation;
	}
	
	public HandoutItem() {
		// TODO Auto-generated constructor stub
	}

	public HandoutItem(JSONObject jObject) {
		try {
			this.Title = jObject.getString("Title");
			this.Borrower = jObject.getString("Borrower");
			this.Room = jObject.getString("Room");
			this.LocationCategory = jObject.getString("LocationCategory");
			this.Tag = jObject.getString("Tag");
			this.Reservation = jObject.getString("Reservation");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
