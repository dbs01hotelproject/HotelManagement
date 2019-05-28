package com.dbs.po;

public class Employee {

	private int e_empno;
	private String e_name;
	private String e_pass;
	private String e_character;
	public int getE_empno() {
		return e_empno;
	}
	public void setE_empno(int e_empno) {
		this.e_empno = e_empno;
	}
	public String getE_name() {
		return e_name;
	}
	public void setE_name(String e_name) {
		this.e_name = e_name;
	}
	public String getE_pass() {
		return e_pass;
	}
	public void setE_pass(String e_pass) {
		this.e_pass = e_pass;
	}
	public String getE_character() {
		return e_character;
	}
	public void setE_character(String e_character) {
		this.e_character = e_character;
	}
	
	public Employee() {
		super();
	}
	public Employee(String e_name, String e_pass, String e_character) {
		super();
		this.e_name = e_name;
		this.e_pass = e_pass;
		this.e_character = e_character;
	}
	@Override
	public String toString() {
		return "Employee [e_empno=" + e_empno + ", e_name=" + e_name + ", e_pass=" + e_pass + ", e_character="
				+ e_character + "]";
	}
	
	
	
	
	
}
