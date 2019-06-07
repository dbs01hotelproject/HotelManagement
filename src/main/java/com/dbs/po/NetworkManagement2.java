package com.dbs.po;

import java.util.Date;

public class NetworkManagement2 {
	private RoomInformation roominformation;

	private int n_serialnumber;
	private int n_roomnumber;
	private int n_customernumbernumber;
	private String n_opentime;
	private String n_closetime;
	public RoomInformation getRoominformation() {
		return roominformation;
	}
	public void setRoominformation(RoomInformation roominformation) {
		this.roominformation = roominformation;
	}
	public int getN_serialnumber() {
		return n_serialnumber;
	}
	public void setN_serialnumber(int n_serialnumber) {
		this.n_serialnumber = n_serialnumber;
	}
	public int getN_roomnumber() {
		return n_roomnumber;
	}
	public void setN_roomnumber(int n_roomnumber) {
		this.n_roomnumber = n_roomnumber;
	}
	public int getN_customernumbernumber() {
		return n_customernumbernumber;
	}
	public void setN_customernumbernumber(int n_customernumbernumber) {
		this.n_customernumbernumber = n_customernumbernumber;
	}
	public String getN_opentime() {
		return n_opentime;
	}
	public void setN_opentime(String n_opentime) {
		this.n_opentime = n_opentime;
	}
	public String getN_closetime() {
		return n_closetime;
	}
	public void setN_closetime(String n_closetime) {
		this.n_closetime = n_closetime;
	}
	@Override
	public String toString() {
		return "NetworkManagement [roominformation=" + roominformation + ", n_serialnumber=" + n_serialnumber
				+ ", n_roomnumber=" + n_roomnumber + ", n_customernumbernumber=" + n_customernumbernumber
				+ ", n_opentime=" + n_opentime + ", n_closetime=" + n_closetime + "]";
	}
	
	


}
