package com.dbs.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.dbs.po.Employee;

@Controller
@RequestMapping("/login")
public class CustomerController {

	@RequestMapping(value= {"/users"})
	public String register() {
		return "register";
	}
	
	//@RequestMapping(method=RequestMethod.POST)
		@PostMapping()			 //低版本不支持
		public ModelAndView createUser(Employee user) {
			
			ModelAndView mav= new ModelAndView();
			mav.setViewName("createSuccess");
			mav.addObject("user",user);
			return mav;
			
		}
	
	
}

