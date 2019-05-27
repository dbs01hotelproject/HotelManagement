package com.dbs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dbs.po.Employee;


public class LoginInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String uri = request.getRequestURI();
		//除了login.html可以公开访问,其余将被拦截
		if(uri.indexOf("/empLogin")>0) {
			return true;
		}
		
		//获取Session 
		HttpSession session = request.getSession();
		String e_name = (String) session.getAttribute("EmployeeName");
		//session判断是否有数据,如果有,则返回true，继续向下执行
		if(e_name!=null) {
			return true;
		}else {
			request.setAttribute("msg", "您还没有登录,请先登录!");
			request.getRequestDispatcher("/empRegister.html").forward(request, response);
			//不符合条件的给我们提示信息，并转发到登录页面
			return false;
		}
		
		
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
	}
}










