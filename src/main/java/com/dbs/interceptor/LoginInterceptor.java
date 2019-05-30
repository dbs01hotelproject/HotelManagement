package com.dbs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dbs.po.User;


public class LoginInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String uri = request.getRequestURI();
		//闄や簡login.jsp鍙互鍏紑璁块棶,鍏朵綑灏嗚鎷︽埅
		if(uri.indexOf("/login")>0) {
			return true;
		}
		
		//鑾峰彇Session 
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("USERSESSION");
		//session鍒ゆ柇鏄惁鏈夋暟鎹�,濡傛灉鏈�,鍒欒繑鍥瀟rue锛岀户缁悜涓嬫墽琛�
		if(user!=null) {
			return true;
		}
		
		System.out.println("test");
		
		request.setAttribute("msg", "鎮ㄨ繕娌℃湁鐧诲綍,璇峰厛鐧诲綍!");
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		//涓嶇鍚堟潯浠剁殑缁欐垜浠彁绀轰俊鎭紝骞惰浆鍙戝埌鐧诲綍椤甸潰
		return false;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
	}
}










