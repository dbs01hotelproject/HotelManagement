package com.dbs.service.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.dbs.mapper.CustomerMapper;
import com.dbs.mapper.EmployeeMapper;
import com.dbs.po.Employee;
import com.dbs.service.CustomerService;
import com.dbs.service.EmployeeService;

@Controller
public class FirstTest {

	
	@Test
	public void Testfor() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		CustomerMapper customerMapper = (CustomerMapper) ctx.getBean("customerMapper");
		System.out.println(customerMapper.queryCustomerById(1));
		EmployeeMapper employeeMapper = ctx.getBean(EmployeeMapper.class);
		System.out.println(employeeMapper.AdminQueryAll());
	}
}
