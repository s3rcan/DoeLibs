package com.groupd2.doelibs.models;

import org.json.JSONObject;

public class Loanable {
	private String location;
	private String subLocation;

	public Loanable(JSONObject jObject) throws Exception {
		location = jObject.getString("Location") != "null" ? jObject.getString("Location") : "";
		subLocation = jObject.getString("Sublocation") != "null" ? jObject.getString("Sublocation") : "";
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
