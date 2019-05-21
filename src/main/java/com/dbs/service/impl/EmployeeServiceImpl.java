package com.dbs.service.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.mapper.EmployeeMapper;
import com.dbs.po.Employee;
import com.dbs.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	@Test
	public List<Employee> AdminQueryAll() {
		
		return employeeMapper.AdminQueryAll();
	}

	@Override
	public void queryALLEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee queryEmployeeForSelf(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerForEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerForLevel(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateForEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteForEmployee(int e_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logoutForEmployee(int e_id) {
		// TODO Auto-generated method stub
		
	}

	
	
	


}
