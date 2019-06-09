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
import com.dbs.po.Reception;
import com.dbs.po.Reservation;
import com.dbs.po.RoomInformation;
import com.dbs.service.CustomerService;
import com.dbs.service.ReservationService;
import com.dbs.util.Common;
import com.dbs.util.ReturnData;

/**
 * 预定管理
 * 
 * @author muyian
 *
 */
@Controller
@RequestMapping(value = "/reservation")
public class ReservationController {

	@Autowired
	ReservationService reservationService;

	@Autowired
	CustomerService customerService;

	/**
	 * 添加预定
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody ReturnData insertReception(HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnData = new ReturnData();
		try {
			// 获取请求参数
			Reservation reservation = new Reservation();
			String r_name = request.getParameter("r_name");
			int r_roomnumber = Integer.parseInt(request.getParameter("r_roomnumber"));
			reservation.setR_name(r_name);
			RoomInformation room = new RoomInformation();
			room.setR_number(r_roomnumber);
			reservation.setRoomInformation(room);
			reservation.setR_date(Common.formDate3(new Date()));
			// 插入
			int count = reservationService.insertReservation(reservation);
			if (count == 1) {
				returnData.setKey(ReturnData.SUCCESS);
				returnData.setMsg("办理客户入住成功");
			} else {
				returnData.setKey(ReturnData.FAIL);
				returnData.setMsg("办理客户入住失败");
			}

			// 修改房间状态
			RoomInformation roomInformation = new RoomInformation();
			roomInformation.setR_number(r_roomnumber);
			roomInformation.setR_state(0);
			customerService.updateRoomState(roomInformation);

		} catch (Exception e) {
			// 失败
			returnData.setKey(ReturnData.FAIL);
			returnData.setMsg("办理客户入住失败");
			e.printStackTrace();
		}
		return returnData;
	}

	/**
	 * 查询预定
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	public @ResponseBody ReturnData queryReceptionList(HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnData = new ReturnData();
		try {
			// 获取请求参数
			Reservation reservation = new Reservation();
			String number = request.getParameter("r_number");
			int r_number = "".equals(number) ? 0 : Integer.parseInt(number);
			String r_name = request.getParameter("r_name");
			reservation.setR_number(r_number);
			reservation.setR_name(r_name);
			String r_date = request.getParameter("r_date");
			if (!"".equals(r_date)) {
				reservation.setR_date(r_date);
			}
			List<Reservation> reservationList = reservationService.queryReservationList(reservation);
			List<Object> list = new ArrayList<Object>();
			list.add(reservationList);
			returnData.setBody(list);
			returnData.setKey(ReturnData.SUCCESS);
			returnData.setMsg("查询预定信息成功");
		} catch (Exception e) {
			// 失败
			returnData.setKey(ReturnData.FAIL);
			returnData.setMsg("查询预定信息失败");
			e.printStackTrace();
		}
		return returnData;
	}

	/**
	 * 根据id查询预定
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryReservationById", method = RequestMethod.POST)
	public @ResponseBody ReturnData queryReceptionById(HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnData = new ReturnData();
		try {
			int r_number = Integer.parseInt(request.getParameter("r_number"));
			Reservation reservation = reservationService.queryReservationById(r_number);
			List<Object> list = new ArrayList<Object>();
			list.add(reservation);
			returnData.setBody(list);
			returnData.setKey(ReturnData.SUCCESS);
			returnData.setMsg("查询预定信息成功");
		} catch (Exception e) {
			// 失败
			returnData.setKey(ReturnData.FAIL);
			returnData.setMsg("查询预定信息失败");
			e.printStackTrace();
		}
		return returnData;
	}

	/**
	 * 修改预定
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody ReturnData updateReception(HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnData = new ReturnData();
		try {
			// 获取请求参数
			Reservation reservation = new Reservation();
			int r_number = Integer.parseInt(request.getParameter("r_number"));
			int r_roomnumber = Integer.parseInt(request.getParameter("r_roomnumber"));
			RoomInformation room = new RoomInformation();
			room.setR_number(r_roomnumber);
			reservation.setRoomInformation(room);
			reservation.setR_number(r_number);
			reservation.setR_date(Common.formDate3(new Date()));
			int row = reservationService.updateReservation(reservation);
			if (row == 1) {
				returnData.setKey(ReturnData.SUCCESS);
				returnData.setMsg("修改预定信息成功");
			} else {
				returnData.setKey(ReturnData.FAIL);
				returnData.setMsg("修改预定信息失败");
			}
		} catch (Exception e) {
			// 失败
			returnData.setKey(ReturnData.FAIL);
			returnData.setMsg("修改预定信息失败");
			e.printStackTrace();
		}
		return returnData;
	}

	/**
	 * 删除预定
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody ReturnData deleteReception(HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnData = new ReturnData();
		try {
			//删除预定
			String[] ids = request.getParameter("r_numbers").split(",");
			int row = reservationService.deleteReservation(ids);

			if (row >= 1) {
				returnData.setKey(ReturnData.SUCCESS);
				returnData.setMsg("修改预定信息成功");
				// 改变房间状态
				for (int r_roomnumber = 0; r_roomnumber < ids.length; r_roomnumber++) {
					RoomInformation roomInformation = new RoomInformation();
					roomInformation.setR_number(r_roomnumber);
					roomInformation.setR_state(2);
					customerService.updateRoomState(roomInformation);
				}
			} else {
				returnData.setKey(ReturnData.FAIL);
				returnData.setMsg("修改预定信息失败");
			}
		} catch (Exception e) {
			// 失败
			returnData.setKey(ReturnData.FAIL);
			returnData.setMsg("修改预定信息失败");
			e.printStackTrace();
		}
		return returnData;
	}

	/**
	 * 预定转接待
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/transferReception", method = RequestMethod.POST)
	public @ResponseBody ReturnData transationReception(HttpServletRequest request, HttpServletResponse response) {
		ReturnData returnData = new ReturnData();
		try {
			// 获取请求参数
			String r_number = request.getParameter("r_number");
			String r_roomnumber = request.getParameter("r_roomnumber");
			// 新增客户
			Customer customer = new Customer();
			customer.setC_name(Common.ckeckNull(request.getParameter("c_name")));
			customer.setC_address("");
			customer.setC_identity("");
			customer.setC_sex("");
			customer.setC_tel("");
			// 返回新增客户id
			int c_number = customerService.checkinCustomer(customer);
			// 入住
			Reception reception = new Reception();
			reception.setR_customernumber(c_number);
			reception.setR_roomnumber(Integer.parseInt(r_roomnumber));
			reception.setR_checkin(new Date());
			reception.setR_deposit(0.0f);
			reception.setT_opennetwork(1);
			customerService.insertReception(reception);
			// 改变房间状态
			RoomInformation roomInformation = new RoomInformation();
			roomInformation.setR_number(Integer.parseInt(r_roomnumber));
			roomInformation.setR_state(1);
			customerService.updateRoomState(roomInformation);
			// 删除该条预定信息
			reservationService.deleteReservation(r_number.split(","));
			returnData.setKey(ReturnData.SUCCESS);
			returnData.setMsg("成功接待");
		} catch (Exception e) {
			// 失败
			returnData.setKey(ReturnData.FAIL);
			returnData.setMsg("接待失败");
			e.printStackTrace();
		}
		return returnData;
	}
}
