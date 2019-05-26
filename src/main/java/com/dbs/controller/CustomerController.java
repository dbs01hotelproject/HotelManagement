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
		int c_id = Integer.parseInt(request.getParameter("c_id"));
		String c_name = request.getParameter("c_name");
		String c_identity = request.getParameter("c_identity");
		Customer c = new Customer();
		c.setC_customernumber(c_id);
		c.setC_name(c_name);
		c.setC_identity(c_identity);
		// 请求数据
		try {
			Customer customer = customerService.queryCustomer(c);
			List<Object> list = new ArrayList<Object>();
			list.add(customer);
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
