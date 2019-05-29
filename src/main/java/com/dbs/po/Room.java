package com.dbs.po;

public class Room {
	private int number; 
	private String direction;   //房间的位置
	private int tpye; //房间类型:0单标;1双标;3豪双;4豪三;5总统
	private int equipment;  //设备的状态.0或1;
	private int state; //房间的状态 0:已经预定 1入住 2空房
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getTpye() {
		return tpye;
	}
	public void setTpye(int tpye) {
		this.tpye = tpye;
	}
	public int getEquipment() {
		return equipment;
	}
	public void setEquipment(int equipment) {
		this.equipment = equipment;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Room [number=" + number + ", direction=" + direction + ", tpye=" + tpye + ", equipment=" + equipment
				+ ", state=" + state + "]";
	}
	
}
