package com.dbs.service.impl;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.dbs.mapper.EmployeeMapper;
import com.dbs.po.Employee;
import com.dbs.service.EmployeeService;

@Controller
public class FuntionTest {

	@Autowired
	EmployeeService employeeService;
	
	@Test
	public void AdminQueryAllTestfor() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmployeeMapper employeeMapper = ctx.getBean(EmployeeMapper.class);
		System.out.println(employeeMapper.AdminQueryAll());
	}
	
	@Test
	public void checkEmpTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmployeeMapper employeeMapper = ctx.getBean(EmployeeMapper.class);
		Employee employee = new Employee();
		employee.setE_name("杨过");
		employee.setE_pass("77778888");
		System.out.println(employeeMapper.checkEmp(employee));
	}
	
	 @Test
	public void queryEmployeeForSelfTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmployeeMapper employeeMapper = ctx.getBean(EmployeeMapper.class);
		Employee employee = new Employee();
		employee.setE_empno(1);
		employee.setE_name("杨过");
		employee.setE_pass("77778888");
		System.out.println(employeeMapper.queryEmployeeForSelf(employee));
	}
	
	@Test
	public void registerForEmployeeTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmployeeMapper employeeMapper = ctx.getBean(EmployeeMapper.class);
		Employee employee = new Employee();
		employee.setE_character("前台接待");
		employee.setE_name("王语嫣");
		employee.setE_pass("66666671");
		employeeMapper.registerForEmployee(employee);
	}
	
	@Test
	public void registerForLevelTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmployeeMapper employeeMapper = ctx.getBean(EmployeeMapper.class);
		Employee employee = new Employee();
		employee.setE_empno(11);
		employee.setE_character("前台接待");
		employeeMapper.registerForLevel(employee);
	}
	
	@Test
	public void updateForEmployeeTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		EmployeeMapper employeeMapper = ctx.getBean(EmployeeMapper.class);
		Employee employee = new Employee();
		employee.setE_empno(11);
		employee.setE_character("前台接待");
		employee.setE_name("钟灵");
		employee.setE_pass("66666672");
		employeeMapper.updateForEmployee(employee);
	}
	
	@Test
	public void deleteForEmployeeTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		EmployeeMapper employeeMapper = ctx.getBean(EmployeeMapper.class);
		Employee employee = new Employee();
		employee.setE_empno(5);
		employeeMapper.deleteEmpInfo(employee);
	}
	
	@Test
	public void logoutForEmployeeTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		EmployeeMapper employeeMapper = ctx.getBean(EmployeeMapper.class);
		Employee employee = new Employee();
		employee.setE_empno(6);
		employeeMapper.logoutForEmployee(employee);
	}
	
}
