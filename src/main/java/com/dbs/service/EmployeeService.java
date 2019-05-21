package com.dbs.service;

import java.util.List;

import com.dbs.po.Employee;

public interface EmployeeService {

	//管理员查询所有的用户
		public List<Employee> AdminQueryAll();
		
		//验证用户信息
		public void queryALLEmployee(Employee employee);
		
		//查询个人信息
		public Employee queryEmployeeForSelf(Employee employee);
		
		//注册用户信息
		public void registerForEmployee(Employee employee);
		
		//自动设置权限
		public void registerForLevel(Employee employee);
		
		//修改用户信息
		public void updateForEmployee(Employee employee);
		
		//删除用户信息
		public void deleteForEmployee(int e_id);
		
		//注销用户信息
		public void logoutForEmployee(int e_id);
	
}
