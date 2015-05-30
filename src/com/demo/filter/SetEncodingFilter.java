package com.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Set encoding.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-2 上午10:38:34
 */
public class SetEncodingFilter implements Filter{
	
	String encoding = null;

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (this.encoding != null) {
			request.setCharacterEncoding(this.encoding);// 设置编码格式
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {

		this.encoding = config.getInitParameter("encoding");// 获得参数
	}
}
