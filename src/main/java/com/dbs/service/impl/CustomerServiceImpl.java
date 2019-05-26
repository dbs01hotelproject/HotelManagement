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
	public Customer queryCustomer(Customer customer) throws Exception {
		return customerMapper.queryCustomer(customer);
	}

	@Override
	public List<RoomInformation> queryRoomInformation() throws Exception {
		return customerMapper.queryRoomInformation();
	}

	@Override
	public void insertReception(Reception reception) throws Exception {
		customerMapper.insertReception(reception);
	}

}
