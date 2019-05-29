package com.dbs.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbs.po.Employee;
import com.dbs.service.EmployeeService;
import com.dbs.util.ReturnData;

@RequestMapping(value = "/EmployeeController")
@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "empLogin";
	}

	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public String management() {

		return "management";
	}
	
	@RequestMapping(value = "/empRegister", method = RequestMethod.GET)
	public String register() {

		return "empRegister";
	}
	
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logout(HttpSession session) {
		System.out.println("当前session:"+session.getAttribute("EmployeeName"));
		session.invalidate();
		System.out.println("session已清空");
		return "redirect: ../login.html";
	}

	/*
	 * 登录验证 接收页面请求的JSON数据,返回JSON格式结果
	 */
	@RequestMapping(value = "/login2")
	// @RequestBady 将请求体中的JSON数据绑定到形参employee中
	public @ResponseBody ReturnData checkLogin(@RequestBody Employee employee, HttpSession session) {
		System.out.println("开始checkEmp方法");
		
		//调用业务层的实现类
		ReturnData returnData = employeeService.checkEmp(employee, session);
		
		// 返回JSON格式响应
		return returnData;
	}

	/*
	 * 注册验证 接收页面请求的JSON数据,返回JSON格式结果
	 */
	@RequestMapping(value = "/register2")
	// @RequestBady 将请求体中的JSON数据绑定到形参employee中
	public @ResponseBody ReturnData registerCheck(@RequestBody Employee employee, HttpSession session) {
		System.out.println("开始registerCheck方法");
		
		//调用业务层的方法开始注册
		ReturnData returnData = employeeService.registerForEmployee(employee, session);
		
		// 返回JSON格式响应
		return returnData;
	}

	/*
	 * 更新操作之显示用户原本信息 
	 */
	@RequestMapping(value = "/search")
	// @RequestBady 将请求体中的JSON数据绑定到形参employee中
	public @ResponseBody ReturnData checkshowinfo(@RequestBody Employee employee, HttpSession session) {
		System.out.println("开始checkSearch方法");
		ReturnData returnData = new ReturnData();
		
		//调用业务层方法
		returnData = employeeService.showinfo(employee, session);
		
		// 返回JSON格式响应
		return returnData;
	}

	/*
	 * 修改用户信息  接收页面请求的JSON数据,返回JSON格式结果
	 */
	@RequestMapping(value = "/update")
	// @RequestBady 将请求体中的JSON数据绑定到形参employee中
	public @ResponseBody ReturnData updatecheck(@RequestBody Employee employee, HttpSession session) {
		System.out.println("开始updatecheck方法");
		
		//调用业务层的更新方法
		ReturnData returnData = employeeService.updateForEmployee(employee, session);

		// 返回JSON格式响应
		return returnData;
	}

	/*
	 * 删除用户信息  接收页面请求的JSON数据,返回JSON格式结果
	 */
	@RequestMapping(value = "/delete")
	// @RequestBady 将请求体中的JSON数据绑定到形参employee中
	public @ResponseBody ReturnData deletecheck(@RequestBody Employee employee, HttpSession session) {
		System.out.println("开始deletecheck方法");
		
		//调用业务层的更新方法
		ReturnData returnData = employeeService.deleteEmpInfo(employee, session);
		
		// 返回JSON格式响应
		return returnData;
	}
	

}
