package com.dbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.mapper.CustomerMapper;
import com.dbs.po.Customer;
import com.dbs.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerMapper customerMapper;

	public Customer queryCustomerById(Integer id) {
		return customerMapper.queryCustomerById(id);
	}

	public void addCustomer(Customer customer) {
		customerMapper.addCustomer(customer);
	}

}
