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

import com.dbs.mapper.EmployeeMapper;
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

	/*
	 * 登录验证 接收页面请求的JSON数据,返回JSON格式结果
	 */
	@RequestMapping(value = "/login2")
	// @RequestBady 将请求体中的JSON数据绑定到形参employee中
	public @ResponseBody ReturnData checkLogin(@RequestBody Employee employee, HttpSession session) {
		System.out.println("开始checkEmp方法");
		Employee emp = employeeService.checkEmp(employee);

		System.out.println(emp);

		ReturnData returnData = new ReturnData();
		List<Object> emps = new ArrayList<Object>();

		if (emp != null) {
			returnData.setKey("success");
			returnData.setMsg("登录成功");
			session.setAttribute("EmployeeName", emp.getE_name());
			emps.add(emp);
		} else {
			returnData.setKey("fail");
			returnData.setMsg("登录失败");
		}
		returnData.setBody(emps);
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
		ReturnData returnData = new ReturnData();
		String username = (String) session.getAttribute("EmployeeName");
		System.out.println("测试session的值：" + username);
		Employee testforEmpno = employeeService.queryEmployeeForSelf(employee);

		// 判断员工编号是否已经存在
		if (testforEmpno != null) {
			returnData.setKey(returnData.FAIL);
			returnData.setMsg("该员工编号已存在，请重新注册!");
		} else {
			// 前端传来注册管理员的请求
			if (employee.getE_character().equals("管理员")) {

				Employee emp = new Employee();

				emp.setE_name(username);
				// 查询登录的人员是否是管理员
				Employee emp2 = employeeService.selectByNameAndCharacter(emp);
				System.out.println(emp2);
				if (emp2 != null) {
					System.out.println("您是管理员可以开始注册 管理员");
					// 开始注册管理员权限
					employeeService.registerForEmployee(employee);
					// 设置权限等级
					employeeService.registerForLevel(employee);
					Employee emp3 = employeeService.queryEmployeeForSelf(employee);
					if (emp3 != null) {
						System.out.println("管理员模式下的注册管理员结果" + emp3);
						returnData.setKey(returnData.SUCCESS);
						returnData.setMsg("符合管理员权限,已成功注册");
					} else {
						returnData.setKey(returnData.FAIL);
						returnData.setMsg("符合管理员权限,但注册失败！");
					}

				} else {
					System.out.println("你不是管理员，不能注册管理员");
					returnData.setKey(returnData.FAIL);
					returnData.setMsg("您不是管理员，不能注册管理员!");
				}
			}
			// 前端传来非注册管理员的请求
			else {

				// 开始注册
				employeeService.registerForEmployee(employee);
				// 设置权限等级
				employeeService.registerForLevel(employee);
				Employee emp4 = employeeService.queryEmployeeForSelf(employee);
				System.out.println("注册非管理员的结果" + emp4);
				if (emp4 != null) {
					returnData.setKey(returnData.SUCCESS);
					returnData.setMsg("注册成功");
				} else {
					returnData.setKey(returnData.FAIL);
					returnData.setMsg("注册失败");
				}
			}

		}
		// 返回JSON格式响应
		return returnData;
	}

}
