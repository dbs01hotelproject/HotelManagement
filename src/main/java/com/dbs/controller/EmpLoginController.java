package com.dbs.controller;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbs.po.Employee;
import com.dbs.service.EmployeeService;
import com.dbs.util.ReturnData;

import org.springframework.http.converter.xml.AbstractXmlHttpMessageConverter;


@RequestMapping(value="/EmpLoginController")
@Controller
public class EmpLoginController {

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		
		return "login";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register() {
		
		return "register";
	}
	
	@RequestMapping(value="/login2")
	public @ResponseBody ReturnData queryforAll(HttpServletRequest request ,HttpServletResponse response,HttpSession session) {
		System.out.println("开始queryforAll方法");
		String e_name = request.getParameter("e_name");
		String e_pass = request.getParameter("e_pass");
		Employee employee = new Employee();
		employee.setE_name(e_name);
		employee.setE_pass(e_pass);
		Employee emp = employeeService.checkEmp(employee);
		
		System.out.println(emp);
		
		ReturnData returnData = new ReturnData();
		List<Object> emps = new ArrayList<Object>();
		
		if(emp!=null) {
			returnData.setKey("success");
			returnData.setMsg("查找用户成功");
			session.setAttribute(emp.getE_name(), emp.getE_name());
			emps.add(emp);
		}else {
			returnData.setKey("fail");
			returnData.setMsg("查找用户失败");
		}
		
		returnData.setBody(emps);
		
		
		return returnData;
	}
	
}
