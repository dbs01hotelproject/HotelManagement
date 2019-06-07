package com.dbs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

		return "login";
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
		ReturnData returnData = employeeService.updateForAdmin(employee, session);

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
	
	@RequestMapping(value = "/searchAll")
	// @RequestBady 将请求体中的JSON数据绑定到形参employee中
	public @ResponseBody ReturnData searchAll(@RequestBody Employee employee, HttpSession session) {
		System.out.println("开始searchAll方法");
		
		//调用业务层的更新方法
		ReturnData returnData = employeeService.AdminQueryAll();
		
		// 返回JSON格式响应
		return returnData;
	}
	
	@RequestMapping(value = "/selectbyempno")
	// @RequestBady 将请求体中的JSON数据绑定到形参employee中
	public @ResponseBody ReturnData searchone(@RequestBody Employee employee, HttpSession session) {
		System.out.println("开始searchone方法");
		List<Object> emps = new ArrayList<Object>();
		//调用业务层的查询方法
		Employee emp = employeeService.queryEmployeeForSelf(employee);
		emps.add(emp);
		ReturnData returnData = new ReturnData();
		returnData.setBody(emps);
		// 返回JSON格式响应
		return returnData;
	}
	
	//修改密码
	@RequestMapping(value = "/updatepass")
	public @ResponseBody ReturnData updatepass(HttpServletRequest request, HttpSession session) {
		System.out.println("开始updatepass方法");
		ReturnData returnData = new ReturnData();
		Employee testforOld = new Employee();
		Employee testforNew = new Employee();
		String e_name = (String) session.getAttribute("EmployeeName");
		String e_pass = request.getParameter("e_pass");
		String old_password =request.getParameter("old_password");
		System.out.println("新密码:"+e_pass+" 旧密码:"+old_password);
		
		testforOld.setE_pass(old_password);
		testforOld.setE_name(e_name);
		
		//设置员工号，否则无法更新
		testforNew.setE_pass(e_pass);
		testforNew.setE_name(e_name);
		Employee emp2 = employeeService.selectByName(testforOld);
		testforNew.setE_empno(emp2.getE_empno());
		
		//查询旧密码是否正确
		Employee empold = new Employee();
		empold = employeeService.checkPwd(testforOld);
		
		if(empold!=null) {
			employeeService.updateSelfInfo(testforNew, session);
			returnData.setKey(returnData.SUCCESS);
			returnData.setMsg("修改密码成功");
		}else {
			returnData.setKey(returnData.FAIL);
			returnData.setMsg("输入密码错误,修改密码失败");
		}
		
		return returnData;
	}
	
	@RequestMapping(value = "/updatPersonInfo")
	public @ResponseBody ReturnData updatPersonInfo(HttpServletRequest request, HttpSession session) {
		System.out.println("开始updatPersonInfo方法");
		ReturnData returnData = new ReturnData();
		//获取登录的用户
		String e_name = (String) session.getAttribute("EmployeeName");
		Employee emp = new Employee();
		emp.setE_name(e_name);
		Employee user = employeeService.selectByName(emp);
		System.out.println("user:"+user);
		//设置员工号，否则无法更新
		Employee user2 = new Employee();
		user2.setE_empno(user.getE_empno());
		user2.setE_name(request.getParameter("e_name"));
		//判断请求的更新的用户名是否存在
		if(employeeService.selectByName(user2)!=null) {
			returnData.setKey(returnData.FAIL);
			returnData.setMsg("用户名已存在,请重新输入!");
		}else {
			employeeService.updateSelfInfo(user2, session);
			session.setAttribute("EmployeeName", user2.getE_name());
			returnData.setKey(returnData.SUCCESS);
			returnData.setMsg("修改信息成功");
		}
		
		return returnData;
	}
	
	
}
