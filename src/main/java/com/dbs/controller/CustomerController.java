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
	CustomerService customerService;

	@RequestMapping(value="/findCustomer",method=RequestMethod.GET)
	public void findCuStomer(Integer id,Model model) {
		Customer customer = customerService.queryCustomerById(id);
		model.addAttribute("customer",customer);
	}
}

