package com.dbs.mapper;

import java.util.List;

import com.dbs.po.Customer;
import com.dbs.po.NetworkInformation;
import com.dbs.po.Reception;
import com.dbs.po.RoomInformation;

public interface CustomerMapper {

	// 登记客户信息
	public int checkinCustomer(Customer customer) throws Exception;

	// 修改客户信息
	public void updateCustomer(Customer customer) throws Exception;

	// 删除客户信息
	public void delCustomer(Customer customer) throws Exception;

	// 查询客户信息
	public List<Customer> queryCustomer(Customer customer) throws Exception;

	// 查询房态状态
	public List<RoomInformation> queryRoomInformation(RoomInformation room) throws Exception;

	// 办理客户入住
	public int insertReception(Reception reception) throws Exception;

	// 改变房间状态
	public int updateRoomState(RoomInformation roomInformation) throws Exception;

	// 登记客户信息
	public int insertNetwork(NetworkInformation networkInformation) throws Exception;

}
