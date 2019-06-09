package com.dbs.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbs.po.Bill;
import com.dbs.po.NetworkInformation;
import com.dbs.po.NetworkManagement2;
import com.dbs.po.Reception;
import com.dbs.po.StateInfo;
import com.dbs.service.CashierService;
import com.dbs.util.ReturnData;

@RequestMapping(value = "/CashierController")
@Controller
public class CashierController {

	@Autowired
	private CashierService cashierMapperService;
	
	@RequestMapping(value = "/searchRoomAndCustomer")
	// @RequestBady 将请求体中的JSON数据绑定到形参employee中
	public @ResponseBody ReturnData searchRoomAndCustomer(@RequestBody StateInfo stateInfo, HttpSession session) {
		System.out.println("开始searchAllReception方法");
		ReturnData returnData = new ReturnData();
		StateInfo sta = new StateInfo();
		sta = cashierMapperService.selectforAllreception(stateInfo);
		if(sta!=null) {
			returnData.setKey(returnData.SUCCESS);
			List<Object> list = new ArrayList<Object>();
			list.add(sta);
			returnData.setBody(list);
		}
		return returnData;
	}
	
	@RequestMapping(value = "/updateBill")
	// @RequestBady 将请求体中的JSON数据绑定到形参employee中
	public @ResponseBody ReturnData updateBill(@RequestBody StateInfo stateInfo, HttpSession session) throws ParseException {
		System.out.println("开始updateBill方法");
		ReturnData returnData = new ReturnData();
		int id = stateInfo.getR_roomnumber();
		//开始更新房间状态
		cashierMapperService.updateforRoomState(id);
		
		//开始更新退房时间
		Reception reception = new Reception();
		reception.setR_roomnumber(id);
		reception.setR_leave(new Date(System.currentTimeMillis()));
		cashierMapperService.updateforLeave(reception);
		
		//更新网络开通状态
		NetworkManagement2 netManagement2 = new NetworkManagement2();
		netManagement2 = cashierMapperService.selectNetManagement(id);
		if(netManagement2!=null) {
			netManagement2.setN_closetime(new Date(System.currentTimeMillis()).toString());
			cashierMapperService.updateNetworkManagement(netManagement2);
		}
		
		
		//更新网络开通状态(网费管理)
		NetworkInformation neInformation = new NetworkInformation();
		neInformation = cashierMapperService.selectNetInfo(id);
		if(neInformation!=null) {
			neInformation.setE_closetime(new Date(System.currentTimeMillis()).toString());
			cashierMapperService.updateNetworkInformation(neInformation);
		}
		
		//更新网费
		NetworkInformation net2 = new NetworkInformation();
		net2 = cashierMapperService.selectNetInfo2(id);  //入住时间和退房时间都不能为空的查询
		if(net2!=null) {
			String open = net2.getE_opentime();
			String close = net2.getE_closetime();
			int date = CashierController.cacuDate(close, open);
			if(date>0) {
			//	System.out.println(date);
				net2.setE_cost(date*15);
			//	System.out.println("网费为"+date*15);
			}else {
				net2.setE_cost(15);
			}
			cashierMapperService.updateNetFee(net2);
		}
		
		//生成账单
		Bill bill = new Bill();
		bill = cashierMapperService.selectBillInfo(reception);
		Reception re = cashierMapperService.selectCheckinDate(id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		bill.setB_leave(sdf.format(new java.util.Date()));
		String b_open = sdf.format(re.getR_checkin());
		String b_close = bill.getB_leave();
		int b_date = CashierController.cacuDate(b_close, b_open);
		bill.setB_day(b_date);
		//先将数据插入表中，此时还没有房间价格标准和总花费的数据
		cashierMapperService.GenerateBills(bill);
		//更新房间价格标准
		cashierMapperService.updateBillfeetype(bill);
		
		bill.setB_feerates(cashierMapperService.selectBillfeerates(id));
	//	System.out.println("b_date为"+b_date);
		
		if(b_date>0) {
			
			bill.setB_allcosts(b_date*bill.getB_feerates());
		//	System.out.println("总的花费为"+bill.getB_allcosts());
		}else {
			
			bill.setB_allcosts(bill.getB_feerates());
		//	System.out.println("总的花费为"+bill.getB_allcosts());
		}
		//更新总花费
		cashierMapperService.updateBillDateAllPrice(bill);
	//	System.out.println(bill);
		//返回数据到前台
		returnData.setKey(returnData.SUCCESS);
		List<Object> list = new ArrayList<Object>();
		list.add(bill);
		returnData.setBody(list);
		return returnData;
	}
	
	//计算天数
	public static int cacuDate(String dbtime1,String dbtime2) throws ParseException {
		System.out.println(dbtime2);
		System.out.println(dbtime1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = format.parse(dbtime1);
		java.util.Date date2 = format.parse(dbtime2);
		int a = (int) ((date1.getTime() - date2.getTime()) / (1000*3600*24));
		
	//	System.out.println(a);
		
		return a;
	}
	
}
