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

		String uri = request.getRequestURI(); //
		System.out.println("请求的地址为:"+uri);
		if ((uri.indexOf("/empLogin") > 0)||  (uri.indexOf("/login2")>0)) {
			return true;
		}
		System.out.println("开始获取session");
		// 获取Session
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("EmployeeName");
		System.out.println("session的值"+user);
		// session判断是否有数据,如果有,则返回true，继续向下执行
		if (user != null) {
			return true;
		} else {
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
