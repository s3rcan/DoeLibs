package com.groupd2.doelibs.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Book {
	private String ISBN;
	private String title;
	private String author;
	private String publisher;
	private String editionNo;
	private String editionYear;
	private String yearOfFirstEdition;
	private boolean reservedBySelf;

	private ArrayList<Loanable> loanables;
	private ArrayList<Reservation> reservations;

	public Book(JSONObject jObject) throws Exception {
		JSONObject jBook = jObject.getJSONObject("book");

		ISBN = jBook.getString("ISBN") != "null" ? jBook.getString("ISBN") : "";
		title = jBook.getString("Title") != "null" ? jBook.getString("Title")
				: "";
		author = jBook.getString("Author") != "null" ? jBook
				.getString("Author") : "";
		publisher = jBook.getString("Publisher") != "null" ? jBook
				.getString("Publisher") : "";
		editionNo = jBook.getString("EditionNo") != "null" ? jBook
				.getString("EditionNo") : "";
		editionYear = jBook.getString("EditionYear") != "null" ? jBook
				.getString("EditionYear") : "";
		yearOfFirstEdition = jBook.getString("YearOfFirstEdition") != "null" ? jBook
				.getString("YearOfFirstEdition") : "";
				
		reservedBySelf = jObject.getBoolean("reserveBySelf");

		loanables = new ArrayList<Loanable>();
		JSONArray jArray = jObject.getJSONArray("loanables");
		for (int i = 0; i < jArray.length(); i++)
			loanables.add(new Loanable(jArray.getJSONObject(i)));

		reservations = new ArrayList<Reservation>();
		jArray = jObject.getJSONArray("reservations");
		for (int i = 0; i < jArray.length(); i++)
			reservations.add(new Reservation(jArray.getJSONObject(i)));

	}

	public boolean isReservedBySelf() {
		return reservedBySelf;
	}

	public void setReservedBySelf(boolean reservedBySelf) {
		this.reservedBySelf = reservedBySelf;
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

	public ArrayList<Loanable> getLoanables() {
		return loanables;
	}

	public void setLoanables(ArrayList<Loanable> loanables) {
		this.loanables = loanables;
	}

	public ArrayList<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}

}
