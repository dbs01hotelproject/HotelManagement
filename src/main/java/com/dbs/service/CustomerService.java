package com.dbs.service;

import java.util.List;

import com.dbs.po.Customer;
import com.dbs.po.Reception;
import com.dbs.po.RoomInformation;

public interface CustomerService {

	// 根据id查询客户信息
	public Customer queryCustomer(Customer customer) throws Exception;

	// 查询房态状态
	public List<RoomInformation> queryRoomInformation() throws Exception;

	// 办理客户入住
	public int insertReception(Reception reception) throws Exception;
}
