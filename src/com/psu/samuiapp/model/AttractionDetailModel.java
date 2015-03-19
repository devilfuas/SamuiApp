package com.psu.samuiapp.model;

import java.net.URL;

public class AttractionDetailModel {
	
	private int id;
	private int categoryId;
	private String name;
	private String detail;
	private String address;
	private String picture;
	private String contact;
	private float latitude;
	private float longitude;
	private String time;
	private String service;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public String getOpeningTime() {
		return time;
	}
	public void setOpeningTime(String openingTime) {
		this.time = openingTime;
	}
	public String getServiceCharge() {
		return service;
	}
	public void setServiceCharge(String serviceCharge) {
		this.service = serviceCharge;
	}
	@Override
	public String toString() {
		return "AttractionDetailModel [id=" + id + ", categoryId=" + categoryId
				+ ", name=" + name + ", detail=" + detail + ", address="
				+ address + ", picture=" + picture + ", contact=" + contact
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", openingTime=" + time + ", serviceCharge="
				+ service + "]";
	}
	

}
