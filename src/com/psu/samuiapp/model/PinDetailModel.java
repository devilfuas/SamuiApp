package com.psu.samuiapp.model;

public class PinDetailModel {
	
	int id;
	String name;
	String address;
	String contact;
	Double latitude = 0.00;
	Double longitude = 0.00;

	public PinDetailModel(int id, String name, String address, String contact,
			Double latitude, Double longitude) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.latitude = latitude;
		this.longitude = longitude;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "PinDetailModel [id=" + id + ", name=" + name + ", address="
				+ address + ", contact=" + contact + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}
	
	

}
