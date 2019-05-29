package com.dbs.po;

import java.util.Date;

public class Network {
	private int serialnumber;
	private int roomnumber; //房间号
	private int customernumber; //用户号
	private Date opentime;
	private Date closetime;
	public int getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(int serialnumber) {
		this.serialnumber = serialnumber;
	}
	public int getRoomnumber() {
		return roomnumber;
	}
	public void setRoomnumber(int roomnumber) {
		this.roomnumber = roomnumber;
	}
	public int getCustomernumber() {
		return customernumber;
	}
	public void setCustomernumber(int customernumber) {
		this.customernumber = customernumber;
	}
	public Date getOpentime() {
		return opentime;
	}
	public void setOpentime(Date opentime) {
		this.opentime = opentime;
	}
	public Date getClosetime() {
		return closetime;
	}
	public void setClosetime(Date closetime) {
		this.closetime = closetime;
	}
	@Override
	public String toString() {
		return "Network [serialnumber=" + serialnumber + ", roomnumber=" + roomnumber + ", customernumber="
				+ customernumber + ", opentime=" + opentime + ", closetime=" + closetime + "]";
	}
	
}
