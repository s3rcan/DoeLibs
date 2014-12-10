package com.groupd2.doelibs.models;

import org.json.JSONObject;

public class Loanable {
	/*
	 * Owner = d.Lender.Fullname, Location = d.Location.Room, Sublocation =
	 * d.Location.LocationCategory, Status = d.Status
	 */
	private String owner;
	private String location;
	private String subLocation;
	private String status;

	public Loanable(JSONObject jObject) throws Exception {
		owner = jObject.getString("Owner") != "null" ? jObject
				.getString("Owner") : "";
		location = jObject.getString("Location") != "null" ? jObject
				.getString("Location") : "";
		subLocation = jObject.getString("Sublocation") != "null" ? jObject
				.getString("Sublocation") : "";
		status = jObject.getString("Status") != "null" ? jObject
				.getString("Status") : "";
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSubLocation() {
		return subLocation;
	}

	public void setSubLocation(String subLocation) {
		this.subLocation = subLocation;
	}

}
