package com.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.demo.model.Admin;

/**
 * Session filter.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-21 下午10:34:21
 */
public class SessionFilter implements Filter
{

	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		  HttpServletRequest requestHttp = (HttpServletRequest) request;  
	        String requestURI = requestHttp.getRequestURI().toLowerCase();  
	        // 判断是否是首次登陆  
	        boolean isLogin = requestURI.indexOf("login") >= 0;  
	        Admin admin = (Admin) requestHttp.getSession().getAttribute("loginUser");  
	        String uri = ((HttpServletRequest) request).getRequestURI();
	        String contextPath = ((HttpServletRequest) request).getContextPath();
	        if(isLogin || admin!=null || uri.contains("index.jsp") || uri.endsWith("/")|| uri.endsWith("login.jsp")){
	            chain.doFilter(request, response);
	        }else{
	            response.setContentType("text/html;charset=utf-8");
	            response.getWriter().print("<script type='text/javascript'>alert('session过期，请重新登录！');window.parent.location='"+contextPath+"/index.jsp'</script>");
	        } 
	}

	public void init(FilterConfig filterConfig) throws ServletException
	{
		// TODO Auto-generated method stub
		
	}

}
