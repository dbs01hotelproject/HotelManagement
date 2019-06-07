package com.dbs.service;

import java.util.List;

import com.dbs.po.Bill;
import com.dbs.po.Customer;
import com.dbs.po.NetworkInformation;
import com.dbs.po.NetworkManagement2;
import com.dbs.po.Reception;
import com.dbs.po.StateInfo;

public interface CashierService {
	
		//查询所有的入住信息
		public StateInfo selectforAllreception(StateInfo stateInfo);
		
		//查询入住时间所用
		public Reception selectCheckinDate(int r_roomnumber);
		
		//更新退房时间
		public void updateforLeave(Reception reception);
		
		//更新房间状态
		public void updateforRoomState(int r_number);
		
		//查询网络是否开通(网络管理) 
		public NetworkManagement2 selectNetManagement(int n_roomnumber);
		
		//查询网络是否开通(网费管理)
		public NetworkInformation selectNetInfo(int e_roomnumber);
		
		//查询网络是否开通(网费管理2)
		public NetworkInformation selectNetInfo2(int e_roomnumber);
		
		//更新是否网络开通(网络管理) 
		public void updateNetworkManagement(NetworkManagement2 neManagement);
		
		//更新是否网络开通(网费管理) 
		public void updateNetworkInformation(NetworkInformation netInformation);
		
		//更新网费
		public void updateNetFee(NetworkInformation netInformation);
		
		//查询生成账单所需要的信息 
		public Bill selectBillInfo(Reception reception);
		
		//生成账单
		public void GenerateBills(Bill bill);
		
		//更新账单信息之收费标准
		public void updateBillfeetype(Bill bill);
		
		//更新账单之入住天数与总消费
		public void updateBillDateAllPrice(Bill bill);
	
		//查询房间收费标准
		public int selectBillfeerates(int b_roomnumber);
	
}
