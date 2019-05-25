package com.dbs.po;

import javax.xml.crypto.Data;

/**
 * 入住信息
 * 
 * @author muyian
 *
 */
public class Reception {

	private int r_number;
	private int r_customernumber;
	private int r_roomnumber;
	private Data r_checkin;
	private Data r_leave;
	private float r_deposit;
	private int t_opennetwork;

	public int getR_number() {
		return r_number;
	}

	public void setR_number(int r_number) {
		this.r_number = r_number;
	}

	public int getR_customernumber() {
		return r_customernumber;
	}

	public void setR_customernumber(int r_customernumber) {
		this.r_customernumber = r_customernumber;
	}

	public int getR_roomnumber() {
		return r_roomnumber;
	}

	public void setR_roomnumber(int r_roomnumber) {
		this.r_roomnumber = r_roomnumber;
	}

	public Data getR_checkin() {
		return r_checkin;
	}

	public void setR_checkin(Data r_checkin) {
		this.r_checkin = r_checkin;
	}

	public Data getR_leave() {
		return r_leave;
	}

	public void setR_leave(Data r_leave) {
		this.r_leave = r_leave;
	}

	public float getR_deposit() {
		return r_deposit;
	}

	public void setR_deposit(float r_deposit) {
		this.r_deposit = r_deposit;
	}

	public int getT_opennetwork() {
		return t_opennetwork;
	}

	public void setT_opennetwork(int t_opennetwork) {
		this.t_opennetwork = t_opennetwork;
	}

}
