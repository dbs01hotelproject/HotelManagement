package com.dbs.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dbs.po.Customer;
import com.dbs.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	
	@RequestMapping(value="/getCustomer",method=RequestMethod.GET)
	public String findCustomerById(Integer id,Model model) {
		
		model.addAttribute("customers","asd");
		//返回客户信息展示页面
		return "customer";
		
	}
	
	
	
}