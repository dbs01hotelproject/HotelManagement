package com.dbs.po;

public class NetworkInformation {

	private int e_number;
	private int e_roomnumber;
	private int e_customernumbernumber;
	private String e_opentime;
	private String e_closetime;
	private float e_cost;
	
	public int getE_number() {
		return e_number;
	}
	public void setE_number(int e_number) {
		this.e_number = e_number;
	}
	public int getE_roomnumber() {
		return e_roomnumber;
	}
	public void setE_roomnumber(int e_roomnumber) {
		this.e_roomnumber = e_roomnumber;
	}
	public int getE_customernumbernumber() {
		return e_customernumbernumber;
	}
	public void setE_customernumbernumber(int e_customernumbernumber) {
		this.e_customernumbernumber = e_customernumbernumber;
	}
	public String getE_opentime() {
		return e_opentime;
	}
	public void setE_opentime(String e_opentime) {
		this.e_opentime = e_opentime;
	}
	public String getE_closetime() {
		return e_closetime;
	}
	public void setE_closetime(String e_closetime) {
		this.e_closetime = e_closetime;
	}
	public float getE_cost() {
		return e_cost;
	}
	public void setE_cost(float e_cost) {
		this.e_cost = e_cost;
	}
	@Override
	public String toString() {
		return "NetworkInformation [e_number=" + e_number + ", e_roomnumber=" + e_roomnumber
				+ ", e_customernumbernumber=" + e_customernumbernumber + ", e_opentime=" + e_opentime + ", e_closetime="
				+ e_closetime + ", e_cost=" + e_cost + "]";
	}
	
}
