package com.dbs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dbs.po.User;


@Controller
public class UserController {

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String toLogin() {
		
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(User user,Model model,HttpSession session){
		
		String username = user.getUsername();
		String password = user.getPassword();
		//此处根据username和password从数据库中查询信息
		
		if(username!=null&&username.equals("admin")&&password!=null&&password.equals("123")) {
			session.setAttribute("USERSESSION", user);
			return "redirect:main";
		}
		model.addAttribute("msg","用户名或者密码错误,请重新登录!");
		return "login";
	}
	
	@RequestMapping(value="/main")
	public String toMain() {
		
		return "main";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		//清空session
		session.invalidate();
		//重定向到登录页面的跳转方法
		return "redirect:login";
		
	}
	
}
