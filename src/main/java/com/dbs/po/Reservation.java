package com.dbs.po;

public class Reservation {

	private int r_number;
	private String r_name;
	
	private String r_date;
	private RoomInformation roomInformation;

	public RoomInformation getRoomInformation() {
		return roomInformation;
	}

	public void setRoomInformation(RoomInformation roomInformation) {
		this.roomInformation = roomInformation;
	}

	public int getR_number() {
		return r_number;
	}

	public void setR_number(int r_number) {
		this.r_number = r_number;
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

	public String getR_date() {
		return r_date;
	}

	public void setR_date(String r_date) {
		this.r_date = r_date;
	}

}
