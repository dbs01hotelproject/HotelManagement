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

	/*更新操作之显示用户原本信息
	 * 登录验证 接收页面请求的JSON数据,返回JSON格式结果
	 */
	@RequestMapping(value = "/search")
	// @RequestBady 将请求体中的JSON数据绑定到形参employee中
	public @ResponseBody ReturnData checkSearch(@RequestBody Employee employee, HttpSession session) {
		System.out.println("开始checkSearch方法");
		ReturnData returnData = new ReturnData();
		List<Object> emps = new ArrayList<Object>();
		//根据e_mepno查找
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
		// 返回JSON格式响应
		return returnData;
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

		// 查询员工编号是否已经存在
		Employee testforEmpno = employeeService.queryEmployeeForSelf(employee);
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

	/*
	 * 修改用户信息 登录验证 接收页面请求的JSON数据,返回JSON格式结果
	 */
	@RequestMapping(value = "/update")
	// @RequestBady 将请求体中的JSON数据绑定到形参employee中
	public @ResponseBody ReturnData updatecheck(@RequestBody Employee employee, HttpSession session) {
		System.out.println("开始updatecheck方法");
		ReturnData returnData = new ReturnData();

		// 接收用户登录时的Name
		String username = (String) session.getAttribute("EmployeeName");
		Employee emp = new Employee();
		emp.setE_name(username);
		// 查询登录的人员是否是管理员
		Employee emp2 = employeeService.selectByNameAndCharacter(emp);

		// 是管理员的情况下执行的更新操作
		if (emp2 != null) {
			// 更新基本信息
			employeeService.updateForEmployee(employee);
			// 自动更新权限
			employeeService.registerForLevel(employee);
			returnData.setKey(returnData.SUCCESS);
			returnData.setMsg("尊敬的管理员，您已经成功修改信息");
		}
		// 不是管理员的情况下执行的更新操作
		else {

			// 判断非管理员登录用户是否修改的是自己的信息
			Employee emp3 = employeeService.selectByName(emp);

			// 修改自己的信息
			if (emp3.getE_empno() == employee.getE_empno()) {

				// 前端请求是否含有角色修改(只有管理员才能修改)
				if (employee.getE_character() == null || employee.getE_character() == "") {
					employeeService.updateForEmployee(employee);
					returnData.setKey(returnData.SUCCESS);
					returnData.setMsg("您已经成功修改信息");
				} else {
					returnData.setKey(returnData.FAIL);
					returnData.setMsg("您不是管理员，不能修改角色信息");
				}
			} else {
				System.out.println("你不是管理员，不能修改他人信息");
				returnData.setKey(returnData.FAIL);
				returnData.setMsg("您不是管理员，不能修改他人信息");
			}

		}

		// 返回JSON格式响应
		return returnData;
	}
}
