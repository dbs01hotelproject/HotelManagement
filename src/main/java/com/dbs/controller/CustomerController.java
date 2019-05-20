package com.dbs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbs.po.Customer;
import com.dbs.service.CustomerService;
import com.dbs.util.ReturnData;

@Controller
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@RequestMapping(value="/findCustomer",method=RequestMethod.GET)
	public @ResponseBody ReturnData findCuStomer(Integer id,ServletRequest request,ServletResponse response) throws ServletException, IOException {
		Customer customer = customerService.queryCustomerById(id);
		ReturnData returnData = new  ReturnData();
		returnData.setKey("truekey");
		returnData.setMsg("登陆成功");
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(customer);
		System.out.println(customer);
		request.getRequestDispatcher("").forward(request, response);
		return returnData;
		////////////////测试
	}
	
	
}

