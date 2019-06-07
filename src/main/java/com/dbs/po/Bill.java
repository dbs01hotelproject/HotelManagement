package com.dbs.po;

public class Bill {

	private int b_number;
	private int b_customernumber;
	private String b_cname;
	private int b_roomnumber;
	private int b_type;
	private int b_day;
	private String b_leave;
	private int b_feerates;
	private float b_netcost;
	private float b_allcosts;
	public int getB_number() {
		return b_number;
	}
	public void setB_number(int b_number) {
		this.b_number = b_number;
	}
	public int getB_customernumber() {
		return b_customernumber;
	}
	public void setB_customernumber(int b_customernumber) {
		this.b_customernumber = b_customernumber;
	}
	
	
	public String getB_cname() {
		return b_cname;
	}
	public void setB_cname(String b_cname) {
		this.b_cname = b_cname;
	}
	public int getB_roomnumber() {
		return b_roomnumber;
	}
	public void setB_roomnumber(int b_roomnumber) {
		this.b_roomnumber = b_roomnumber;
	}
	public int getB_type() {
		return b_type;
	}
	public void setB_type(int b_type) {
		this.b_type = b_type;
	}
	public int getB_day() {
		return b_day;
	}
	public void setB_day(int b_day) {
		this.b_day = b_day;
	}
	public String getB_leave() {
		return b_leave;
	}
	public void setB_leave(String b_leave) {
		this.b_leave = b_leave;
	}
	public int getB_feerates() {
		return b_feerates;
	}
	public void setB_feerates(int b_feerates) {
		this.b_feerates = b_feerates;
	}
	public float getB_netcost() {
		return b_netcost;
	}
	public void setB_netcost(float b_netcost) {
		this.b_netcost = b_netcost;
	}
	public float getB_allcosts() {
		return b_allcosts;
	}
	public void setB_allcosts(float b_allcosts) {
		this.b_allcosts = b_allcosts;
	}
	@Override
	public String toString() {
		return "Bill [b_number=" + b_number + ", b_customernumber=" + b_customernumber + ", b_cname=" + b_cname
				+ ", b_roomnumber=" + b_roomnumber + ", b_type=" + b_type + ", b_day=" + b_day + ", b_leave=" + b_leave
				+ ", b_feerates=" + b_feerates + ", b_netcost=" + b_netcost + ", b_allcosts=" + b_allcosts + "]";
	}
	public Bill(int b_number, int b_customernumber, int b_roomnumber, int b_type, int b_day, String b_leave,
			int b_feerates, float b_netcost, float b_allcosts) {
		super();
		this.b_number = b_number;
		this.b_customernumber = b_customernumber;
		this.b_roomnumber = b_roomnumber;
		this.b_type = b_type;
		this.b_day = b_day;
		this.b_leave = b_leave;
		this.b_feerates = b_feerates;
		this.b_netcost = b_netcost;
		this.b_allcosts = b_allcosts;
	}
	
	
	public Bill() {
		super();
	}
	
	
	
}
