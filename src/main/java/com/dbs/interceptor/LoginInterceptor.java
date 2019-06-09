package com.dbs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String uri = request.getRequestURI();
		System.out.println("请求地址：" + uri);
		if (uri.indexOf("/login") >= 0) {
			return true;
		}
		
		if (uri.indexOf(".js") >= 0) {
			return true;
		}
		
		if (uri.indexOf(".css") >= 0) {
			return true;
		}

		// 获取Session
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("EmployeeName");
		System.out.println("session：" + user);
		// session判断是否有数据,如果有,则返回true，继续向下执行
		if (user != null) {
			return true;
		} 
		response.sendRedirect("login.html");
		return false;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
	}
}
