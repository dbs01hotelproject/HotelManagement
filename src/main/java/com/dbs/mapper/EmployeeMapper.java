package com.dbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.dbs.po.Employee;
import com.dbs.util.ReturnData;

public interface EmployeeMapper {

	//管理员查询所有的用户
	public List<Employee> AdminQueryAll();
	
	//验证用户信息
	public Employee checkEmp(Employee employee);
	
	//查询个人信息
	public Employee queryEmployeeForSelf(Employee employee);
	
	//注册用户信息
	public void registerForEmployee(Employee employee);
	
	//自动设置权限
	public void registerForLevel(Employee employee);
	
	//修改用户信息
	public void updateForEmployee(Employee employee);
	
	//删除用户信息
	public void deleteEmpInfo(Employee employee);
	
	//注销用户信息
	public void logoutForEmployee(Employee employee);
}
