package com.dbs.po;

public class Room {
	private int number; 
	private String direction;   //�����λ��
	private int tpye; //��������:0����;1˫��;3��˫;4����;5��ͳ
	private int equipment;  //�豸��״̬.0��1;
	private int state; //�����״̬ 0:�Ѿ�Ԥ�� 1��ס 2�շ�
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
