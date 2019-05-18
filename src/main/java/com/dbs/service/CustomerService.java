package com.dbs.service;

import com.dbs.po.Customer;

public interface CustomerService {

	// 通过id查询客户
	public Customer queryCustomerById(Integer id);

	// 添加客户
	public void addCustomer(Customer customer);

}
