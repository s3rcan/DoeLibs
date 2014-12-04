package com.groupd2.doelibs;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

public class Borrowing {
	private int Tag;
	private String Title;
	private Date Expires;
	
	public int getTag() {
		return Tag;
	}
	public void setTag(int tag) {
		Tag = tag;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public Date getExpires() {
		return Expires;
	}
	public void setExpires(Date expires) {
		Expires = expires;
	}
	public String getExpiresAsString(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
		return sdf.format(this.Expires);
	}
		
	public Borrowing(){}
	
	public Borrowing(JSONObject jObject){
		try{
		this.Tag = jObject.getInt("Tag");
		this.Title = jObject.getString("Title");
		String date = jObject.getString("Expires");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
		this.Expires = sdf.parse(date);
		}
		catch(Exception e){}
	}
	
}
