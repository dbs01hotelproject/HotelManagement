package com.dbs.controller;

import java.util.ArrayList;
import java.util.List;

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
	public String register() {

		return "management";
	}
	
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logout(HttpSession session) {
		System.out.println("现在登录的是"+session.getAttribute("EmployeeName"));
		session.removeAttribute("EmployeeName");
		System.out.println("现在登录的是"+session.getAttribute("EmployeeName"));
		return "empLogin";
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
	public @ResponseBody ReturnData checkSearch(@RequestBody Employee employee, HttpSession session) {
		System.out.println("开始checkSearch方法");
		ReturnData returnData = new ReturnData();
		//判断登录用户是否是管理员
		String username = (String) session.getAttribute("EmployeeName");
		Employee user = employeeService.selectByNameAndCharacter(employee);
		
		if(user!=null) {
			//登录用户是管理员
			List<Object> emps = new ArrayList<Object>();
			// 根据e_emppno查找
			Employee emp = employeeService.queryEmployeeForSelf(employee);
			System.out.println(emp);

			if (emp != null) {
				returnData.setKey(returnData.SUCCESS);
				emps.add(emp);

			} else {
				returnData.setKey(returnData.FAIL);
				returnData.setMsg("查找失败");
			}
			returnData.setBody(emps);
		}else {
			//根据前台请求信息设置对象
			Employee emp = employeeService.queryEmployeeForSelf(employee);
			//存储登录用户的的实体对象
			Employee user2 = new Employee();
			user2.setE_name(username);
			Employee userself = employeeService.selectByName(user2);
			
			if(emp.getE_empno()==userself.getE_empno()) {
				List<Object> emps = new ArrayList<Object>();
				returnData.setKey(returnData.SUCCESS);
				emps.add(emp);
				returnData.setBody(emps);
			}else {
				returnData.setKey(returnData.FAIL);
				returnData.setMsg("您不是管理员,没有权限查看别人的信息");
			}
			
		}
		
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
