package com.dbs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.mapper.EmployeeMapper;
import com.dbs.po.Employee;
import com.dbs.service.EmployeeService;
import com.dbs.util.ReturnData;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;

	// 管理员查询所有的用户
	@Override
	@Test
	public List<Employee> AdminQueryAll() {

		return employeeMapper.AdminQueryAll();
	}

	// 验证用户信息
	@Override
	public ReturnData checkEmp(Employee employee, HttpSession session) {
		Employee emp = employeeMapper.checkEmp(employee);

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

		return returnData;
	}

	// 查询个人信息
	@Override
	public Employee queryEmployeeForSelf(Employee employee) {
		return employeeMapper.queryEmployeeForSelf(employee);

	}

	// 注册用户信息
	@Override
	public ReturnData registerForEmployee(Employee employee, HttpSession session) {

		ReturnData returnData = new ReturnData();
		String username = (String) session.getAttribute("EmployeeName");
		System.out.println("测试session的值：" + username);
		// 查询请求注册的员工编号是否已经存在
		Employee testforEmpno = employeeMapper.queryEmployeeForSelf(employee);
		if (testforEmpno != null) {
			returnData.setKey(returnData.FAIL);
			returnData.setMsg("该员工编号已存在，请重新注册!");
		} else {
			// 员工编号为未注册过的,再次检查用户名是否注册过
			// 用户名已经注册
			Employee testforEname = employeeMapper.selectByName(employee);
			if (testforEname != null) {
				returnData.setKey(returnData.FAIL);
				returnData.setMsg("该用户名已经存在，请重新注册！");
			} else {
				// 员工编号与用户名都未被注册,即表示可以使用

				// 前端传来注册管理员的请求
				if (employee.getE_character().equals("管理员")) {
					Employee emp = new Employee();
					emp.setE_name(username);
					// 查询登录的人员是否是管理员
					Employee emp2 = employeeMapper.selectByNameAndCharacter(emp);
					System.out.println("查询是否是管理员的结果" + emp2);

					// 登录人员是管理员
					if (emp2 != null) {
						System.out.println("您是管理员可以开始注册 管理员");
						// 开始注册管理员权限
						employeeMapper.registerForEmployee(employee);
						// 设置权限等级
						// employeeMapper.registerForLevel(employee);
						Employee emp3 = employeeMapper.queryEmployeeForSelf(employee);
						if (emp3 != null) {
							System.out.println("管理员模式下的注册管理员结果" + emp3);
							returnData.setKey(returnData.SUCCESS);
							returnData.setMsg("符合管理员权限,已成功注册");
						}
					} else {
						// 登录人员不是管理员
						System.out.println("你不是管理员，不能注册管理员");
						returnData.setKey(returnData.FAIL);
						returnData.setMsg("您不是管理员，不能注册管理员!");
					}
				} else {
					// 前端传来非注册管理员的请求
					// 开始注册
					employeeMapper.registerForEmployee(employee);
					// 设置权限等级
					// employeeMapper.registerForLevel(employee);

					Employee emp4 = employeeMapper.queryEmployeeForSelf(employee);
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
		}
		return returnData;
	}

	

	// 修改用户信息
	@Override
	public ReturnData updateForEmployee(Employee employee, HttpSession session) {
		ReturnData returnData = new ReturnData();
		
		//判断请求的更新的e_empno是否存在
		Employee empforEmpno = employeeMapper.queryEmployeeForSelf(employee);
		if(empforEmpno!=null) {
			// 接收用户登录时的Name
			String username = (String) session.getAttribute("EmployeeName");
			// 查询请求的修改的e_name是否已经存在
			Employee testname = employeeMapper.selectByName(employee);
			// e_name已经存在
			if (testname != null) {
				returnData.setKey(returnData.FAIL);
				returnData.setMsg("用户名已存在，请重新设置!");
			} else {
				// 请求的用户名可以用的情况
				// 查询登录的人员是否是管理员
				Employee emp = new Employee();
				emp.setE_name(username);
				Employee emp2 = employeeMapper.selectByNameAndCharacter(emp);
				// 是管理员的情况下执行的更新操作
				if (emp2 != null) {
					// 更新基本信息
					employeeMapper.updateForEmployee(employee);
					// 自动更新权限
					// employeeMapper.registerForLevel(employee);
					returnData.setKey(returnData.SUCCESS);
					returnData.setMsg("尊敬的管理员，您已经成功修改信息");
				}
				// 不是管理员的情况下执行的更新操作
				else {

					// 判断非管理员登录用户是否修改的是自己的信息
					Employee emp3 = employeeMapper.selectByName(emp);

					// 修改自己的信息
					if (emp3.getE_empno() == employee.getE_empno()) {

						// 前端请求是否含有角色修改(只有管理员才能修改)
						if (employee.getE_character() == null || employee.getE_character() == "") {
							employeeMapper.updateForEmployee(employee);
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

			}
		}else {
			returnData.setKey(returnData.FAIL);
			returnData.setMsg("员工号不存在，请重新输入");
		}
		
		

		return returnData;

	}

	// 删除用户信息
	@Override
	public ReturnData deleteEmpInfo(Employee employee, HttpSession session) {
		ReturnData returnData = new ReturnData();
		// 接受登录用户的e_name;
		String username = (String) session.getAttribute("EmployeeName");
		// 判断是否是管理员
		Employee emp = new Employee();
		emp.setE_name(username);
		Employee empAdmin = employeeMapper.selectByNameAndCharacter(emp);
		
		Employee empexist = employeeMapper.queryEmployeeForSelf(employee);
		//数据库含有需要删除的信息
		if(empexist!=null) {
			if (empAdmin != null) {
				employeeMapper.deleteEmpInfo(employee);
				Employee empdelete = new Employee();
				empdelete = employeeMapper.queryEmployeeForSelf(employee);
				if(empdelete==null) {
					returnData.setKey(returnData.SUCCESS);
					returnData.setMsg("成功删除");
				}else {
					returnData.setKey(returnData.FAIL);
					returnData.setMsg("成功失败");
				}
				
			} else {
				returnData.setKey(returnData.FAIL);
				returnData.setMsg("您不是管理员,没有权限删除!");
			}
		}
		//s数据库中已经没了需要删除的信息
		else {
			returnData.setKey(returnData.FAIL);
			returnData.setMsg("您所需要删除的数据不存在!");
		}
		
		/*
		//将数据库表中信息封装进入list中然后存进returnData
		List<Object> emps = new ArrayList<Object>();
		List<Employee> emplist = new ArrayList<Employee>();
		emplist = employeeMapper.AdminQueryAll();
		for (Employee emp2 : emplist) {
			emps.add(emp2);
		}
		returnData.setBody(emps);
		*/
		return returnData;
	}

	// 注销用户信息
	@Override
	public void logoutForEmployee(Employee employee) {

	}

	// 查询是否是管理员
	@Override
	public Employee selectByNameAndCharacter(Employee employee) {

		return employeeMapper.selectByNameAndCharacter(employee);
	}

	// 根据用户名查找用户
	@Override
	public Employee selectByName(Employee employee) {
		return employeeMapper.selectByName(employee);
	}

	// 根据查询e_mpno显示出用户信息
	@Override
	public ReturnData showinfo(Employee employee, HttpSession session) {
		
		ReturnData returnData = new ReturnData();
		// 判断登录用户是否是管理员
		String username = (String) session.getAttribute("EmployeeName");
		System.out.println(username);
		Employee user = new Employee();
		user.setE_name(username);
		Employee admain = employeeMapper.selectByNameAndCharacter(user);
		System.out.println(admain);
		if (admain != null) {
			// 登录用户是管理员
			List<Object> emps = new ArrayList<Object>();
			// 根据e_emppno查找
			Employee emp = employeeMapper.queryEmployeeForSelf(employee);
			System.out.println(emp);
			
			//查询的员工编号存在
			if (emp != null) {
				returnData.setKey(returnData.SUCCESS);
				emps.add(emp);

			} else {
				returnData.setKey(returnData.FAIL);
				returnData.setMsg("查找失败,该员工编号不存在");
			}
			returnData.setBody(emps);
		} else {
			//登录人员不是管理员
			//根据前台请求信息设置对象
			Employee emp = employeeMapper.queryEmployeeForSelf(employee);
			// 存储登录用户的的实体对象
			Employee user2 = new Employee();
			user2.setE_name(username);
			Employee userself = employeeMapper.selectByName(user2);

			// 解决空指针异常
			if (emp != null) {
				//不加前置条件会出空指针异常
				if (emp.getE_empno() == userself.getE_empno()) {
					List<Object> emps = new ArrayList<Object>();
					returnData.setKey(returnData.SUCCESS);
					emps.add(emp);
					returnData.setBody(emps);
				} else {
					returnData.setKey(returnData.FAIL);
					returnData.setMsg("您不是管理员,没有权限查看别人的信息");
				}
			} else {

				// 查询的e_mpno为空
				returnData.setKey(returnData.FAIL);
				returnData.setMsg("不存在的用户编号");
			}

		}
		return returnData;
	}

}
