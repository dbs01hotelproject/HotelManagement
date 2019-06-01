package com.dbs.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.dbs.po.Employee;
import com.dbs.util.ReturnData;

public interface EmployeeService {

		//管理员查询所有的用户
		public ReturnData AdminQueryAll();
		
		//验证用户信息
		public ReturnData checkEmp(Employee employee,HttpSession session);
		
		//验证用户修改
		public Employee checkPwd(Employee employee);
		
		//查询个人信息
		public Employee queryEmployeeForSelf(Employee employee);
		
		//注册用户信息
		public ReturnData registerForEmployee(Employee employee, HttpSession session);
		
		//自动设置权限
		//public void registerForLevel(Employee employee);
		
		//根据管理员更新用户信息
		public ReturnData updateForAdmin(Employee employee, HttpSession session);
		
		//修改用户个人信息
		public ReturnData updateSelfInfo(Employee employee, HttpSession session);
		
		//删除用户信息
		public ReturnData deleteEmpInfo(Employee employee, HttpSession session);
		
		//注销用户信息
		public void logoutForEmployee(Employee employee);
		
		//查询是否是管理员
		public Employee selectByNameAndCharacter(Employee employee);
		
		//根据用户名查找用户
		public Employee selectByName(Employee employee);
		
		//根据查询显示用户信息
		public ReturnData showinfo(Employee employee,HttpSession session);
		
		
	
}
