package com.groupd2.doelibs.models;

import org.json.JSONObject;

public class Loan {
	private String title;
	private String borrowerName;
	private String location;
	private String subLocation;

	public Loan(JSONObject jObject) throws Exception {
		title = jObject.getString("LoanableTitle") != "null" ? jObject.getString("LoanableTitle") : "";
		borrowerName = jObject.getString("BorrowerName") != "null" ? jObject.getString("BorrowerName") : "";
		location = jObject.getString("Location") != "null" ? jObject.getString("Location") : "";
		subLocation = jObject.getString("Sublocation") != "null" ? jObject.getString("Sublocation") : "";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String room) {
		this.location = room;
	}

	public String getSubLocation() {
		return subLocation;
	}

	public void setSubLocation(String subLocation) {
		this.subLocation = subLocation;
	}

}
