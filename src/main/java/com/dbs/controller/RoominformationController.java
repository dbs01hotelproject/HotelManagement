package com.dbs.controller;

import java.text.Format;
import java.text.SimpleDateFormat;
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

import com.dbs.po.NetworkManagement;
import com.dbs.po.RoomInformation;
import com.dbs.service.RoominformationService;
import com.dbs.util.ReturnData;

@Controller
@RequestMapping(value = "/room")
public class RoominformationController {
	@Autowired
	RoominformationService roominformationService;

	/***
	 * 添加住房信息
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public String login() {
		return "indexRoominformation";
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.GET)
	public String login1() {
		return "updateRoominformation";
	}
	@RequestMapping(value = "/select", method=RequestMethod.GET)
	public String login2() {
		return "selectRoominformation";
	}
	@RequestMapping(value = "/insertroominformation", method = RequestMethod.POST)
	public @ResponseBody ReturnData insertroominformation(HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		ReturnData returnData = new ReturnData();
		//int r_number = Integer.parseInt(request.getParameter("r_number"));
		RoomInformation roominformation = new RoomInformation();
		RoomInformation roominformation1 = new RoomInformation();
		if(request.getParameter("r_number")!="") {
			try {
				roominformation1 = roominformationService.findRoominformationByNumber(Integer.parseInt(request.getParameter("r_number")));
				if (roominformation1==null){
					System.out.println("-------");
					roominformation.setR_number(Integer.parseInt(request.getParameter("r_number")));
					roominformation.setR_direction(request.getParameter("r_direction"));
					roominformation.setR_tpye(Integer.parseInt(request.getParameter("r_tpye")));
					roominformation.setR_equipment(Integer.parseInt(request.getParameter("r_equipment")));
					roominformation.setR_state(Integer.parseInt(request.getParameter("r_state")));
					roominformationService.addRoominformation(roominformation);
					List<Object> listroominformation = new ArrayList<Object>();
					listroominformation.add(roominformation);
					returnData.setBody(listroominformation);
					returnData.setKey("SUCCESS");
					returnData.setMsg("客房信息添加成功");
				}
				else {
					returnData.setKey("FAIL");
					returnData.setMsg("客房信息添加失败");
					System.out.println();
			}
			
				
		} catch (Exception e) {
		}
		
		}
			else {
				returnData.setKey("FAIL");
				returnData.setMsg("客房信息添加失败");
				System.out.println();
		}
		
		
	/*	roominformation1 = roominformationService.findRoominformationByNumber(r_number);
		List<Object> listroominformation = new ArrayList<Object>();
		listroominformation.add(roominformation);
		if (roominformation1.getR_number() > 0) {
			returnData.setBody(listroominformation);
			returnData.setKey("SUCCESS");
			returnData.setMsg("客房信息添加成功");
		} else {
			returnData.setKey("FAIL");
			returnData.setMsg("客房信息添加失败");
			System.out.println();

		}*/
		
