package com.dbs.service.impl;


import java.sql.Date;

import org.apache.catalina.util.NetMask;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.dbs.mapper.CashierMapper;
import com.dbs.po.Bill;
import com.dbs.po.Customer;
import com.dbs.po.NetworkInformation;
import com.dbs.po.NetworkManagement;
import com.dbs.po.NetworkManagement2;
import com.dbs.po.Reception;
import com.dbs.po.RoomInformation;
import com.dbs.po.StateInfo;

import java.util.List;

@Controller
public class FuntionTest {

	
	@Test
	public void selectforAllreceptionfor() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		CashierMapper cashierMapper = ctx.getBean(CashierMapper.class);
		StateInfo stateInfo = new StateInfo();
		stateInfo.setR_roomnumber(5);
		stateInfo.setC_name("小三");
		System.out.println(cashierMapper.selectforAllreception(stateInfo));
	}
	
	@Test
	public void updateforLeaveTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		CashierMapper cashierMapper = ctx.getBean(CashierMapper.class);
		Reception reception = new Reception();
		reception.setR_number(1);
		reception.setR_leave(new Date(System.currentTimeMillis()));
		cashierMapper.updateforLeave(reception);
		
	}
	
	@Test
	public void GenerateBillsTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		CashierMapper cashierMapper = ctx.getBean(CashierMapper.class);
		Bill bill = new Bill();
		bill.setB_customernumber(100);
		bill.setB_roomnumber(1);
		bill.setB_type(1);
		bill.setB_leave("1997-01-02");
		bill.setB_allcosts(300);
		cashierMapper.GenerateBills(bill);
		
	}
	
	@Test
	public void selectBillInfoTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		CashierMapper cashierMapper = ctx.getBean(CashierMapper.class);
		Reception customer = new Reception();
		customer.setR_roomnumber(1);
		System.out.println(cashierMapper.selectBillInfo(customer));
	}
	
	@Test
	public void selectNetManagementTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		CashierMapper cashierMapper = ctx.getBean(CashierMapper.class);
		
		System.out.println(cashierMapper.selectNetManagement(1));
	}
	
	@Test
	public void updateBillDateAllPriceTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		CashierMapper cashierMapper = ctx.getBean(CashierMapper.class);
		Bill bill = new Bill();
		bill.setB_allcosts(100);
		bill.setB_roomnumber(1);
		cashierMapper.updateBillDateAllPrice(bill);
	}

}
