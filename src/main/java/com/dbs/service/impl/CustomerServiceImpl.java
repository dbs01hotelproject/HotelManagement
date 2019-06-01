package com.dbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.mapper.CustomerMapper;
import com.dbs.po.Customer;
import com.dbs.po.Reception;
import com.dbs.po.RoomInformation;
import com.dbs.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public List<Customer> queryCustomer(Customer customer) throws Exception {
		return customerMapper.queryCustomer(customer);
	}

	@Override
	public List<RoomInformation> queryRoomInformation() throws Exception {
		return customerMapper.queryRoomInformation();
	}

	@Override
	public int insertReception(Reception reception) throws Exception {
		return customerMapper.insertReception(reception);
	}

	@Override
	public void checkinCustomer(Customer customer) throws Exception {
		customerMapper.checkinCustomer(customer);
	}

	@Override
	public void updateCustomer(Customer customer) throws Exception {
		customerMapper.updateCustomer(customer);
	}

	@Override
	public void delCustomer(Customer customer) throws Exception {
		customerMapper.delCustomer(customer);
	}

	@Override
	public void updateRoomState(Integer id) throws Exception {
		customerMapper.updateRoomState(id);
	}

}
