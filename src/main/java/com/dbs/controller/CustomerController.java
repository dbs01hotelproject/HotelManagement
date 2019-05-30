package com.dbs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbs.po.Customer;
import com.dbs.po.Reception;
import com.dbs.po.RoomInformation;
import com.dbs.service.CustomerService;
import com.dbs.util.Common;
import com.dbs.util.ReturnData;

/**
 * 接待信息管理
 * @author muyian
 * @date 2019/5/26
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	/**
	 * 登记入住，登记客户信息
	 * @author lezhinan
	 * @param request
	 * @param response
	 * @return returndata
	 */
	@RequestMapping(value = "/checkinCustomer", method = RequestMethod.POST)
	public @ResponseBody ReturnData checkinCuStomer(HttpServletRequest request, HttpServletResponse response) {
		ReturnData returndata = new ReturnData();
		try {
			// 从前台获取插入参数
			Customer customer =new  Customer();
			customer.setC_address(request.getParameter("c_address"));
			customer.setC_identity(request.getParameter("c_identity"));
			customer.setC_name(request.getParameter("c_name"));
			customer.setC_sex(request.getParameter("c_sex"));
			customer.setC_tel(request.getParameter("c_tel"));
			//插入
			customerService.checkinCustomer(customer);
			returndata.setKey(ReturnData.SUCCESS);
			returndata.setMsg("登记客户信息成功");
		} catch (Exception e) {
			// 请求失败
			returndata.setKey(ReturnData.FAIL);
			returndata.setMsg("登记客户信息失败");
			e.printStackTrace();
		}
		return returndata;
	}
	/**
	 * 修改客户信息
	 * @author lezhinan
	 * @param request
	 * @param response
	 * @return returndata
	 */
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
	public @ResponseBody ReturnData updateCuStomer(HttpServletRequest request, HttpServletResponse response) {
		ReturnData returndata = new ReturnData();
		try {
			// 从前台获取插入参数
			Customer customer =new  Customer();
			customer.setC_address(Common.ckeckNull(request.getParameter("c_address")));
			customer.setC_customernumber(Integer.parseInt(request.getParameter("c_customernumber")));
			customer.setC_identity(Common.ckeckNull(request.getParameter("c_identity")));
			customer.setC_name(Common.ckeckNull(request.getParameter("c_name")));
			customer.setC_sex(Common.ckeckNull(request.getParameter("c_sex")));
			customer.setC_tel(Common.ckeckNull(request.getParameter("c_tel")));
			//插入
			customerService.updateCustomer(customer);
			returndata.setKey(ReturnData.SUCCESS);
			returndata.setMsg("修改客户信息成功");
		} catch (Exception e) {
			// 请求失败
			returndata.setKey(ReturnData.FAIL);
			returndata.setMsg("修改客户信息失败");
			e.printStackTrace();
		}
		return returndata;
	}
	/**
	 * 删除客户信息
	 * @author lezhinan
	 * @param request
	 * @param response
	 * @return returndata
	 */
	@RequestMapping(value = "/delCustomer", method = RequestMethod.POST)
	public @ResponseBody ReturnData delCuStomer(HttpServletRequest request, HttpServletResponse response) {
		ReturnData returndata = new ReturnData();
		try {
			// 从前台获取插入参数
			Customer customer =new  Customer();
			customer.setC_customernumber(Integer.parseInt(request.getParameter("c_customernumber")));
			//删除
			customerService.delCustomer(customer);
			returndata.setKey(ReturnData.SUCCESS);
			returndata.setMsg("删除客户信息成功");
		} catch (Exception e) {
			// 请求失败
			returndata.setKey(ReturnData.FAIL);
			returndata.setMsg("删除客户信息失败");
			e.printStackTrace();
		}
		return returndata;
	}
	

	/**
	 * 查询客户
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findCustomer", method = RequestMethod.POST)
	public @ResponseBody ReturnData findCuStomer(HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnData = new ReturnData();
		// 获取请求信息
		String id = request.getParameter("c_id");
		//奇葩的解决方法 如果写成id.equals("")?"0":id就会出现空指针
		//String aa = "".equals(id)?"0":id;
		if("".equals(id)||id==null) {
			id="0";
		}
		System.out.println(id);
		int c_id = Integer.parseInt(id);
		String c_name = request.getParameter("c_name");
		String c_identity = request.getParameter("c_identity");
		Customer c = new Customer();
		c.setC_customernumber(c_id);
		c.setC_name(c_name);
		c.setC_identity(c_identity);
		// 请求数据
		try {
			List<Customer> customers = customerService.queryCustomer(c);
			List<Object> list = new ArrayList<Object>();
			list.add(customers);
			returnData.setKey(ReturnData.SUCCESS);
			returnData.setMsg("获取客户信息成功");
			returnData.setBody(list);
		} catch (Exception e) {
			// 请求失败
			returnData.setKey(ReturnData.FAIL);
			returnData.setMsg("获取客户信息失败");
			e.printStackTrace();
		}
		return returnData;
	}

	/**
	 * 获取房态信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findRoomInformation", method = RequestMethod.GET)
	public @ResponseBody ReturnData queryRoomInformation(HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnData = new ReturnData();
		// 请求数据
		try {
			List<RoomInformation> roomInformation = customerService.queryRoomInformation();
			List<Object> list = new ArrayList<Object>();
			list.add(roomInformation);
			returnData.setKey(ReturnData.SUCCESS);
			returnData.setMsg("获取客户信息成功");
			returnData.setBody(list);
		} catch (Exception e) {
			// 请求失败
			returnData.setKey(ReturnData.FAIL);
			returnData.setMsg("获取客户信息失败");
			e.printStackTrace();
		}
		return returnData;
	}

	/**
	 * 办理客户入住
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/insertReception", method = RequestMethod.GET)
	public @ResponseBody ReturnData insertReception(HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnData = new ReturnData();
		try {
			// 获取请求数据
			Reception reception = new Reception();
			reception.setR_customernumber(Integer.parseInt(Common.ckeckNull(request.getParameter("c_id"))));
			reception.setR_roomnumber(Integer.parseInt(Common.ckeckNull(request.getParameter("r_id"))));
			reception.setR_checkin(Common.formDate(Common.ckeckNull(request.getParameter("r_checkin"))));
			String r_deposit = Common.ckeckNull(request.getParameter("r_deposit"));
			reception.setR_deposit(Float.parseFloat(r_deposit.equals("")?"0":r_deposit));
			String t_opennetwork = Common.ckeckNull(request.getParameter("t_opennetwork"));
			reception.setT_opennetwork(Integer.parseInt(t_opennetwork.equals("")?"0":t_opennetwork));
			//插入
			customerService.insertReception(reception);
			returnData.setKey(ReturnData.SUCCESS);
			returnData.setMsg("办理客户入住成功");
		} catch (Exception e) {
			// 请求失败
			returnData.setKey(ReturnData.FAIL);
			returnData.setMsg("办理客户入住失败");
			e.printStackTrace();
		}
		return returnData;
	}	
}
