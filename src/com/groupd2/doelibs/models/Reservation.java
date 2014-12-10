package com.groupd2.doelibs.models;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

@SuppressLint("SimpleDateFormat") public class Reservation {
	private String Reserver;
	private String Title;
	private Date Reserved;
	private boolean Available;

	public String getTitle() {
		return Title;
	}

	public Date getReserved() {
		return Reserved;
	}

	public String getReservedAsString(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
		return sdf.format(Reserved);
	}
	
	public boolean isAvailable() {
		return Available;
	}

	public Reservation() {
	}

	public Reservation(JSONObject jObject) {
		try {
			this.Reserver = jObject.getString("Reserver");
			this.Title = jObject.getString("Title");
			this.Available = jObject.getBoolean("Available");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
			String dateString = jObject.getString("Reserved");

			this.Reserved = sdf.parse(dateString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getReserver() {
		return Reserver;
	}

	public void setReserver(String reserver) {
		Reserver = reserver;
	}
}
