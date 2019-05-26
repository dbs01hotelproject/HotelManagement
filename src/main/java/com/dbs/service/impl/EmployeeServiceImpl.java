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
	public Employee checkEmp(Employee employee) {
		return employeeMapper.checkEmp(employee);
		
	}

	@Override
	public Employee queryEmployeeForSelf(Employee employee) {
		return employeeMapper.queryEmployeeForSelf(employee);

	}

	@Override
	public void registerForEmployee(Employee employee) {
			if(employeeMapper.queryEmployeeForSelf(employee)==null) {
				employeeMapper.registerForEmployee(employee);
			}else {
				System.out.println("该员工编号已经存在，请重新注册!");
			}
			
	}

	@Override
	public void registerForLevel(Employee employee) {
		employeeMapper.registerForLevel(employee);
	}

	@Override
	public void updateForEmployee(Employee employee) {
		//解决选项都为空，更新异常的bug
		if(employee.getE_pass()==null&&
				employee.getE_name()==null&&
				employee.getE_character()==null) {
			System.out.println("密码、用户名和角色都为空,无法更新!");
		}else {
			employeeMapper.updateForEmployee(employee);
		}
		
	}

	@Override
	public void deleteEmpInfo(Employee employee) {
		employeeMapper.deleteEmpInfo(employee);
	}

	@Override
	public void logoutForEmployee(Employee employee) {
		
	}

	@Override
	public Employee selectByNameAndCharacter(Employee employee) {

		return employeeMapper.selectByNameAndCharacter(employee);
	}

	@Override
	public Employee selectByName(Employee employee) {
		return employeeMapper.selectByName(employee);
	}

	
	
	


}