	//	ReturnData returnData = new ReturnData();
	//	returnData.setKey("success11111");
		return returnData;
	}

	/***
	 * 查找响应的客房信息，由于是按照 主键来查找则每次只能查找一条数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "findRoominformationByNumber", method = RequestMethod.POST)
	public @ResponseBody ReturnData findRoominformationByNumber(HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		// Roominformation roominformation = new Roominformation();
		ReturnData returnData = new ReturnData();
		int r_number = Integer.parseInt(request.getParameter("r_number"));
		RoomInformation room1 = roominformationService.findRoominformationByNumber(r_number);
		if (room1 != null) {
			List<Object> list = new ArrayList<Object>();
			/**
			 * roominformation.setR_direction(room1.getR_direction());
			 * roominformation.setR_equipment(room1.getR_equipment());
			 * roominformation.setR_number(room1.getR_number());
			 * roominformation.setR_state(room1.getR_state());
			 * roominformation.setR_tpye(room1.getR_tpye());
			 */
			returnData.setKey("SUCCESS");
			returnData.setMsg("查看信息成功");
			list.add(room1);
			returnData.setBody(list);
		} else {
			returnData.setKey("FAIL");
			returnData.setMsg("查询客房信息失败");

		}

		return returnData;
	}

	/**
	 * 根据房间号对其进行删除
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteRoominformationByNumberlogin", method = RequestMethod.POST)
	public @ResponseBody ReturnData deleteRoominformationByNumber(HttpServletResponse response,
			HttpServletRequest request) {
		ReturnData returnData = new ReturnData();
		int r_number = Integer.parseInt(request.getParameter("r_number"));
		int n_roomnumber = r_number;
		try {
			roominformationService.deleteNetworkManagementByNumber(n_roomnumber);
			int deletenumber = roominformationService.deleteRoominformationByNumber(r_number);
			if (deletenumber > 0) {
				returnData.setKey("SUCCESS");
				returnData.setMsg("你已经删除了编号为  " + r_number + "的房间");
			} else {
				returnData.setKey("FAIL");
				returnData.setMsg("对不起没有这个  " + r_number + "  的房间，无法进行删除");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return returnData;
	}

	/**
	 * 修改先判断是否有这个房间 如果有就进行修改
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateRoominformationByNumberlogin", method = RequestMethod.POST)
	public @ResponseBody ReturnData updateRoominformationByNumber(HttpServletResponse response,
			HttpServletRequest request) {
		int r_number = Integer.parseInt(request.getParameter("r_number"));
		RoomInformation roominformation = new RoomInformation();
		ReturnData returnData = new ReturnData();
		List<Object> list = new ArrayList<Object>();
		RoomInformation room1 = null;
		try {
			room1 = roominformationService.findRoominformationByNumber(r_number);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (room1 != null) {
			roominformation.setR_number(r_number);
			roominformation.setR_direction(request.getParameter("r_direction"));
			roominformation.setR_equipment(Integer.parseInt(request.getParameter("r_equipment")));
			roominformation.setR_state(Integer.parseInt(request.getParameter("r_state")));
			roominformation.setR_tpye(Integer.parseInt(request.getParameter("r_tpye")));
			System.out.println(Integer.parseInt(request.getParameter("r_tpye")));
			try {
				roominformationService.updateRoominformationByNumber(roominformation);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(roominformation);

			returnData.setBody(list);
			returnData.setKey("SUCCESS");
			returnData.setMsg("修改" + r_number + "的信息成功");
		} else {
			returnData.setKey("FAIL");
			returnData.setMsg("修改" + r_number + "信息失败没有这个房间");
		}

		return returnData;

	}

	/**
	 * 判断是否开通网络管理
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/openNetworkManagement", method = RequestMethod.POST)
	public @ResponseBody ReturnData openNetworkManagement(HttpServletResponse response, HttpServletRequest request) {
		int r_number = Integer.parseInt(request.getParameter("r_number"));
		System.out.println(r_number);
		ReturnData returnData = new ReturnData();
		List<Object> list = new ArrayList<Object>();
		RoomInformation room1 = null;
		NetworkManagement networkManagement = null;
		try {
			room1 = roominformationService.findRoominformationByNumber(r_number);
		if (room1 != null) {
			// 进行多表连接
				networkManagement = roominformationService.openNetworkManagement(r_number);
			if (networkManagement!=null) {
				list.add(networkManagement);
				returnData.setBody(list);
				returnData.setKey("SUCCESS");
				returnData.setMsg("此人开通的时间是：" + networkManagement.getN_opentime());
			} else {
				System.out.println("===============");
				returnData.setKey("FAIL");
				returnData.setMsg("不好意思你没有开通网络功能");
			}
			}
		else {
			System.out.println("===============");
			returnData.setKey("FAIL");
			returnData.setMsg("不好意思你没有开通网络功能");
		}
		}
		catch (Exception e) {
				e.printStackTrace();
			}
		return returnData;
	}
}
