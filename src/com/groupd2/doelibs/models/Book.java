package com.groupd2.doelibs.models;

import org.json.JSONObject;

public class Book {
	private String ISBN;
	private String title;
	private String author;
	private String publisher;
	private String editionNo;
	private String editionYear;
	private String yearOfFirstEdition;

	public Book(JSONObject jObject) throws Exception {
		ISBN = jObject.getString("ISBN") != "null" ? jObject.getString("ISBN") : "";
		title = jObject.getString("Title") != "null" ? jObject.getString("Title") : "";
		author = jObject.getString("Author") != "null" ? jObject.getString("Author") : "";
		publisher = jObject.getString("Publisher") != "null" ? jObject.getString("Publisher") : "";
		editionNo = jObject.getString("EditionNo") != "null" ? jObject.getString("EditionNo") : "";
		editionYear = jObject.getString("EditionYear") != "null" ? jObject.getString("EditionYear") : "";
		yearOfFirstEdition = jObject.getString("YearOfFirstEdition") != "null" ? jObject.getString("YearOfFirstEdition") : "";
		
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getEditionNo() {
		return editionNo;
	}

	public void setEditionNo(String editionNo) {
		this.editionNo = editionNo;
	}

	public String getEditionYear() {
		return editionYear;
	}

	public void setEditionYear(String editionYear) {
		this.editionYear = editionYear;
	}

	public String getYearOfFirstEdition() {
		return yearOfFirstEdition;
	}

	public void setYearOfFirstEdition(String yearOfFirstEdition) {
		this.yearOfFirstEdition = yearOfFirstEdition;
	}

}
