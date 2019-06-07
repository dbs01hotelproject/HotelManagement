package com.dbs.po;

public class StateInfo {

	private int r_customernumber;
	private String c_name;
	private int r_roomnumber;
	private int r_tpye;
	public int getR_customernumber() {
		return r_customernumber;
	}
	public void setR_customernumber(int r_customernumber) {
		this.r_customernumber = r_customernumber;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public int getR_roomnumber() {
		return r_roomnumber;
	}
	public void setR_roomnumber(int r_roomnumber) {
		this.r_roomnumber = r_roomnumber;
	}
	public int getR_tpye() {
		return r_tpye;
	}
	public void setR_tpye(int r_tpye) {
		this.r_tpye = r_tpye;
	}
	@Override
	public String toString() {
		return "StateInfo [r_customernumber=" + r_customernumber + ", c_name=" + c_name + ", r_roomnumber="
				+ r_roomnumber + ", r_tpye=" + r_tpye + "]";
	}
	
	
	
}
