package com.psu.samuiapp.model;

public class EventModel {

	 int id;
	 String name;
	 String date;
	 String link;
	 
	 
	 
	public EventModel(int id, String name, String date, String link) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.link = link;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	 
	 

}
