package com.dbs.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbs.po.Customer;
import com.dbs.po.NetworkInformation;
import com.dbs.po.Reception;
import com.dbs.po.RoomInformation;
import com.dbs.service.CustomerService;
import com.dbs.util.Common;
import com.dbs.util.ReturnData;

/**
 * 
 * 接待信息管理
 * 
 * @author muyian
 * @date 2019/5/26
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	/**
	 * 
	 * 登记客户信息
	 * 
	 * @author lezhinan
	 * @param request
	 * @param response
	 * @return returndata
	 */
	@RequestMapping(value = "/checkinCustomer", method = RequestMethod.POST)
	public @ResponseBody ReturnData checkinCuStomer(HttpServletRequest request, HttpServletResponse response) {
		ReturnData returndata = new ReturnData();
		try {
			// 接收请求数据
			Customer customer = new Customer();
			customer.setC_address(Common.ckeckNull(request.getParameter("c_address")));
			customer.setC_identity(Common.ckeckNull(request.getParameter("c_identity")));
			customer.setC_name(Common.ckeckNull(request.getParameter("c_name")));
			customer.setC_sex(Common.ckeckNull(request.getParameter("c_sex")));
			customer.setC_tel(Common.ckeckNull(request.getParameter("c_tel")));
			// 插入
			customerService.checkinCustomer(customer);
			returndata.setKey(ReturnData.SUCCESS);
			returndata.setMsg(" 登记客户信息 成功");
		} catch (Exception e) {
			//
			returndata.setKey(ReturnData.FAIL);
			returndata.setMsg(" 登记客户信息 失败");
			e.printStackTrace();
		}
		return returndata;
	}

	/**
	 * 修改客户信息
	 * 
	 * @author lezhinan
	 * @param request
	 * @param response
	 * @return returndata
	 */
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
	public @ResponseBody ReturnData updateCuStomer(HttpServletRequest request, HttpServletResponse response) {
		ReturnData returndata = new ReturnData();
		try {
			// 获取请求数据
			Customer customer = new Customer();
			customer.setC_address(Common.ckeckNull(request.getParameter("c_address")));
			customer.setC_customernumber(Integer.parseInt(request.getParameter("c_customernumber")));
			customer.setC_identity(Common.ckeckNull(request.getParameter("c_identity")));
			customer.setC_name(Common.ckeckNull(request.getParameter("c_name")));
			customer.setC_sex(Common.ckeckNull(request.getParameter("c_sex")));
			customer.setC_tel(Common.ckeckNull(request.getParameter("c_tel")));
			// 修改
			customerService.updateCustomer(customer);
			returndata.setKey(ReturnData.SUCCESS);
			returndata.setMsg("修改客户信息 成功");
		} catch (Exception e) {
			// 失败
			returndata.setKey(ReturnData.FAIL);
			returndata.setMsg("修改客户信息 失败");
			e.printStackTrace();
		}
		return returndata;
	}

	/**
	 * 删除客户信息
	 * 
	 * @author lezhinan
	 * @param request
	 * @param response
	 * @return returndata
	 */
	@RequestMapping(value = "/delCustomer", method = RequestMethod.POST)
	public @ResponseBody ReturnData delCuStomer(HttpServletRequest request, HttpServletResponse response) {
		ReturnData returndata = new ReturnData();
		try {
			Customer customer = new Customer();
			String[] ids = request.getParameter("c_customernumber").split(",");
			for (int i = 0; i < ids.length; i++) {
				customer.setC_customernumber(Integer.parseInt(ids[i]));
				// 删除
				customerService.delCustomer(customer);
			}
			returndata.setKey(ReturnData.SUCCESS);
			returndata.setMsg("删除客户信息 成功");
		} catch (Exception e) {
			// 失败
			returndata.setKey(ReturnData.FAIL);
			returndata.setMsg("删除客户信息 失败");
			e.printStackTrace();
		}
		return returndata;
	}

	/**
	 * 查询客户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findCustomer", method = RequestMethod.POST)
	public @ResponseBody ReturnData findCuStomer(HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnData = new ReturnData();
		// 获取请求数据
		String id = Common.ckeckNull(request.getParameter("c_id"));
		if ("".equals(id)) {
			id = "0";
		}
		int c_id = Integer.parseInt(id);
		String c_name = request.getParameter("c_name");
		String c_identity = request.getParameter("c_identity");
		Customer c = new Customer();
		c.setC_customernumber(c_id);
		c.setC_name(c_name);
		c.setC_identity(c_identity);
		// 查询
		try {
			List<Customer> customers = customerService.queryCustomer(c);
			List<Object> list = new ArrayList<Object>();
			list.add(customers);
			returnData.setKey(ReturnData.SUCCESS);
			returnData.setMsg("查询客户信息 成功");
			returnData.setBody(list);
		} catch (Exception e) {
			returnData.setKey(ReturnData.FAIL);
			returnData.setMsg("查询客户信息 失败");
			e.printStackTrace();
		}
		return returnData;
	}

	/**
	 * 查看房态信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findRoomInformation", method = RequestMethod.POST)
	public @ResponseBody ReturnData queryRoomInformation(HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnData = new ReturnData();

		String state = request.getParameter("r_state");
		int r_state = "".equals(Common.ckeckNull(state)) ? -1 : Integer.parseInt(state);
		String type = request.getParameter("r_type");
		int r_tpye = "".equals(Common.ckeckNull(type)) ? -1 : Integer.parseInt(type);
		RoomInformation room = new RoomInformation();
		room.setR_state(r_state);
		room.setR_tpye(r_tpye);
		try {
			List<RoomInformation> roomInformation = customerService.queryRoomInformation(room);
			List<Object> list = new ArrayList<Object>();
			list.add(roomInformation);
			returnData.setKey(ReturnData.SUCCESS);
			returnData.setMsg("查看房态信息成功");
			returnData.setBody(list);
		} catch (Exception e) {
			returnData.setKey(ReturnData.FAIL);
			returnData.setMsg("查看房态信息失败");
			e.printStackTrace();
		}
		return returnData;
	}

	/**
	 * 办理客户入住
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/insertReception", method = RequestMethod.POST)
	public @ResponseBody ReturnData insertReception(HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnData = new ReturnData();
		try {
			// 获取请求数据
			Reception reception = new Reception();
			reception.setR_customernumber(Integer.parseInt(Common.ckeckNull(request.getParameter("c_id"))));
			reception.setR_roomnumber(Integer.parseInt(Common.ckeckNull(request.getParameter("r_id"))));
			reception.setR_checkin(Common.formDate(request.getParameter("r_checkin")));
			reception.setR_deposit(Float.parseFloat(request.getParameter("r_deposit")));
			reception.setT_opennetwork(Integer.parseInt(request.getParameter("t_opennetwork")));
			// 插入
			customerService.insertReception(reception);
			// 改变房间状态
			RoomInformation roomInformation = new RoomInformation();
			roomInformation.setR_number(reception.getR_roomnumber());
			roomInformation.setR_state(1);
			customerService.updateRoomState(roomInformation);

			// 网络信息
			if (reception.getT_opennetwork() == 0) {
				NetworkInformation networkInformation = new NetworkInformation();
				networkInformation.setE_customernumbernumber(reception.getR_customernumber());
				networkInformation.setE_roomnumber(reception.getR_roomnumber());
				networkInformation.setE_opentime(Common.formDate3(new Date()));
				customerService.insertNetwork(networkInformation);
			}
			returnData.setKey(ReturnData.SUCCESS);
			returnData.setMsg("办理客户入住成功");
		} catch (Exception e) {
			// 失败
			returnData.setKey(ReturnData.FAIL);
			returnData.setMsg("办理客户入住失败");
			e.printStackTrace();
		}
		return returnData;
	}
}
