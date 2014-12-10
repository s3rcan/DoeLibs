package com.groupd2.doelibs.models;

import org.json.JSONException;
import org.json.JSONObject;

public class SearchResult {
	private String Title;
	private String ISBN;
	private String Authors;
	private String TitleId;

	public SearchResult(JSONObject jObject) {
		try {
			this.Authors = jObject.getString("Authors");
			this.Title = jObject.getString("Title");
			this.ISBN = jObject.getString("ISBN");
			this.TitleId = jObject.getString("TitleId");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getTitleId() {
		return TitleId;
	}

	public void setTitleId(String titleId) {
		TitleId = titleId;
	}

	public String getTitle() {
		return Title;
	}

	public String getISBN() {
		return ISBN;
	}

	public String getAuthors() {
		return Authors;
	}
}
