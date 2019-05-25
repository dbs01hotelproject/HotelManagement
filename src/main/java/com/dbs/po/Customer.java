package com.dbs.po;

/**
 * 客户
 * @author muyian
 *
 */
public class Customer {

	private int c_customernumber;
	private String c_name;
	private String c_identity;
	private String c_sex;
	private String c_address;
	private String c_tel;

	public int getC_customernumber() {
		return c_customernumber;
	}

	public void setC_customernumber(int c_customernumber) {
		this.c_customernumber = c_customernumber;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_identity() {
		return c_identity;
	}

	public void setC_identity(String c_identity) {
		this.c_identity = c_identity;
	}

	public String getC_sex() {
		return c_sex;
	}

	public void setC_sex(String c_sex) {
		this.c_sex = c_sex;
	}

	public String getC_address() {
		return c_address;
	}

	public void setC_address(String c_address) {
		this.c_address = c_address;
	}

	public String getC_tel() {
		return c_tel;
	}

	public void setC_tel(String c_tel) {
		this.c_tel = c_tel;
	}

}
