package com.dbs.mapper;

import com.dbs.po.Customer;
import com.dbs.po.Network;
import com.dbs.po.Room;

/**
 * 订房信息
 * @author 16675
 *
 */
public interface ReservationMapper {
	//增加订房信息,依赖于房间号, 用户下单后用户名号,房间号存放在接待信息表里,我要修改的只是订房信息表;
	//public void addReservarion(Customer customer);
	
	//查看订房信息
	public void queryReservation(Integer number);
	
	
	//删除订房信息
	public void deleteReservation(Integer number);
	
	
	
	//查询网络信息,开通网络信息的东西不归我管
	public Network queryNetworkbyroomnumber(Integer roomnumber);
	
	
	//以房间号码查询房间信息
	public Room queryRoombynumber(Integer number);
	
	
}
