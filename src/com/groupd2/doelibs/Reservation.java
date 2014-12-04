package com.groupd2.doelibs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

public class Reservation {
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
}
